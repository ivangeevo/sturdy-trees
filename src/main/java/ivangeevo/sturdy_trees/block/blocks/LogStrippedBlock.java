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

import java.util.HashMap;
import java.util.Map;

import static ivangeevo.sturdy_trees.block.blocks.LogSpikeBlock.FACING;

public class LogStrippedBlock extends ConvertingLogBlock {

    private static final Map<Block, Block> logToStrippedLogMap = new HashMap<>();

    static {
        logToStrippedLogMap.put(SturdyTreesBlocks.LOG_OAK_STRIPPED, SturdyTreesBlocks.LOG_OAK_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.BIRCH_LOG, SturdyTreesBlocks.LOG_BIRCH_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.SPRUCE_LOG, SturdyTreesBlocks.LOG_SPRUCE_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.JUNGLE_LOG, SturdyTreesBlocks.LOG_JUNGLE_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.ACACIA_LOG, SturdyTreesBlocks.LOG_ACACIA_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.DARK_OAK_LOG, SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.MANGROVE_LOG, SturdyTreesBlocks.LOG_MANGROVE_STRIPPED);
        logToStrippedLogMap.put(SturdyTreesBlocks.CHERRY_LOG, SturdyTreesBlocks.LOG_CHERRY_STRIPPED);
    }

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

         // Determine neighbor presence based on axis
         Direction.Axis axis = currentState.get(AXIS);
         Direction dirPos, dirNeg;

         //boolean hasPos, hasNeg;

        BlockState statePos, stateNeg;

        switch (axis) {
            case X -> {
                dirPos = Direction.EAST;
                dirNeg = Direction.WEST;
                statePos = blockEastState;
                stateNeg = blockWestState;
            }
            case Z -> {
                dirPos = Direction.SOUTH;
                dirNeg = Direction.NORTH;
                statePos = blockSouthState;
                stateNeg = blockNorthState;
            }
            default -> {
                dirPos = Direction.UP;
                dirNeg = Direction.DOWN;
                statePos = blockAboveState;
                stateNeg = blockBelowState;
            }
        }

         /**
         switch (axis) {
             case X -> {
                 dirPos = Direction.EAST;
                 dirNeg = Direction.WEST;
                 hasPos = !blockWestState.isAir();
                 hasNeg = !blockEastState.isAir();
             }
             case Z -> {
                 dirPos = Direction.NORTH;
                 dirNeg = Direction.SOUTH;
                 hasPos = !blockSouthState.isAir();
                 hasNeg = !blockNorthState.isAir();
             }
             default -> {
                 dirPos = Direction.UP;
                 dirNeg = Direction.DOWN;
                 hasPos = !blockBelowState.isAir();
                 hasNeg = !blockAboveState.isAir();
             }
         }
          **/

        // Check whether solid, full, non-replaceable block exists
        boolean hasPos = isSolidBlockAtBase(world, pos.offset(dirPos), statePos);
        boolean hasNeg = isSolidBlockAtBase(world, pos.offset(dirNeg), stateNeg);


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

    // checks if the block against the currently replaced block is solid full block
    private boolean isSolidBlockAtBase(World world, BlockPos pos, BlockState base) {
      return base.isOpaqueFullCube(world, pos) && !base.isReplaceable();
    }

    // Only apply FACING if the block actually has that property
    private BlockState getSpikeState(Block spikeVar, BlockState currentState, Direction facing) {
        if (spikeVar instanceof LogSpikeBlock spikeBlock) {
            BlockState base = spikeVar.getStateWithProperties(currentState);
            if (base.contains(FACING)) {
                return base.with(FACING, facing);
            }
        }
        return currentState;
    }

}