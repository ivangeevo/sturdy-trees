package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class LogChewedBlock extends ConvertingLogBlock {

    public static final IntProperty CHEWED_VARIATION = IntProperty.of("variation", 0, 2);

    public LogChewedBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(CHEWED_VARIATION);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int var = state.get(CHEWED_VARIATION);
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
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(CHEWED_VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }

    @Override
    public int getOutlineOffset() {
        return 2;
    }

    @Override
    public IntProperty getBreakLevel() {
        return CHEWED_VARIATION;
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(CHEWED_VARIATION);
        if (breakLevel >= 2) {
            return false;
        }

        world.setBlockState(pos, getStateWithProperties(state.with(CHEWED_VARIATION, breakLevel + 1)));
        return true;
    }

}