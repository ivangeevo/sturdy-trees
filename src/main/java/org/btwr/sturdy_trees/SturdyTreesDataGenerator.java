package org.btwr.sturdy_trees;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.btwr.sturdy_trees.datagen.*;

public class SturdyTreesDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(SturdyTreesBlockTagProvider::new);
        pack.addProvider(SturdyTreesItemTagProvider::new);
        pack.addProvider(SturdyTreesRecipeProvider::new);
        pack.addProvider(SturdyTreesLootTableProvider::new);
        pack.addProvider(SturdyTreesLangProvider::new);
    }

}
