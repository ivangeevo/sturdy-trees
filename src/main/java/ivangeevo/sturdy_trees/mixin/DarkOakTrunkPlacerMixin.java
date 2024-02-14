package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.DarkOakTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(DarkOakTrunkPlacer.class)
public abstract class DarkOakTrunkPlacerMixin extends TrunkPlacer {
    public DarkOakTrunkPlacerMixin(int i, int j, int k) {
        super(i, j, k);
    }

    @Inject(
            method = "generate",
            at = @At(value = "TAIL")
    )
    private void modifyTrunkBlocks(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> cir) {
        Block stumpBlock;
        BlockState trunkState = config.trunkProvider.get(random, startPos);
        if (trunkState.isOf(Blocks.DARK_OAK_LOG)) {
            stumpBlock = SturdyTreesBlocks.STUMP_DARK_OAK;
        } else {
            stumpBlock = SturdyTreesBlocks.STUMP_OAK;
        }

        if (stumpBlock != null) {
            int stumpWidth = 2; // Width of the stump (2x2)

            // Generate the stump at the bottom of the tree
            for (int i = 0; i < stumpWidth; i++) {
                for (int j = 0; j < stumpWidth; j++) {
                    BlockPos stumpPos = startPos.add(i, 0, j);
                    replacer.accept(stumpPos, stumpBlock.getDefaultState());
                }
            }
        }
    }
}
