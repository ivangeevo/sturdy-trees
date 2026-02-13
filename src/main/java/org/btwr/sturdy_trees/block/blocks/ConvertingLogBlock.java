package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ConvertingLogBlock extends PillarBlock implements IConvertingTreeBlock {

    // Charring functionality not added fully yet, so we exclude mentions in code for now
    public static final BooleanProperty CHARRED = BooleanProperty.of("charred");

    public ConvertingLogBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                //.with(CHARRED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(/**, CHARRED**/);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int breakLevel = state.get(this.getBreakLevel());
        Direction.Axis axis = state.get(Properties.AXIS);

        double offset = (this.getOutlineOffset() + breakLevel) / 16.0;
        double to = 1.0 - offset;

        VoxelShape shape = VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);

        return switch (axis) {
            case X -> rotateYtoX(shape);
            case Z -> rotateYtoZ(shape);
            default -> shape;
        };
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (this.convertBlock(world, pos, state, player)) {
            this.playSoundsOnBreak(world, pos, state, player);
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

}