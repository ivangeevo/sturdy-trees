package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.CherryTrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.function.BiConsumer;

@Mixin(CherryTrunkPlacer.class)
public abstract class CherryTrunkPlacerMixin extends TrunkPlacer
{
    public CherryTrunkPlacerMixin(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Inject(method = "generate", at = @At("TAIL"))
    private void modifyGenerateMethodArgument(
            TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height,
            BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> cir)
    {
        BlockState stumpBlock = SturdyTreesBlocks.STUMP_CHERRY.getDefaultState();

        // Place the stump block
        replacer.accept(startPos, stumpBlock);

    }
}
