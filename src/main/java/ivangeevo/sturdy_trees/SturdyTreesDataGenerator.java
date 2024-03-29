package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.datagen.SturdyTreesBlockTagProvider;
import ivangeevo.sturdy_trees.datagen.SturdyTreesItemTagProvider;
import ivangeevo.sturdy_trees.datagen.SturdyTreesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SturdyTreesDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();


        pack.addProvider(SturdyTreesBlockTagProvider::new);
        pack.addProvider(SturdyTreesItemTagProvider::new);
        pack.addProvider(SturdyTreesRecipeProvider::new);
    }

}
