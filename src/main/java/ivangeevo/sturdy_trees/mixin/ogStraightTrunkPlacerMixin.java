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
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(StraightTrunkPlacer.class)
public abstract class ogStraightTrunkPlacerMixin extends TrunkPlacer {
    public ogStraightTrunkPlacerMixin(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    private void cancelGetAndSetState(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> ci) {
        BlockStateProvider trunkProvider = config.trunkProvider;

        for (int i = 0; i < height; ++i) {
            if (i == 0) {
                // Determine stump block based on log type
                BlockState logBlockState = getLogBlockState(trunkProvider, random, startPos);
                Block blockStump = getStumpBlockForLog(logBlockState.getBlock());
                BlockState blockStumpState = blockStump.getDefaultState();
                replacer.accept(startPos.up(i), blockStumpState);
            } else {
                // Generate LOG block for the rest of the blocks in the trunk
                BlockState logBlockState = getLogBlockState(trunkProvider, random, startPos);
                replacer.accept(startPos.up(i), logBlockState);
            }
        }
        ci.setReturnValue(ImmutableList.of(new FoliagePlacer.TreeNode(startPos.up(height), 0, false)));
    }

    private BlockState getLogBlockState(BlockStateProvider provider, Random random, BlockPos pos) {
        // Replace this with the correct way to retrieve the log block state from your trunkProvider
        return provider.getBlockState(random, pos);
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
}
