package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.explosion.Explosion;
import org.btwr.shared_library.api.block.util.FireBlockUtils;
import org.btwr.shared_library.api.entity.interfaces.IFallingImpactData;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.mixin.FallingBlockEntityAccessor;
import org.btwr.sturdy_trees.particle.SturdyTreesParticles;
import org.jetbrains.annotations.Nullable;

public class SmoulderingLogBlock extends FallingBlock {

    public static final MapCodec<SmoulderingLogBlock> CODEC = createCodec(SmoulderingLogBlock::new);

    public static final IntProperty BURN_LEVEL = IntProperty.of("burn_level", 0, 3);
    public static final BooleanProperty SUPPRESS_SNAP_ON_FALL = BooleanProperty.of("suppress_snap_on_fall");

    private static final int CHANCE_OF_DECAY = 5;
    private static final int CHANCE_OF_EXTINGUISH_IN_RAIN = 5;
    private static final float EXPLOSION_STRENGTH = 1.0F;

    public SmoulderingLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(BURN_LEVEL, 0)
                .with(SUPPRESS_SNAP_ON_FALL, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BURN_LEVEL, SUPPRESS_SNAP_ON_FALL);
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!hasWaterToSidesOrTop(world, pos)) {
            // prevent falling behavior for stumps and first level cinders

            if (shouldFall(state) && state.get(BURN_LEVEL) > 0) {
                super.scheduledTick(state, world, pos, random);
            }
        }
        else {
            // extinguish due to neighboring water blocks
            convertToCinders(world, pos);
            world.playSound(
                    null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F
            );
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
            if (!checkForGoOutInRain(world, pos)) {
                FireBlockUtils.checkForSmoulderingSpreadFromLocation(world, pos);

                int burnLevel = state.get(BURN_LEVEL);
                if (burnLevel == 0) {
                    if (!FireBlockUtils.hasFlammableNeighborsWithinSmoulderRange(world, pos)) {
                        state = state.with(BURN_LEVEL, 1);

                        if (isSupportedBySolidBlocks(world, pos)) {
                            // intentionally leaves the flag as true if it's already set
                            state = state.with(SUPPRESS_SNAP_ON_FALL, true);
                        }

                        world.setBlockState(pos, state);
                        world.scheduleBlockTick(pos, this, this.getFallDelay());
                    }
                }
                else if (random.nextInt(CHANCE_OF_DECAY) == 0) {
                    if (burnLevel < 3) {
                        world.setBlockState(pos, state.with(BURN_LEVEL, burnLevel + 1));
                    }
                    else {
                        convertToCinders(world, pos);
                    }
                }
            }
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        emitSmokeParticles(
                world,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                random,
                state.get(BURN_LEVEL)
        );

        // Play fire crackling sounds
        if (random.nextInt(getSoundChance()) == 0) {
            world.playSound(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    SoundEvents.BLOCK_FIRE_AMBIENT,
                    SoundCategory.BLOCKS,
                    0.1F + random.nextFloat() * 0.1F,
                    random.nextFloat() * 0.7F + 0.3F,
                    false
            );
        }
    }

    private void emitSmokeParticles(World world, double centerX, double centerY, double centerZ, Random rand, int burnLevel)
    {
        for (int i = 0; i < getParticleCount(); ++i) {
            double x = centerX - 0.60D + rand.nextDouble() * 1.2D;
            double y = centerY + 0.25D + rand.nextDouble() * 0.25D;
            double z = centerZ - 0.60D + rand.nextDouble() * 1.2D;

            if (burnLevel > 0) {
                world.addParticle(SturdyTreesParticles.SLOW_WHITE_SMOKE, x, y, z, 0, 0, 0);
            }
            else {
                world.addParticle(ParticleTypes.LARGE_SMOKE, x, y, z, 0, 0, 0);
            }
        }
    }


    @Override
    protected void configureFallingBlockEntity(FallingBlockEntity entity) {
        World world = entity.getWorld();
        BlockState state = entity.getBlockState();

        if (!state.get(SUPPRESS_SNAP_ON_FALL)) {
            // Play snap sound when starting to fall
            world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,
                    SoundCategory.BLOCKS, 1.25F, 0.5F + world.getRandom().nextFloat() * 0.1F
            );

            world.playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_GHAST_SHOOT,
                    SoundCategory.BLOCKS, 1F, 0.5F + world.getRandom().nextFloat() * 0.1F
            );

            // Only makes the fall sound once
            ((FallingBlockEntityAccessor)entity).setBlock(state.with(SUPPRESS_SNAP_ON_FALL, true));
        }
    }

    @Override
    public void onLanding(World world,
                          BlockPos pos,
                          BlockState fallingBlockState,
                          BlockState currentStateInPos,
                          FallingBlockEntity fallingBlockEntity
    ) {
        if (!world.isClient) {
            float impactDistance = 0.0F;

            if (fallingBlockEntity instanceof IFallingImpactData data) {
                impactDistance = data.btwr$getImpactFallDistance();
            }

            // BTW-style: needs to have fallen a bit before it can explode
            int effective = (int) Math.ceil(impactDistance - 5.0F);
            boolean hardFall = effective > 0;

            if (hardFall && !world.getFluidState(pos).isIn(FluidTags.WATER)) {
                int roll = world.random.nextInt(5);

                if (roll < effective) {
                    this.explodeOnLanding(world, pos);

                    // remove the block after the explosion
                    world.setBlockState(pos, Blocks.AIR.getDefaultState());
                    this.spawnParticles((ServerWorld) world, pos);
                    return;
                }
            }
        }

        super.onLanding(world, pos, fallingBlockState, currentStateInPos, fallingBlockEntity);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state,
                           @Nullable BlockEntity blockEntity, net.minecraft.item.ItemStack tool) {
        this.explode(world, pos);
        this.spawnParticles((ServerWorld) world, pos);

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {

        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
        if (!world.isClient) {
            this.explode(world, pos);
            this.spawnParticles((ServerWorld) world, pos);
        }
    }

    // Protected methods for subclass customization
    protected boolean shouldFall(BlockState state) {
        return true;
    }

    protected int getParticleCount() {
        return 5;
    }

    protected int getSoundChance() {
        return 24;
    }

    protected Block getCindersBlock() {
        return SturdyTreesBlocks.WOOD_CINDERS;
    }

    private boolean hasWaterToSidesOrTop(World world, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            if (direction != Direction.DOWN) {
                if (world.getFluidState(pos.offset(direction)).isIn(FluidTags.WATER)) {
                    return true;
                }
            }

        }
        return false;
    }

    private boolean checkForGoOutInRain(ServerWorld world, BlockPos pos) {
        if (world.getRandom().nextInt(CHANCE_OF_EXTINGUISH_IN_RAIN) == 0) {
            if (world.hasRain(pos.up())) {
                world.playSound(null, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F, 2.6F);
                convertToCinders(world, pos);
                return true;
            }
        }
        return false;
    }

    private void explode(World world, BlockPos pos) {
        this.createExplosion(world, pos);

        world.playSound(null, pos,
                SoundEvents.ENTITY_ZOMBIE_ATTACK_WOODEN_DOOR,
                SoundCategory.BLOCKS,
                1.25F,
                0.5F + world.random.nextFloat() * 0.1F
        );
    }

    private void explodeOnLanding(World world, BlockPos pos) {
        this.createExplosion(world, pos);

        world.playSound(null, pos,
                SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR,
                SoundCategory.BLOCKS,
                1.25F,
                0.5F + world.random.nextFloat() * 0.1F
        );
        world.playSound(null, pos,
                SoundEvents.ENTITY_GHAST_SHOOT,
                SoundCategory.BLOCKS,
                1.25F,
                0.5F + world.random.nextFloat() * 0.1F
        );

    }

    private void createExplosion(World world, BlockPos pos) {
        Explosion explosion = new Explosion(
                world,
                null,
                pos.getX() + 0.5,
                pos.getY() + 0.5,
                pos.getZ() + 0.5,
                EXPLOSION_STRENGTH,
                true,
                Explosion.DestructionType.KEEP
        );

        explosion.collectBlocksAndDamageEntities();
        explosion.affectWorld(true);
    }

    private void convertToCinders(World world, BlockPos pos) {
        world.setBlockState(pos, getCindersBlock().getDefaultState());
    }

    protected boolean isSupportedBySolidBlocks(World world, BlockPos pos) {
        Block blockBelow = world.getBlockState(pos.down()).getBlock();

        return blockBelow != null && blockBelow.btwr$hasLargeCenterHardPointToFacing(
                world, pos.down(), Direction.UP, false
        );
    }

    private void spawnParticles(ServerWorld world, BlockPos pos) {
        world.spawnParticles(ParticleTypes.EXPLOSION, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5,
                10, 0.2, 0.2, 0.2, 0.1
        );

        for (int i = 0; i < 20; i++) {
            double smokeX = pos.getX() + world.random.nextDouble() - 0.5D;
            double smokeY = pos.getY() + world.random.nextDouble() - 0.5D;
            double smokeZ = pos.getZ() + world.random.nextDouble() - 0.5D;

            double smokeVelX = (smokeX - pos.getX()) * 0.33D;
            double smokeVelY = (smokeY - pos.getY()) * 0.33D;
            double smokeVelZ = (smokeZ - pos.getZ()) * 0.33D;

            world.spawnParticles(ParticleTypes.SMOKE, smokeX, smokeY, smokeZ, 1, smokeVelX, smokeVelY, smokeVelZ, 2);
        }

        for(int i = 0; i < 8; i++) {
            world.addParticle(
                    SturdyTreesParticles.CINDERS,
                    MathHelper.floor(pos.getX()),
                    MathHelper.floor(pos.getY()),
                    MathHelper.floor(pos.getZ()),
                    0.0D,
                    0.0D,
                    0.0D
            );
        }
    }
}