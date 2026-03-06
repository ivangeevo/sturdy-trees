package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

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
}