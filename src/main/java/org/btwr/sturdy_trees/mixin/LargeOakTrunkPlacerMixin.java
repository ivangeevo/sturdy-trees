package org.btwr.sturdy_trees.mixin;

import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(LargeOakTrunkPlacer.class)
public abstract class LargeOakTrunkPlacerMixin extends TrunkPlacer  {

    public LargeOakTrunkPlacerMixin(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Inject(method = "generate", at = @At("TAIL"))
    private void modifyGenerateMethodArgument(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> cir) {
        Block logBlock = config.trunkProvider.get(random, startPos).getBlock();

        if (logBlock.getDefaultState().isIn(SturdyTreesTags.Blocks.LARGE_OAK_TRUNK_TREES)) {
            // Place the stump block
            replacer.accept(startPos, SturdyTreesBlocks.STUMP_OAK.getDefaultState());
        }
    }

}