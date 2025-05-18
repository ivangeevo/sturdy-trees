package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;

import static ivangeevo.sturdy_trees.block.blocks.LogSpikeBlock.FACING;

public class LogStrippedBlock extends ConvertingLogBlock {

    public LogStrippedBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int var = state.get(VARIATION);
        Direction.Axis axis = state.get(Properties.AXIS);

        double offset = (this.getOutlineOffset() + var) / 16.0;
        double to = 1.0 - offset;

        VoxelShape shape = VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);

        return switch (axis) {
            case X -> rotateYtoX(shape);
            case Z -> rotateYtoZ(shape);
            default -> shape;
        };
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);

        if (!world.isClient && state.get(VARIATION) == 0) {
            Block thisBlock = state.getBlock();
            Identifier id = Registries.BLOCK.getId(thisBlock);
            String[] parts = id.getPath().split("_");

            if (parts.length >= 3 && parts[0].equals("log")) {
                String woodType = parts[1]; // e.g., spruce

                Identifier chewedId = Identifier.of(id.getNamespace(), "log_" + woodType + "_chewed");
                Block chewedBlock = Registries.BLOCK.get(chewedId);

                Identifier strippedId = Identifier.of(id.getNamespace(), "log_" + woodType + "_stripped");
                Block strippedBlock = Registries.BLOCK.get(strippedId);

                Identifier spikeId = Identifier.of(id.getNamespace(), "log_" + woodType + "_spike");
                Block spikeBlock = Registries.BLOCK.get(spikeId);

                if (spikeBlock != Blocks.AIR) {
                    for (Direction dir : Direction.values()) {
                        BlockPos otherPos = pos.offset(dir);
                        BlockState otherState = world.getBlockState(otherPos);

                        if (otherState.getBlock() == chewedBlock) {
                            int variation = otherState.getOrEmpty(VARIATION).orElse(-1);
                            if (variation >= 0 && variation <= 2) {
                                // Build states preserving properties
                                BlockState newSelfState = spikeBlock.getStateWithProperties(state)
                                        .with(Properties.FACING, dir)
                                        .with(VARIATION, 0); // this block always becomes variation 0

                                BlockState newOtherState = spikeBlock.getStateWithProperties(otherState)
                                        .with(Properties.FACING, dir.getOpposite() )
                                        .with(VARIATION, variation); // preserve variation of chewed block

                                // Replace the chewed block
                                world.setBlockState(otherPos, newOtherState, Block.NOTIFY_ALL);

                                return newSelfState;
                            }
                        }

                        if (otherState.getBlock() == strippedBlock) {
                            int variation = otherState.getOrEmpty(VARIATION).orElse(-1);
                            if (variation >= 0 && variation <= 3) {
                                // Build states preserving properties
                                BlockState newSelfState = spikeBlock.getStateWithProperties(state)
                                        .with(Properties.FACING, dir)
                                        .with(VARIATION, 0); // this block always becomes variation 0

                                BlockState newOtherState = strippedBlock.getStateWithProperties(otherState);

                                // Replace the stripped block
                                world.setBlockState(otherPos, newOtherState, Block.NOTIFY_ALL);

                                return newSelfState;
                            }
                        }
                    }
                }
            }
        }

        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }

        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));
        return state;
    }


    /**
     * Sets the amount to offset the outline by (outline shape)
     **/
    protected int getOutlineOffset() {
        return 1;
    }

    protected static VoxelShape rotateYtoX(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and X
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minY, minX, minZ, maxY, maxX, maxZ));
        });
        return buffer[0];
    }

    protected static VoxelShape rotateYtoZ(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and Z
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minX, minZ, minY, maxX, maxZ, maxY));
        });
        return buffer[0];
    }


    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // Logic to determine the block to replace with
        BlockState newState = getReplacementState(world, pos, state);
        world.setBlockState(pos, newState);
    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        switch (state.get(VARIATION)) {
            case 0, 2:
                this.playSpecialBreakSound(world, pos, player);
        }
    }

    protected void playSpecialBreakSound(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, this.getSpecialBreakSound(), SoundCategory.BLOCKS, 0.1F,
                1.25F + (player.getWorld().random.nextFloat() * 0.25F));
    }

    protected SoundEvent getSpecialBreakSound() {
        return SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR;
    }

    private BlockState getReplacementState(World world, BlockPos pos, BlockState currentState) {
        Block strippedVar = null;
        Block chewedVar = null;
        Block spikeVar = null;

        // Define blocks to change
        Identifier id = Registries.BLOCK.getId(currentState.getBlock());

        if (id.getPath().endsWith("_stripped")) {
            String base = id.getPath().replace("_stripped", "");
            strippedVar = currentState.getBlock(); // already stripped
            chewedVar = Registries.BLOCK.get(Identifier.of(id.getNamespace(), base + "_chewed"));
            spikeVar = Registries.BLOCK.get(Identifier.of(id.getNamespace(), base + "_spike"));
        }

        // Determine directions based on axis
        Direction.Axis axis = currentState.get(AXIS);
        Direction dirPos, dirNeg;
        BlockState statePos, stateNeg;

        switch (axis) {
            case X -> {
                dirPos = Direction.EAST;
                dirNeg = Direction.WEST;
                statePos = world.getBlockState(pos.west());
                stateNeg = world.getBlockState(pos.east());
            }
            case Z -> {
                dirPos = Direction.NORTH;
                dirNeg = Direction.SOUTH;
                statePos = world.getBlockState(pos.south());
                stateNeg = world.getBlockState(pos.north());
            }
            default -> {
                dirPos = Direction.UP;
                dirNeg = Direction.DOWN;
                statePos = world.getBlockState(pos.down());
                stateNeg = world.getBlockState(pos.up());
            }
        }

        // Check whether solid, full, non-replaceable block exists
        boolean hasPos = isSolidBlockAtBase(world, pos.offset(dirPos), statePos);
        boolean hasNeg = isSolidBlockAtBase(world, pos.offset(dirNeg), stateNeg);

        // Apply logic
        if (hasNeg && hasPos) {
            return chewedVar != null ? chewedVar.getStateWithProperties(currentState) : currentState;
        } else if (hasNeg) {
            return getSpikeState(spikeVar, currentState, dirNeg);
        } else if (hasPos) {
            return getSpikeState(spikeVar, currentState, dirPos);
        } else if (currentState.get(VARIATION) == 3) {
            return Blocks.AIR.getDefaultState();
        } else {
            int level = currentState.get(VARIATION);
            return strippedVar != null
                    ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4))
                    : currentState;
        }
    }

    // Only apply FACING if the block actually has that property
    private BlockState getSpikeState(Block spikeVar, BlockState currentState, Direction facing) {
        if (spikeVar instanceof LogSpikeBlock) {
            BlockState base = spikeVar.getStateWithProperties(currentState);
            if (base.contains(FACING)) {
                return base.with(FACING, facing);
            }
        }
        return currentState;
    }

    // checks if the block against the currently replaced block is solid full block
    private boolean isSolidBlockAtBase(World world, BlockPos pos, BlockState base) {
      return base.isOpaqueFullCube(world, pos) && !base.isReplaceable();
    }

}