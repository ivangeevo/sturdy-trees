package org.btwr.sturdy_trees.event;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.block.blocks.StumpBlock;
import org.btwr.sturdy_trees.block.enums.LogCondition;

import java.util.Map;

public class ModBlockBreakEvents {

    private static final Map<Block, Block> CRAFTING_TO_STUMP = Map.of(
            SturdyTreesBlocks.STUMP_OAK_CRAFTING, SturdyTreesBlocks.STUMP_OAK,
            SturdyTreesBlocks.STUMP_SPRUCE_CRAFTING, SturdyTreesBlocks.STUMP_SPRUCE,
            SturdyTreesBlocks.STUMP_BIRCH_CRAFTING, SturdyTreesBlocks.STUMP_BIRCH,
            SturdyTreesBlocks.STUMP_JUNGLE_CRAFTING, SturdyTreesBlocks.STUMP_JUNGLE,
            SturdyTreesBlocks.STUMP_ACACIA_CRAFTING, SturdyTreesBlocks.STUMP_ACACIA,
            SturdyTreesBlocks.STUMP_DARK_OAK_CRAFTING, SturdyTreesBlocks.STUMP_DARK_OAK,
            SturdyTreesBlocks.STUMP_MANGROVE_CRAFTING, SturdyTreesBlocks.STUMP_MANGROVE,
            SturdyTreesBlocks.STUMP_CHERRY_CRAFTING, SturdyTreesBlocks.STUMP_CHERRY
    );

    public static void register() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state, blockEntity) -> {

            if (world.isClient) return true;

            Block stumpBlock = CRAFTING_TO_STUMP.get(state.getBlock());

            if (stumpBlock != null) {

                BlockState newState = stumpBlock.getDefaultState()
                        .with(StumpBlock.CONDITION, LogCondition.STRIPPED)
                        .with(StumpBlock.BREAK_LEVEL, 0);

                world.setBlockState(pos, newState);

                return false; // cancel normal break
            }

            return true;
        });
    }

}