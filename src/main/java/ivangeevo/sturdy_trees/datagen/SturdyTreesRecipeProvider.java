package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Consumer;

public class SturdyTreesRecipeProvider extends FabricRecipeProvider
{


    public SturdyTreesRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter)
    {
        this.addToModRecipes(exporter);
    }

    private void addToModRecipes(Consumer<RecipeJsonProvider> exporter)
    {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.OAK_PLANKS).input('#', Items.OAK_SLAB).pattern("#").pattern("#").criterion("has_oak_planks", RecipeProvider.conditionsFromItem(Items.OAK_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.BIRCH_PLANKS).input('#', Items.BIRCH_SLAB).pattern("#").pattern("#").criterion("has_birch_planks", RecipeProvider.conditionsFromItem(Items.BIRCH_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.SPRUCE_PLANKS).input('#', Items.SPRUCE_SLAB).pattern("#").pattern("#").criterion("has_spruce_planks", RecipeProvider.conditionsFromItem(Items.SPRUCE_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.JUNGLE_PLANKS).input('#', Items.JUNGLE_SLAB).pattern("#").pattern("#").criterion("has_jungle_planks", RecipeProvider.conditionsFromItem(Items.JUNGLE_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.ACACIA_PLANKS).input('#', Items.ACACIA_SLAB).pattern("#").pattern("#").criterion("has_acacia_planks", RecipeProvider.conditionsFromItem(Items.ACACIA_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.DARK_OAK_PLANKS).input('#', Items.DARK_OAK_SLAB).pattern("#").pattern("#").criterion("has_dark_oak_planks", RecipeProvider.conditionsFromItem(Items.DARK_OAK_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.MANGROVE_PLANKS).input('#', Items.MANGROVE_SLAB).pattern("#").pattern("#").criterion("has_mangrove_planks", RecipeProvider.conditionsFromItem(Items.MANGROVE_SLAB)).offerTo(exporter);
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Items.CHERRY_PLANKS).input('#', Items.CHERRY_SLAB).pattern("#").pattern("#").criterion("has_cherry_planks", RecipeProvider.conditionsFromItem(Items.CHERRY_SLAB)).offerTo(exporter);

    }


    // Helper methods
    public static void offerTwoInputShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount)
                .input(input).input(input2)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, convertBetween(output, input));
    }

    public static void offerThreeInputShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2, ItemConvertible input3, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount)
                .input(input).input(input2).input(input3)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, convertBetween(output, input));
    }

    public static void offerFourInputShapelessRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, ItemConvertible input2, ItemConvertible input3, ItemConvertible input4, @Nullable String group, int outputCount) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, output, outputCount)
                .input(input).input(input2).input(input3).input(input4)
                .group(group)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, convertBetween(output, input));
    }
}