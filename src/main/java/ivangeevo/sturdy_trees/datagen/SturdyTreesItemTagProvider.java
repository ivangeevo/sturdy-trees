package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import org.jetbrains.annotations.Nullable;

public class SturdyTreesItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public SturdyTreesItemTagProvider(FabricDataGenerator dataGenerator, @Nullable BlockTagProvider blockTagProvider) {
        super(dataGenerator, blockTagProvider);
    }

    @Override
    protected void generateTags() {
    }

}
