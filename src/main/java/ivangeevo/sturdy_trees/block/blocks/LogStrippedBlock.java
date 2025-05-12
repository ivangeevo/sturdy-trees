package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
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
        double offset = (1 + var) / 16.0;
        double to = 1.0 - offset;

        // Create a VoxelShape based on the dimensions
        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());

        // Logic to determine the block to replace with
        BlockState newState = getReplacementState(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, newState);


        super.tryConvert(world, pos, state, player);


    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        switch (state.get(VARIATION)) {
            case 0, 2: this.playSpecialBreakSound(world, pos, player);
        }
    }

    /**
    private BlockState getReplacementState(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        int level = state.get(BREAK_LEVEL);
        LogBlockState logState = state.get(LOG_STATE);

        if (logState == LogBlockState.STRIPPED) {
            if (level == 3) {
                return Blocks.AIR.getDefaultState();
            }
        } else {
            if (level == 2) {
                return Blocks.AIR.getDefaultState();
            }
        }

        if (hasBlockAbove && hasBlockBelow) {
            return state.with(LOG_STATE, LogBlockState.CHEWED);
        } else if (hasBlockAbove || hasBlockBelow) {
            return state.with(LOG_STATE, LogBlockState.SPIKE);
        } else {
            return state.with(BREAK_LEVEL, (level + 1) % 4);
        }

    }
     **/

    private BlockState getReplacementState(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        Block strippedVar = null;
        Block chewedVar = null;
        Block spikeVar = null;

        // Assign the appropriate block variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_OAK_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_OAK_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_BIRCH_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_BIRCH_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_BIRCH_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_SPRUCE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_SPRUCE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_JUNGLE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_JUNGLE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_ACACIA_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_ACACIA_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_ACACIA_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_DARK_OAK_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_MANGROVE_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_MANGROVE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_CHERRY_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_CHERRY_CHEWED;
            spikeVar = SturdyTreesBlocks.LOG_CHERRY_SPIKE;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            return chewedVar != null ? chewedVar.getDefaultState() : state;
        } else if (hasBlockAbove) {
            return spikeVar != null ? spikeVar.getDefaultState().with(FACING, Direction.DOWN) : state;
        } else if (hasBlockBelow) {
            return spikeVar != null ? spikeVar.getDefaultState().with(FACING, Direction.UP) : state;
        } else {
            // If the variation is 3 for stripped, break to air
            if (state.get(VARIATION) == 3) {
                return Blocks.AIR.getDefaultState();
            }
            // Default, choose the next stripped variation
            int level = state.get(VARIATION);
            return strippedVar != null ? strippedVar.getStateWithProperties(state.with(VARIATION, (level + 1) % 4)) : state;
        }
    }



}