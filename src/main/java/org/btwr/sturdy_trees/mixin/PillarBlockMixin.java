package org.btwr.sturdy_trees.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.PillarBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block {

    public PillarBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean btwr$hasCustomFireDestructionBehavior() {
        return true;
    }

    @Override
    public void btwr$onDestroyedByFire(World world, BlockPos pos, int fireAge, boolean forcedFireSpread) {
        PillarBlock self = (PillarBlock)(Object)this;

        if (self.getDefaultState().isIn(BlockTags.LOGS_THAT_BURN)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_SMOULDERING.getDefaultState());
        }
    }

}
