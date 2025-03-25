package ivangeevo.sturdy_trees.block;

import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class TreeBreakManager
{
    private static final TreeBreakManager instance = new TreeBreakManager();

    // Private constructor to prevent instantiation
    private TreeBreakManager() {}

    public static TreeBreakManager getInstance()
    {
        return instance;
    }

    private static final Map<Block, Block> logToStrippedLogMap = new HashMap<>();

    static {
        logToStrippedLogMap.put(Blocks.OAK_LOG, SturdyTreesBlocks.LOG_OAK_STRIPPED);
        logToStrippedLogMap.put(Blocks.BIRCH_LOG, SturdyTreesBlocks.LOG_BIRCH_STRIPPED);
        logToStrippedLogMap.put(Blocks.SPRUCE_LOG, SturdyTreesBlocks.LOG_SPRUCE_STRIPPED);
        logToStrippedLogMap.put(Blocks.JUNGLE_LOG, SturdyTreesBlocks.LOG_JUNGLE_STRIPPED);
        logToStrippedLogMap.put(Blocks.ACACIA_LOG, SturdyTreesBlocks.LOG_ACACIA_STRIPPED);
        logToStrippedLogMap.put(Blocks.DARK_OAK_LOG, SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED);
        logToStrippedLogMap.put(Blocks.MANGROVE_LOG, SturdyTreesBlocks.LOG_MANGROVE_STRIPPED);
        logToStrippedLogMap.put(Blocks.CHERRY_LOG, SturdyTreesBlocks.LOG_CHERRY_STRIPPED);
    }

    public void setStateForLog(World world, BlockPos pos, BlockState state, ItemStack tool)
    {
        Block strippedLog = logToStrippedLogMap.get(state.getBlock());
        if (strippedLog != null) {
            handleLogBreak(world, pos, tool, strippedLog);
        }
    }

    private void handleLogBreak(World world, BlockPos pos, ItemStack tool, Block strippedLog)
    {
        boolean isFullyBreakingAxe = tool.isIn(BTWRConventionalTags.Items.AXES_HARVEST_FULL_BLOCK);

        if (!(world instanceof ServerWorld)) {
            return;
        }

        if (isFullyBreakingAxe) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            world.setBlockState(pos, strippedLog.getDefaultState());
        }
    }

}
