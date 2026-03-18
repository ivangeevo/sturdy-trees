package org.btwr.sturdy_trees.block.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;

public class SturdyTreesBlockRenderLayers {

    public static void register() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getCutout(),
                SturdyTreesBlocks.LOG_OAK_STRIPPED,
                SturdyTreesBlocks.LOG_OAK_SPIKE,
                SturdyTreesBlocks.LOG_OAK_CHEWED,

                SturdyTreesBlocks.LOG_SPRUCE_STRIPPED,
                SturdyTreesBlocks.LOG_SPRUCE_SPIKE,
                SturdyTreesBlocks.LOG_SPRUCE_CHEWED,

                SturdyTreesBlocks.LOG_BIRCH_STRIPPED,
                SturdyTreesBlocks.LOG_BIRCH_SPIKE,
                SturdyTreesBlocks.LOG_BIRCH_CHEWED,

                SturdyTreesBlocks.LOG_JUNGLE_STRIPPED,
                SturdyTreesBlocks.LOG_JUNGLE_SPIKE,
                SturdyTreesBlocks.LOG_JUNGLE_CHEWED,

                SturdyTreesBlocks.LOG_ACACIA_STRIPPED,
                SturdyTreesBlocks.LOG_ACACIA_SPIKE,
                SturdyTreesBlocks.LOG_ACACIA_CHEWED,

                SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED,
                SturdyTreesBlocks.LOG_DARK_OAK_SPIKE,
                SturdyTreesBlocks.LOG_DARK_OAK_CHEWED,

                SturdyTreesBlocks.LOG_MANGROVE_STRIPPED,
                SturdyTreesBlocks.LOG_MANGROVE_SPIKE,
                SturdyTreesBlocks.LOG_MANGROVE_CHEWED,

                SturdyTreesBlocks.LOG_CHERRY_STRIPPED,
                SturdyTreesBlocks.LOG_CHERRY_SPIKE,
                SturdyTreesBlocks.LOG_CHERRY_CHEWED,

                SturdyTreesBlocks.LOG_SMOULDERING,
                SturdyTreesBlocks.STUMP_SMOULDERING,

                SturdyTreesBlocks.WOOD_CINDERS,
                SturdyTreesBlocks.WOOD_CINDERS_STUMP,

                SturdyTreesBlocks.ASH_GROUND_COVER,

                SturdyTreesBlocks.OAK_SAPLING_SMALL,
                SturdyTreesBlocks.SPRUCE_SAPLING_SMALL,
                SturdyTreesBlocks.BIRCH_SAPLING_SMALL,
                SturdyTreesBlocks.JUNGLE_SAPLING_SMALL,
                SturdyTreesBlocks.ACACIA_SAPLING_SMALL,
                SturdyTreesBlocks.DARK_OAK_SAPLING_SMALL,
                SturdyTreesBlocks.CHERRY_SAPLING_SMALL

        );

    }
}
