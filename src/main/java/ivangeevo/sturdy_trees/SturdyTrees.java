package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.client.SturdyTreesClient;
import ivangeevo.sturdy_trees.datagen.SturdyTreesBlockTagProvider;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyTrees implements ModInitializer {

    public static final String MOD_ID = "sturdy_trees";


    public static final Logger LOGGER = LoggerFactory.getLogger("sturdy_trees");

    @Override
    public void onInitialize() {
        SturdyTreesBlocks.registerModBlocks();
        SturdyTreesItems.registerModItems();

    }
}
