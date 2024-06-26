package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SturdyTreesItemTagProvider extends FabricTagProvider.ItemTagProvider
{
    public SturdyTreesItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {
        getOrCreateTagBuilder(SturdyTreesTags.Items.BARK_ITEMS)
                .add(SturdyTreesItems.BARK_OAK)
                .add(SturdyTreesItems.BARK_SPRUCE)
                .add(SturdyTreesItems.BARK_BIRCH)
                .add(SturdyTreesItems.BARK_JUNGLE)
                .add(SturdyTreesItems.BARK_ACACIA)
                .add(SturdyTreesItems.BARK_DARK_OAK)
                .add(SturdyTreesItems.BARK_MANGROVE)
                .add(SturdyTreesItems.BARK_CHERRY);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.MODERN_AXES)
                .add(Items.STONE_AXE)
                .add(Items.IRON_AXE)
                .add(Items.DIAMOND_AXE);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.ADVANCED_AXES)
                .add(Items.NETHERITE_AXE);

        getOrCreateTagBuilder(BTWRConventionalTags.Items.AXES_HARVEST_FULL_BLOCK)
                .add(Items.STONE_AXE)
                .addTag(BTWRConventionalTags.Items.MODERN_AXES)
                .addTag(BTWRConventionalTags.Items.ADVANCED_AXES);





    }
}
