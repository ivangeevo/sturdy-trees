package org.btwr.sturdy_trees;

import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.config.SturdyTreesConfig;
import org.btwr.sturdy_trees.item.SturdyTreesItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyTreesMod implements ModInitializer {

    public static final String MOD_ID = "sturdy_trees";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Sturdy Trees");

        SturdyTreesConfig.register();
        SturdyTreesBlocks.registerModBlocks();
        SturdyTreesItems.registerModItems();
        TreeBreakHandler.registerBreakEvent();
    }

}