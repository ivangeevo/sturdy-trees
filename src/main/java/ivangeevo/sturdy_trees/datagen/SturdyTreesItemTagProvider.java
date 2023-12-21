package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.tag.BlockTags;
import org.jetbrains.annotations.Nullable;

public class SturdyTreesItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public SturdyTreesItemTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {

        getOrCreateTagBuilder(SturdyTreesTags.Items.BARK_ITEMS)
                .add(SturdyTreesItems.BARK_OAK)
                .add(SturdyTreesItems.BARK_SPRUCE)
                .add(SturdyTreesItems.BARK_BIRCH)
                .add(SturdyTreesItems.BARK_JUNGLE)
                .add(SturdyTreesItems.BARK_ACACIA)
                .add(SturdyTreesItems.BARK_DARK_OAK)
                .add(SturdyTreesItems.BARK_MANGROVE);

    }

}
