package ivangeevo.sturdy_trees.client;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class SturdyTreesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient()
    {

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_OAK_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_SPRUCE_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_BIRCH_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_JUNGLE_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_ACACIA_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_DARK_OAK_CHEWED, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_MANGROVE_CHEWED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN, RenderLayer.getCutout());

        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_CHEWED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_STRIPPED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN, RenderLayer.getCutout());



    }
}
