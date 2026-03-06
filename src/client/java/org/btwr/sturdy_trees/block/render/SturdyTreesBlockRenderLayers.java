package org.btwr.sturdy_trees.block.render;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;

public class SturdyTreesBlockRenderLayers {

    public static void register() {
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_SPIKE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SMOULDERING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.STUMP_SMOULDERING, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.WOOD_CINDERS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.WOOD_CINDERS_STUMP, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.ASH_GROUND_COVER, RenderLayer.getCutout());

    }
}
