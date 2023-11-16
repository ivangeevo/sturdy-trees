package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.client.SturdyTreesClient;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyTrees implements ModInitializer {

    public static final String MOD_ID = "sturdy_trees";


    public static final Logger LOGGER = LoggerFactory.getLogger("btwr");

    @Override
    public void onInitialize() {
        SturdyTreesBlocks.registerModBlocks();
        SturdyTreesItems.registerModItems();

    }
}
