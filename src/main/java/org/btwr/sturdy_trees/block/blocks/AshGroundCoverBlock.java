package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.btwr.shared_library.api.block.blocks.GroundCoverBlock;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;

public class AshGroundCoverBlock extends GroundCoverBlock {

    public AshGroundCoverBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.hasRain(pos)) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public boolean btwr$getCanGrassGrowUnderBlock(World world, BlockPos pos, boolean grassOnHalfSlab) {
        return false;
    }

    @Override
    public boolean btwr$getCanBlockBeReplacedByFire(World world, BlockPos pos) {
        return true;
    }

    public static boolean canAshReplaceBlock(World world, BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();

        return block == null || state.isAir() || (block.btwr$isGroundCover() && block != SturdyTreesBlocks.ASH_GROUND_COVER);
    }

    public static boolean attemptToPlaceAshAt(World world, BlockPos pos) {
        if (AshGroundCoverBlock.canAshReplaceBlock(world, pos)) {
            BlockState stateBelow = world.getBlockState(pos.down());

            Block blockBelow = stateBelow.getBlock();

            if (blockBelow != null && blockBelow.btwr$canGroundCoverRestOnBlock(world, pos.down()) ) {
                world.setBlockState(pos, SturdyTreesBlocks.ASH_GROUND_COVER.getDefaultState());

                return true;
            }
        }

        return false;
    }

}
