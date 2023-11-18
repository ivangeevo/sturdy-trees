package ivangeevo.sturdy_trees.mixin;

import com.google.common.collect.ImmutableList;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(StraightTrunkPlacer.class)
public abstract class StraightTrunkPlacerMixin extends TrunkPlacer {
    public StraightTrunkPlacerMixin(int i, int j, int k) {
        super(i, j, k);
    }

    // Helper method to determine stump block based on log type
    private Block getStumpBlockForLog(Block logBlock) {
        // Customize this logic based on your block registry and log-stump mappings
        if (logBlock == Blocks.OAK_LOG) {
            return SturdyTreesBlocks.STUMP_OAK;
        } else if (logBlock == Blocks.BIRCH_LOG) {
            return SturdyTreesBlocks.STUMP_BIRCH;
        } else if (logBlock == Blocks.SPRUCE_LOG) {
            return SturdyTreesBlocks.STUMP_SPRUCE;
        } else if (logBlock == Blocks.JUNGLE_LOG) {
            return SturdyTreesBlocks.STUMP_JUNGLE;
        }
        return Blocks.AIR;
    }

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    private void injectedGenerate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> cir) {
        StraightTrunkPlacer.setToDirt(world, replacer, random, startPos.down(), config);
        for (int i = 0; i < height; ++i) {
            this.getAndSetState(world, replacer, random, startPos.up(i), config);
        }
        Block logBlock = config.trunkProvider.getBlockState(random, startPos).getBlock();
        Block stumpBlock = getStumpBlockForLog(logBlock);


        replacer.accept(startPos, stumpBlock.getDefaultState());
        cir.setReturnValue(ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false)));
    }
}
