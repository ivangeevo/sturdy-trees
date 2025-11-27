package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ConvertingLogBlock extends PillarBlock {

    public static final IntProperty VARIATION = IntProperty.of("variation", 0, 3);
    public static final BooleanProperty CHARRED = BooleanProperty.of("charred");

    public ConvertingLogBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(VARIATION, 0)
                .with(CHARRED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(VARIATION, CHARRED);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        this.tryConvert(world, pos, state, player);
        this.playSoundsOnBreak(world, pos, state, player);
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {}

    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {}

}