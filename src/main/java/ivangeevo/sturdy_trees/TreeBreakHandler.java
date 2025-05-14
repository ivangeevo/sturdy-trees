package ivangeevo.sturdy_trees;

import btwr.btwr_sl.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;

public class TreeBreakHandler {

    public static void registerBreakEvent() {
        PlayerBlockBreakEvents.AFTER.register(TreeBreakHandler::onBlockDestroyed);
    }

    private static void onBlockDestroyed(World world, PlayerEntity player, BlockPos pos, BlockState state, /* Nullable */ BlockEntity blockEntity) {
        Block strippedLog = logToStrippedLogMap.get(state.getBlock());
        if (strippedLog != null) {
            handleLogBreak(world, pos, state, player, strippedLog);
        }
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

    private static void handleLogBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, Block strippedLog) {
        boolean isFullyBreakingAxe = player.getWeaponStack().isIn(BTWRConventionalTags.Items.AXES_HARVEST_FULL_BLOCK);

        if (!(world instanceof ServerWorld)) {
            return;
        }

        if (isFullyBreakingAxe || player.isCreative()) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            world.setBlockState(pos, strippedLog.getStateWithProperties(
                    strippedLog.getDefaultState()
                            .with(Properties.AXIS, state.get(Properties.AXIS)))
            );
        }
    }



}
