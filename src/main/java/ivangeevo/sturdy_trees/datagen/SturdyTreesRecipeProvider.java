package ivangeevo.sturdy_trees.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SturdyTreesRecipeProvider extends FabricRecipeProvider
{


    public SturdyTreesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter)
    {
        this.addToModRecipes(exporter);
    }

    private void addToModRecipes(RecipeExporter exporter)
    {
        planksFromSlab(Items.OAK_PLANKS, Items.OAK_SLAB).offerTo(exporter);
        planksFromSlab(Items.BIRCH_PLANKS, Items.BIRCH_SLAB).offerTo(exporter);
        planksFromSlab(Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB).offerTo(exporter);
        planksFromSlab(Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB).offerTo(exporter);
        planksFromSlab(Items.ACACIA_PLANKS, Items.ACACIA_SLAB).offerTo(exporter);
        planksFromSlab(Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB).offerTo(exporter);
        planksFromSlab(Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB).offerTo(exporter);
        planksFromSlab(Items.CHERRY_PLANKS, Items.CHERRY_SLAB).offerTo(exporter);
    }

    private ShapedRecipeJsonBuilder planksFromSlab(Item planks, Item slab)
    {
        String criterionString = "has_" + slab.toString();
        return new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, planks,1)
                .input('#', slab)
                .pattern("#")
                .pattern("#")
                .criterion(criterionString, RecipeProvider.conditionsFromItem(slab));

    }
}