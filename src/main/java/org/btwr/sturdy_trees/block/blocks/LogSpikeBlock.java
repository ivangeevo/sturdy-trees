package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LogSpikeBlock extends ConvertingLogBlock {

    public static final IntProperty SPIKE_VARIATION = IntProperty.of("variation", 0, 2);
    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty CONNECTED = BooleanProperty.of("connected");

    public LogSpikeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager()
                .getDefaultState()
                .with(SPIKE_VARIATION, 0)
                .with(FACING, Direction.UP)
                .with(CONNECTED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(SPIKE_VARIATION, FACING, CONNECTED);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState,
                                                   WorldAccess world, BlockPos pos, BlockPos neighborPos)
    {
        Direction facing = state.get(FACING);

        if (direction == facing) {
            boolean connected = isTouchingSide(world, neighborPos, facing);
            return state.with(CONNECTED, connected);
        }

        return state;
    }

    @Override
    public int getOutlineOffset() {
        return 2;
    }

    @Override
    public IntProperty getBreakLevel() {
        return SPIKE_VARIATION;
    }

    @Override
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(SPIKE_VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(SPIKE_VARIATION);
        if (breakLevel >= 2) {
            return false;
        }

        world.setBlockState(pos, getStateWithProperties(state.with(SPIKE_VARIATION, breakLevel + 1)));
        return true;
    }

    private boolean isTouchingSide(BlockView world, BlockPos neighborPos, Direction fromDirection) {
        VoxelShape shape = world.getBlockState(neighborPos).getCollisionShape(world, neighborPos);
        if (shape.isEmpty()) return false;

        // We want to see if the neighbor shape reaches the side facing `fromDirection`.
        double minTouch = switch (fromDirection) {
            case DOWN -> shape.getMax(Direction.Axis.Y);
            case UP -> 1.0 - shape.getMin(Direction.Axis.Y);
            case NORTH -> shape.getMax(Direction.Axis.Z);
            case SOUTH -> 1.0 - shape.getMin(Direction.Axis.Z);
            case WEST -> shape.getMax(Direction.Axis.X);
            case EAST -> 1.0 - shape.getMin(Direction.Axis.X);
        };

        return minTouch >= 1.0;
    }

}