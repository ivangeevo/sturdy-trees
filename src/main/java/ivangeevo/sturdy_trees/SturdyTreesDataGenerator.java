package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.datagen.SturdyTreesBlockTagProvider;
import ivangeevo.sturdy_trees.datagen.SturdyTreesItemTagProvider;
import ivangeevo.sturdy_trees.datagen.SturdyTreesRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class SturdyTreesDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        fabricDataGenerator.addProvider(SturdyTreesBlockTagProvider::new);
        fabricDataGenerator.addProvider(SturdyTreesItemTagProvider::new);
        fabricDataGenerator.addProvider(SturdyTreesRecipeProvider::new);
    }

}
