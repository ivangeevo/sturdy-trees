package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LogSpikeBlock extends ConvertingLogBlock
{
    private static final EnumProperty<Direction> FACING = Properties.FACING;

    public LogSpikeBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(FACING);
    }
    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation)
    {
        return LogSpikeBlock.changeRotation(state, rotation);
    }

    public static BlockState changeRotation(BlockState state, BlockRotation rotation)
    {
        if ( !(state.getBlock() instanceof LogSpikeBlock) )
        {
            return null;
        }

        if (rotation == BlockRotation.CLOCKWISE_180)
        {
            return state.with(FACING, Direction.UP);
        }
        return state.with(FACING, Direction.DOWN);
    }



    @Override
    public void onBlockAdded(BlockState state, World world, BlockPos pos, BlockState oldState, boolean notify)
    {

        if (!world.isClient)
        {
            world.setBlockState(pos, state.with(FACING, getDirection(world, pos)));
        }
    }

    private static Direction getDirection(World world, BlockPos pos)
    {
        BlockState belowState = world.getBlockState(pos.down());
        BlockState aboveState = world.getBlockState(pos.up());

        if ( (belowState.getBlock() == Blocks.AIR) && (!(aboveState.getBlock() == Blocks.AIR)) )
        {
            return Direction.DOWN;
        }

        return Direction.UP;

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        VoxelShape shape = VoxelShapes.empty();
        int varInt = getShapeForState(state);

        // Define dimensions for each element
        for (int i = varInt; i <= 2; i++)
        {
            // Calculate 'from' coordinates dynamically
            double fromX = (3 - i) / 16.0;
            double fromY = 0.0;
            double fromZ = (3 - i) / 16.0;

            // Calculate 'to' coordinates dynamically
            double toX = (13 + i) / 16.0;
            double toY = (16 - i) / 16.0;
            double toZ = (13 + i) / 16.0;

            // Create VoxelShape for each element and add to the main shape
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ));
        }

        return shape;
    }

    private int getShapeForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 1; }
        else if (state.get(VARIATION) == 2) { return 2; }

        return 0;
    }




}