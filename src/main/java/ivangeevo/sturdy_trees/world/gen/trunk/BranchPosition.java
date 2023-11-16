package ivangeevo.sturdy_trees.world.gen.trunk;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.foliage.FoliagePlacer;

public class BranchPosition {
    public final FoliagePlacer.TreeNode node;

    private final int endY;

    public BranchPosition(BlockPos pos, int width) {
        this.node = new FoliagePlacer.TreeNode(pos, 0, false);
        this.endY = width;
    }

    public int getEndY() {
        return this.endY;
    }
}

