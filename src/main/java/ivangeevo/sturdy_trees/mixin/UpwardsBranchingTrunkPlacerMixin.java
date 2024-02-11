package ivangeevo.sturdy_trees.mixin;

import com.google.common.collect.Lists;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;
import net.minecraft.world.gen.trunk.UpwardsBranchingTrunkPlacer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Mixin(UpwardsBranchingTrunkPlacer.class)
public abstract class UpwardsBranchingTrunkPlacerMixin extends TrunkPlacer {
    @Shadow @Final private IntProvider extraBranchLength;
    @Shadow @Final private IntProvider extraBranchSteps;
    @Shadow protected abstract void generateExtraBranch(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, TreeFeatureConfig config, List<FoliagePlacer.TreeNode> nodes, BlockPos.Mutable pos, int yOffset, Direction direction, int length, int steps);
    @Shadow @Final private float placeBranchPerLogProbability;

    public UpwardsBranchingTrunkPlacerMixin(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    private void injectedGenerate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int height, BlockPos startPos, TreeFeatureConfig config, CallbackInfoReturnable<List<FoliagePlacer.TreeNode>> cir) {
        ArrayList<FoliagePlacer.TreeNode> list = Lists.newArrayList();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int i = 0; i < height; ++i) {
            int j = startPos.getY() + i;
            if (this.getAndSetState(world, replacer, random, mutable.set(startPos.getX(), j, startPos.getZ()), config) && i < height - 1 && random.nextFloat() < this.placeBranchPerLogProbability) {
                Direction direction = Direction.Type.HORIZONTAL.random(random);
                int k = this.extraBranchLength.get(random);
                int l = Math.max(0, k - this.extraBranchLength.get(random) - 1);
                int m = this.extraBranchSteps.get(random);
                this.generateExtraBranch(world, replacer, random, height, config, list, mutable, j, direction, l, m);
            }
            if (i != height - 1) continue;
            list.add(new FoliagePlacer.TreeNode(mutable.set(startPos.getX(), j + 1, startPos.getZ()), 0, false));
        }
        replacer.accept(startPos, SturdyTreesBlocks.STUMP_MANGROVE.getDefaultState());
        cir.setReturnValue(list);
    }
}
