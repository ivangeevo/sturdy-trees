package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class StumpBlock extends Block
{

    public StumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {


        if (tool.isIn(SturdyTreesTags.Items.MODERN_CHISELS))
        {
            if (state.isOf(SturdyTreesBlocks.STUMP_OAK)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_OAK_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_BIRCH)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_BIRCH_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_ACACIA)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_ACACIA_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_JUNGLE)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_JUNGLE_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_SPRUCE)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_SPRUCE_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_DARK_OAK)) {
                world.setBlockState(pos, SturdyTreesBlocks.STUMP_DARK_OAK_VAR1.getDefaultState());
            }

            if (state.isOf(SturdyTreesBlocks.STUMP_OAK_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_BIRCH_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_ACACIA_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_JUNGLE_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_SPRUCE_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.STUMP_DARK_OAK_VAR1)) {
                world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
            }

            super.afterBreak(world, player, pos, state, blockEntity, tool);
        }

    }

}
