package ivangeevo.sturdy_trees.datagen;

import btwr.btwr_sl.lib.recipe.CraftingWithToolShapelessRecipe;
import btwr.btwr_sl.lib.util.utils.RecipeProviderUtils;
import btwr.btwr_sl.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.SturdyTreesMod;
import ivangeevo.sturdy_trees.item.SturdyTreesItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SturdyTreesRecipeProvider extends FabricRecipeProvider implements RecipeProviderUtils {
    
    public SturdyTreesRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    // tough wood types require an axe to break fully
    private static final String[] overworldToughWoodTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };

    @Override
    public void generate(RecipeExporter exporter) {
        this.addToVanillaRecipes(exporter);
        this.addToModRecipes(exporter);
    }

    private void addToModRecipes(RecipeExporter exporter) {
        this.createSticksWithAxe(exporter);
        this.createPlanksWithAxe(exporter);
        this.createPlanksFromSlabs(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, SturdyTreesItems.STUMP_REMOVER)
                .input(Items.ROTTEN_FLESH)
                .input(Items.RED_MUSHROOM)
                .criterion("has_red_mushroom", conditionsFromItem(Items.RED_MUSHROOM))
                .offerTo(exporter);
    }

    private void addToVanillaRecipes(RecipeExporter exporter) {
        disableVanilla(exporter, "oak_planks");
        disableVanilla(exporter, "birch_planks");
        disableVanilla(exporter, "spruce_planks");
        disableVanilla(exporter, "jungle_planks");
        disableVanilla(exporter, "acacia_planks");
        disableVanilla(exporter, "dark_oak_planks");
        disableVanilla(exporter, "mangrove_planks");
        disableVanilla(exporter, "cherry_planks");
        disableVanilla(exporter, "bamboo_planks");
    }
    
    private void createPlanksWithAxe(RecipeExporter exporter) {
        planksWithAxe(Items.OAK_PLANKS, Items.OAK_LOG, Items.STRIPPED_OAK_LOG, SturdyTreesItems.BARK_OAK, exporter);
        planksWithAxe(Items.BIRCH_PLANKS, Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG, SturdyTreesItems.BARK_BIRCH, exporter);
        planksWithAxe(Items.SPRUCE_PLANKS, Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, SturdyTreesItems.BARK_SPRUCE, exporter);
        planksWithAxe(Items.JUNGLE_PLANKS, Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, SturdyTreesItems.BARK_JUNGLE, exporter);
        planksWithAxe(Items.ACACIA_PLANKS, Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, SturdyTreesItems.BARK_ACACIA, exporter);
        planksWithAxe(Items.DARK_OAK_PLANKS, Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, SturdyTreesItems.BARK_DARK_OAK, exporter);
        planksWithAxe(Items.MANGROVE_PLANKS, Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, SturdyTreesItems.BARK_MANGROVE, exporter);
        planksWithAxe(Items.CHERRY_PLANKS, Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, SturdyTreesItems.BARK_SPRUCE, exporter);
    }

    private void createSticksWithAxe(RecipeExporter exporter) {
        sticksWithAxes(Items.OAK_LOG, Items.STRIPPED_OAK_LOG, SturdyTreesItems.BARK_OAK, exporter);
        sticksWithAxes(Items.BIRCH_LOG, Items.STRIPPED_BIRCH_LOG, SturdyTreesItems.BARK_BIRCH, exporter);
        sticksWithAxes(Items.SPRUCE_LOG, Items.STRIPPED_SPRUCE_LOG, SturdyTreesItems.BARK_SPRUCE, exporter);
        sticksWithAxes(Items.JUNGLE_LOG, Items.STRIPPED_JUNGLE_LOG, SturdyTreesItems.BARK_JUNGLE, exporter);
        sticksWithAxes(Items.ACACIA_LOG, Items.STRIPPED_ACACIA_LOG, SturdyTreesItems.BARK_ACACIA, exporter);
        sticksWithAxes(Items.DARK_OAK_LOG, Items.STRIPPED_DARK_OAK_LOG, SturdyTreesItems.BARK_DARK_OAK, exporter);
        sticksWithAxes(Items.MANGROVE_LOG, Items.STRIPPED_MANGROVE_LOG, SturdyTreesItems.BARK_MANGROVE, exporter);
        sticksWithAxes(Items.CHERRY_LOG, Items.STRIPPED_CHERRY_LOG, SturdyTreesItems.BARK_CHERRY, exporter);
    }

    private void createPlanksFromSlabs(RecipeExporter exporter) {
        planksFromSlab(Items.OAK_PLANKS, Items.OAK_SLAB, exporter);
        planksFromSlab(Items.BIRCH_PLANKS, Items.BIRCH_SLAB, exporter);
        planksFromSlab(Items.SPRUCE_PLANKS, Items.SPRUCE_SLAB, exporter);
        planksFromSlab(Items.JUNGLE_PLANKS, Items.JUNGLE_SLAB, exporter);
        planksFromSlab(Items.ACACIA_PLANKS, Items.ACACIA_SLAB, exporter);
        planksFromSlab(Items.DARK_OAK_PLANKS, Items.DARK_OAK_SLAB, exporter);
        planksFromSlab(Items.MANGROVE_PLANKS, Items.MANGROVE_SLAB, exporter);
        planksFromSlab(Items.CHERRY_PLANKS, Items.CHERRY_SLAB, exporter);
    }

    private void sticksWithAxes(Item log, Item strippedLog, Item barkItem, RecipeExporter exporter) {
        String logType = Registries.ITEM.getId(log).getPath();

        // From log
        CraftingWithToolShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, Items.STICK, 2)
                .additionalDrop(barkItem)
                .additionalDrop(SturdyTreesItems.DUST_SAW)
                .withToolDamage()
                .input(Ingredient.fromTag(BTWRConventionalTags.Items.PRIMITIVE_AXES),1)
                .input(log)
                .criterion("has_" + logType, conditionsFromItem(log))
                .offerTo(exporter, Identifier.of(SturdyTreesMod.MOD_ID,"sticks_from_tool_crafting_from_" + logType));

        // From stripped log
        CraftingWithToolShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, Items.STICK, 2)
                .additionalDrop(SturdyTreesItems.DUST_SAW, 2)
                .withToolDamage()
                .input(Ingredient.fromTag(BTWRConventionalTags.Items.PRIMITIVE_AXES),1)
                .input(strippedLog)
                .criterion("has_" + strippedLog, conditionsFromItem(strippedLog))
                .offerTo(exporter, Identifier.of(SturdyTreesMod.MOD_ID,"sticks_from_tool_crafting_from_stripped_" + logType));
    }

    private void planksWithAxe(Item planks, Item log, Item strippedLog, Item bark, RecipeExporter exporter) {
        String logType = Registries.ITEM.getId(log).getPath();
        String planksType = Registries.ITEM.getId(planks).getPath();
        String strippedType = Registries.ITEM.getId(strippedLog).getPath();

        // From log
        CraftingWithToolShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, planks, 4)
                .additionalDrop(bark)
                .additionalDrop(SturdyTreesItems.DUST_SAW)
                .withToolDamage()
                .input(Ingredient.fromTag(BTWRConventionalTags.Items.AXES_MAKE_PLANKS),1)
                .input(log)
                .criterion("has_" + logType, conditionsFromItem(log))
                .offerTo(exporter, Identifier.of(SturdyTreesMod.MOD_ID, planksType + "_from_" + logType + "_tool_crafting"));

        // From stripped log
        CraftingWithToolShapelessRecipe.JsonBuilder.create(RecipeCategory.MISC, planks, 4)
                .additionalDrop(SturdyTreesItems.DUST_SAW, 2)
                .withToolDamage()
                .input(Ingredient.fromTag(BTWRConventionalTags.Items.AXES_MAKE_PLANKS),1)
                .input(strippedLog)
                .criterion("has_" + strippedType, conditionsFromItem(strippedLog))
                .offerTo(exporter, Identifier.of(SturdyTreesMod.MOD_ID, planksType + "_from_" + strippedType + "_tool_crafting"));
    }

    private void planksFromSlab(Item planks, Item slab, RecipeExporter exporter) {
        Identifier planksId = Registries.ITEM.getId(planks);
        String planksType = planksId.getPath();

        Identifier slabId = Registries.ITEM.getId(slab);
        String slabType = slabId.getPath();

        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, planks,1)
                .input('#', slab)
                .pattern("#")
                .pattern("#")
                .criterion("has_" + slabType, RecipeProvider.conditionsFromItem(slab))
                .offerTo(exporter, Identifier.of(SturdyTreesMod.MOD_ID, planksType + "_from_slabs"));
    }

}