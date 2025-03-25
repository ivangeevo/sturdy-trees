package ivangeevo.sturdy_trees;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SturdyTreesMod implements ModInitializer {

    public static final String MOD_ID = "sturdy_trees";
    public static final Logger LOGGER = LoggerFactory.getLogger("sturdy_trees");

    @Override
    public void onInitialize() {
        SturdyTreesBlocks.registerModBlocks();
        SturdyTreesItems.registerModItems();
    }

}
