package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LogChewedBlock extends LogStrippedBlock {

    public LogChewedBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }

    @Override
    protected int getOutlineOffset() {
        return 2;
    }

    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(VARIATION);
        if (breakLevel >= 2) { return; }
        world.setBlockState(pos, getStateWithProperties(state.with(VARIATION, breakLevel + 1)));
    }

}