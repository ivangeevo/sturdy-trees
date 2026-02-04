package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class LogSpikeBlock extends LogStrippedBlock {

    public static final DirectionProperty FACING = Properties.FACING;
    public static final BooleanProperty CONNECTED = BooleanProperty.of("connected");

    public LogSpikeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP).with(CONNECTED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING, CONNECTED);
    }

    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(VARIATION);
        if (breakLevel >= 2) {
            return;
        }
        world.setBlockState(pos, getStateWithProperties(state.with(VARIATION, breakLevel + 1)));
        //this.playSoundsOnBreak(world, pos, state, player);
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        Direction facing = state.get(FACING);
        // Connect a spike block to neighbours if the axis or the facing of the spike match the neighbour's respective orientation method
        if (direction == facing || direction.getAxis() == facing.getAxis()) {
            boolean connected = isTouchingSide(world, neighborPos, facing.getOpposite());
            return state.with(CONNECTED, connected);
        }
        return state;
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


    @Override
    protected int getOutlineOffset() {
        return 2;
    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }

}