package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LogSpikeBlock extends ConvertingLogBlock {

    public static final DirectionProperty FACING = Properties.FACING;

    public LogSpikeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(VARIATION);
        if (breakLevel >= 2) { return; }
        world.setBlockState(pos, getStateWithProperties(state.with(VARIATION, breakLevel + 1)));
        super.tryConvert(world, pos, state, player);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // TODO: FIX OUTLINE FOR THE SPIKE LOG
        int var = state.get(VARIATION);
        double offset = (2 + var) / 16.0;
        double to = 1.0 - offset;
        return VoxelShapes.cuboid(offset, 0, offset, to, 1.0f, to);
    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }
}