package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.block.enums.LogCondition;
import org.jetbrains.annotations.Nullable;

public class WoodCindersStumpBlock extends WoodCindersBlock {

    public static final MapCodec<WoodCindersStumpBlock> CODEC = createCodec(WoodCindersStumpBlock::new);

    public WoodCindersStumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends WoodCindersBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean shouldFall(BlockState state) {
        return false; // Stump cinders don't fall
    }

    @Override
    protected float calcBlockBreakingDelta(BlockState state, PlayerEntity player, BlockView world, BlockPos pos) {
        float delta = super.calcBlockBreakingDelta(state, player, world, pos);
        if (state.get(BREAK_LEVEL) > 0) {
            return delta / 3; // slower to break
        }

        return delta;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (!world.isClient) {
            this.convertBlock(world, pos, state, player);
        }
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return world.setBlockState(pos, this.getStateForBreak(world, pos, state));
    }

    private BlockState getStateForBreak(World world, BlockPos pos, BlockState state) {
        int level = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        // Already spike or chewed; just increment
        if (condition == LogCondition.SPIKE || condition == LogCondition.CHEWED) {
            return incrementOrDestroy(state);
        }

        // Not yet stripped; convert first
        if (condition != LogCondition.STRIPPED) {
            return state.with(CONDITION, LogCondition.STRIPPED);
        }

        // Already stripped; apply neighbor logic
        LogCondition newCondition = resolveConditionFromNeighbors(world, pos);
        return state.with(CONDITION, newCondition).with(BREAK_LEVEL, level + 1);
    }

    private BlockState incrementOrDestroy(BlockState state) {
        int level = state.get(BREAK_LEVEL);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        return state.with(BREAK_LEVEL, level + 1);
    }
}