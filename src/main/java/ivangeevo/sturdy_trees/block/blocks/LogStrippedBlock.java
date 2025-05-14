package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

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

        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());
        BlockState blockNorthState = world.getBlockState(pos.north());
        BlockState blockSouthState = world.getBlockState(pos.south());
        BlockState blockEastState = world.getBlockState(pos.east());
        BlockState blockWestState = world.getBlockState(pos.west());


        // Assign the appropriate block variations based on the log type
        if (currentState.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_OAK_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_OAK_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_BIRCH_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_BIRCH_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_BIRCH_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_SPRUCE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_SPRUCE_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_JUNGLE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_JUNGLE_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_ACACIA_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_ACACIA_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_ACACIA_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_DARK_OAK_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_MANGROVE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_MANGROVE_SPIKE;
        } else if (currentState.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_CHERRY_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_CHERRY_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_CHERRY_SPIKE;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();
        boolean hasBlockNorth = !blockNorthState.isAir();
        boolean hasBlockSouth = !blockSouthState.isAir();
        boolean hasBlockEast = !blockEastState.isAir();
        boolean hasBlockWest = !blockWestState.isAir();

        /**
        if (currentState.get(AXIS) == Direction.Axis.Z) {
            // Default and neighboring replacement logic
            if (hasBlockNorth && hasBlockSouth) {
                return chewedVar != null ? chewedVar.getStateWithProperties(currentState) : currentState;
            } else if (hasBlockNorth) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.SOUTH) : currentState;
            } else if (hasBlockSouth) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.NORTH) : currentState;
            } else {
                // If the variation is 3 for stripped, break to air
                if (currentState.get(VARIATION) == 3) {
                    return Blocks.AIR.getDefaultState();
                }
                // Default, choose the next stripped variation
                int level = currentState.get(VARIATION);
                return strippedVar != null ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4)) : currentState;
            }

        } else if (currentState.get(AXIS) == Direction.Axis.X) {
            // Default and neighboring replacement logic
            if (hasBlockEast && hasBlockWest) {
                return chewedVar != null ? chewedVar.getStateWithProperties(currentState) : currentState;
            } else if (hasBlockEast) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.WEST) : currentState;
            } else if (hasBlockWest) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.EAST) : currentState;
            } else {
                // If the variation is 3 for stripped, break to air
                if (currentState.get(VARIATION) == 3) {
                    return Blocks.AIR.getDefaultState();
                }
                // Default, choose the next stripped variation
                int level = currentState.get(VARIATION);
                return strippedVar != null ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4)) : currentState;
            }

        } else {
            // Default and neighboring replacement logic
            if (hasBlockAbove && hasBlockBelow) {
                return chewedVar != null ? chewedVar.getStateWithProperties(currentState) : currentState;
            } else if (hasBlockAbove) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.DOWN) : currentState;
            } else if (hasBlockBelow) {
                return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, Direction.UP) : currentState;
            } else {
                // If the variation is 3 for stripped, break to air
                if (currentState.get(VARIATION) == 3) {
                    return Blocks.AIR.getDefaultState();
                }
                // Default, choose the next stripped variation
                int level = currentState.get(VARIATION);
                return strippedVar != null ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4)) : currentState;
            }

        }
         **/

         // Determine neighbor presence based on axis
         Direction.Axis axis = currentState.get(AXIS);
         Direction dirPos, dirNeg;
         boolean hasPos, hasNeg;

         switch (axis) {
             case X -> {
                 dirPos = Direction.EAST;
                 dirNeg = Direction.WEST;
                 hasPos = !blockEastState.isAir();
                 hasNeg = !blockWestState.isAir();
             }
             case Z -> {
                 dirPos = Direction.NORTH;
                 dirNeg = Direction.SOUTH;
                 hasPos = !blockNorthState.isAir();
                 hasNeg = !blockSouthState.isAir();
             }
             default -> {
                 dirPos = Direction.UP;
                 dirNeg = Direction.DOWN;
                 hasPos = !blockBelowState.isAir();
                 hasNeg = !blockAboveState.isAir();
             }
         }

         // Main logic
         if (hasNeg && hasPos) {
             return chewedVar != null ? chewedVar.getStateWithProperties(currentState) : currentState;
         } else if (hasNeg) {
             return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, dirNeg) : currentState;
         } else if (hasPos) {
             return spikeVar != null ? spikeVar.getStateWithProperties(currentState).with(FACING, dirPos) : currentState;
         } else if (currentState.get(VARIATION) == 3) {
             return Blocks.AIR.getDefaultState();
         } else {
             int level = currentState.get(VARIATION);
             return strippedVar != null
                     ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4))
                     : currentState;
         }

    }

}