package ivangeevo.sturdy_trees.block;

import com.sun.source.tree.Tree;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeBreakManager
{
    private static final TreeBreakManager instance = new TreeBreakManager();

    // Private constructor to prevent instantiation
    private TreeBreakManager() {}
    public static TreeBreakManager getInstance()
    {
        return instance;
    }

    public void setStateForLog(World world, BlockPos pos, BlockState state, ItemStack tool)
    {

            if (state.isOf(Blocks.OAK_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_OAK_STRIPPED);
            } else if (state.isOf(Blocks.BIRCH_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_BIRCH_STRIPPED);
            } else if (state.isOf(Blocks.SPRUCE_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_SPRUCE_STRIPPED);
            } else if (state.isOf(Blocks.JUNGLE_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_JUNGLE_STRIPPED);
            } else if (state.isOf(Blocks.ACACIA_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_ACACIA_STRIPPED);
            } else if (state.isOf(Blocks.DARK_OAK_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED);
            } else if (state.isOf(Blocks.MANGROVE_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_MANGROVE_STRIPPED);
            } else if (state.isOf(Blocks.CHERRY_LOG)) {
                handleLogBreak(world, pos, state, tool, SturdyTreesBlocks.LOG_CHERRY_STRIPPED);
            }

    }

    private void handleLogBreak(World world, BlockPos pos, BlockState state, ItemStack tool, Block... logVariants) {
        boolean isFullyBreakingAxe = tool.isIn(BTWRConventionalTags.Items.MODERN_AXES)
                || tool.isIn(BTWRConventionalTags.Items.ADVANCED_AXES);

        if (world instanceof ServerWorld)
        {

            if (isFullyBreakingAxe)
            {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
            else
            {

                Block strippedLog = logVariants[0];
                world.setBlockState(pos, strippedLog.getDefaultState());

                for (int i = 0; i < logVariants.length - 1; i++)
                {
                    if (state.isOf(logVariants[i]))
                    {
                        world.setBlockState(pos, logVariants[i + 1].getDefaultState());
                        break;
                    }

                }
            }
        }

    }
}
