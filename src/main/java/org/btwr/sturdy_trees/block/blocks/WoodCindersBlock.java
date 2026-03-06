package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.block.enums.LogCondition;
import org.btwr.sturdy_trees.block.enums.LogConditionProperty;
import org.btwr.sturdy_trees.block.interfaces.IConvertingTreeBlock;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import org.jetbrains.annotations.Nullable;

public class WoodCindersBlock extends FallingBlock implements IConvertingTreeBlock  {

    public static final MapCodec<WoodCindersBlock> CODEC = createCodec(WoodCindersBlock::new);

    public static final IntProperty BREAK_LEVEL = IntProperty.of("break_level", 0, 3);
    public static final LogConditionProperty CONDITION = LogConditionProperty.of("condition");

    private static final int CHANCE_OF_DISSOLVE_IN_RAIN = 5;

    public WoodCindersBlock(Settings settings) {
        super(settings.sounds(BlockSoundGroup.GRAVEL));
        this.setDefaultState(this.getDefaultState().with(BREAK_LEVEL, 0).with(CONDITION, LogCondition.NORMAL));
    }

    @Override
    protected MapCodec<? extends FallingBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<net.minecraft.block.Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BREAK_LEVEL, CONDITION);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int breakLevel = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        int level;

        if (condition == LogCondition.NORMAL) {
            // Always reduced, no break scaling
            level = -1;
        } else if (condition == LogCondition.STRIPPED && breakLevel == 0) {
            // Full shape
            level = 0;
        } else {
            // All other non-normal cases scale normally
            level = breakLevel;
        }

        double offset = (this.getOutlineOffset() + level) / 16.0;
        double to = 1.0 - offset;

        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!world.isClient) {
            this.convertBlock(world, pos, state, player);
        }
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return world.setBlockState(pos, this.getStateForBreak(world, pos, state));
    }

    @Override
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                1.25F + (player.getWorld().random.nextFloat() * 0.25F)
        );
    }

    @Override
    public IntProperty getBreakLevel() {
        return BREAK_LEVEL;
    }

    @Override
    public int getOutlineOffset() {
        return 1;
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        // Only fall if it should fall (stumps override this)
        if (shouldFall(state)) {
            super.scheduledTick(state, world, pos, random);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(CHANCE_OF_DISSOLVE_IN_RAIN) == 0) {
            if (shouldDissolveInRain(state) && world.hasRain(pos.up())) {
                world.removeBlock(pos, false);
            }
        }
    }

    @Override
    public void onLanding(World world, BlockPos pos,
                          BlockState fallingState,
                          BlockState currentState,
                          FallingBlockEntity entity) {

        if (!world.isClient) {

            // Remove the block that vanilla just placed
            world.removeBlock(pos, false);

            if (!AshGroundCoverBlock.attemptToPlaceAshAt(world, pos) &&
                    !AshGroundCoverBlock.attemptToPlaceAshAt(world, pos.up())) {

                for (int i = 0; i < 16; i++) {
                    BlockPos randomPos = pos.add(
                            world.random.nextInt(7) - 3,
                            world.random.nextInt(5) - 2,
                            world.random.nextInt(7) - 3
                    );

                    if (AshGroundCoverBlock.attemptToPlaceAshAt(world, randomPos)) {
                        break;
                    }
                }
            }

            entity.discard();
        }
    }

    private BlockState getStateForBreak(World world, BlockPos pos, BlockState state) {
        int level = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        // Already spike or chewed; just increment
        if (condition == LogCondition.SPIKE || condition == LogCondition.CHEWED) {
            return incrementOrDestroy(state);
        }

        // Not yet stripped; convert first
        if (condition != LogCondition.STRIPPED) {
            return state.with(CONDITION, LogCondition.STRIPPED);
        }

        // Already stripped; apply neighbor logic
        LogCondition newCondition = resolveConditionFromNeighbors(world, pos);
        return state.with(CONDITION, newCondition).with(BREAK_LEVEL, level + 1);
    }

    private BlockState incrementOrDestroy(BlockState state) {
        int level = state.get(BREAK_LEVEL);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        return state.with(BREAK_LEVEL, level + 1);
    }

    // Protected methods for subclass customization
    protected boolean shouldFall(BlockState state) {
        return true; // Regular cinders can fall
    }

    protected boolean shouldDissolveInRain(BlockState state) {
        return true; // Regular cinders dissolve in rain
    }

}