package ivangeevo.sturdy_trees.registry;

import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.registry.FuelRegistry;

public class FuelItems {

    public static void registerFuelEntries()
    {
        FuelRegistry.INSTANCE.add(SturdyTreesItems.DUST_SAW, 200);
        FuelRegistry.INSTANCE.add(SturdyTreesTags.Items.BARK_ITEMS, 350);
    }
}
