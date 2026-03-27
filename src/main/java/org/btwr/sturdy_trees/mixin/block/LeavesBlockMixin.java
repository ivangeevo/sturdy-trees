package org.btwr.sturdy_trees.mixin.block;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.block.blocks.AshGroundCoverBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block {

    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean btwr$hasCustomFireDestructionBehavior() {
        return true;
    }

    @Override
    public void btwr$onDestroyedByFire(World world, BlockPos pos, int fireAge, boolean forcedFireSpread) {
        super.btwr$onDestroyedByFire(world, pos, fireAge, forcedFireSpread);
        this.generateAshOnBurn(world, pos);
    }

    @Unique
    protected void generateAshOnBurn(World world, BlockPos pos) {
        int i = pos.getX();
        int j = pos.getY();
        int k = pos.getZ();

        BlockPos.Mutable mutablePos = new BlockPos.Mutable();

        for (int iTempJ = j; iTempJ > world.getBottomY(); iTempJ--) {
            mutablePos.set(i, iTempJ, k);
            BlockState state = world.getBlockState(mutablePos);

            if (AshGroundCoverBlock.canAshReplaceBlock(world, mutablePos)) {
                mutablePos.set(i, iTempJ - 1, k);
                BlockState belowState = world.getBlockState(mutablePos);
                Block blockBelow = belowState.getBlock();

                if (blockBelow != null && blockBelow.btwr$canGroundCoverRestOnBlock(world, mutablePos)) {
                    mutablePos.set(i, iTempJ, k);
                    world.setBlockState(mutablePos, SturdyTreesBlocks.ASH_GROUND_COVER.getDefaultState(), Block.NOTIFY_ALL);

                    break;
                }
            }
            else if (!(state.getBlock() instanceof AbstractFireBlock)) {
                break;
            }
        }
    }
}
