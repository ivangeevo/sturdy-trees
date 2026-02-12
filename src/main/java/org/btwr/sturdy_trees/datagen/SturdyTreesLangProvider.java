package org.btwr.sturdy_trees.datagen;

import org.btwr.sturdy_trees.SturdyTreesMod;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.item.SturdyTreesItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class SturdyTreesLangProvider extends FabricLanguageProvider {

    public SturdyTreesLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder tb) {
        this.addBlockTranslations(tb);
        this.addItemTranslations(tb);
        this.addConfigTranslations(tb);

        // Needed to display the crafting container title in the crafting stump
        tb.add("container.sturdy_trees.crafting", "Crafting");
    }

    private void addBlockTranslations(TranslationBuilder tb) {
        tb.add(SturdyTreesBlocks.STUMP_OAK, "Oak Stump");
        tb.add(SturdyTreesBlocks.STUMP_SPRUCE, "Spruce Stump");
        tb.add(SturdyTreesBlocks.STUMP_BIRCH, "Birch Stump");
        tb.add(SturdyTreesBlocks.STUMP_JUNGLE, "Jungle Stump");
        tb.add(SturdyTreesBlocks.STUMP_ACACIA, "Acacia Stump");
        tb.add(SturdyTreesBlocks.STUMP_DARK_OAK, "Dark Oak Stump");
        tb.add(SturdyTreesBlocks.STUMP_MANGROVE, "Mangrove Stump");
        tb.add(SturdyTreesBlocks.STUMP_CHERRY, "Cherry Stump");

        tb.add(SturdyTreesBlocks.STUMP_OAK_CRAFTING, "Oak Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_SPRUCE_CRAFTING, "Spruce Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_BIRCH_CRAFTING, "Birch Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_JUNGLE_CRAFTING, "Jungle Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_ACACIA_CRAFTING, "Acacia Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_DARK_OAK_CRAFTING, "Dark Oak Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_MANGROVE_CRAFTING, "Mangrove Crafting Stump");
        tb.add(SturdyTreesBlocks.STUMP_CHERRY_CRAFTING, "Cherry Crafting Stump");

        tb.add(SturdyTreesBlocks.LOG_OAK_SPIKE, "Oak Spike Log");
        tb.add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE, "Spruce Spike Log");
        tb.add(SturdyTreesBlocks.LOG_BIRCH_SPIKE, "Birch Spike Log");
        tb.add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE, "Jungle Spike Log");
        tb.add(SturdyTreesBlocks.LOG_ACACIA_SPIKE, "Acacia Spike Log");
        tb.add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE, "Dark Oak Spike Log");
        tb.add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE, "Mangrove Spike Log");
        tb.add(SturdyTreesBlocks.LOG_CHERRY_SPIKE, "Cherry Spike Log");

        tb.add(SturdyTreesBlocks.LOG_OAK_CHEWED, "Oak Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_SPRUCE_CHEWED, "Spruce Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_BIRCH_CHEWED, "Birch Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_JUNGLE_CHEWED, "Jungle Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_ACACIA_CHEWED, "Acacia Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_DARK_OAK_CHEWED, "Dark Oak Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_MANGROVE_CHEWED, "Mangrove Chewed Log");
        tb.add(SturdyTreesBlocks.LOG_CHERRY_CHEWED, "Cherry Chewed Log");

        tb.add(SturdyTreesBlocks.LOG_OAK_STRIPPED, "Oak Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED, "Spruce Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED, "Birch Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED, "Jungle Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED, "Acacia Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED, "Dark Oak Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED, "Mangrove Stripped Log");
        tb.add(SturdyTreesBlocks.LOG_CHERRY_STRIPPED, "Cherry Stripped Log");
    }

    private void addItemTranslations(TranslationBuilder tb) {
        tb.add(SturdyTreesItems.DUST_SAW, "Saw Dust");

        tb.add(SturdyTreesItems.BARK_OAK, "Oak Bark");
        tb.add(SturdyTreesItems.BARK_SPRUCE, "Spruce Bark");
        tb.add(SturdyTreesItems.BARK_JUNGLE, "Jungle Bark");
        tb.add(SturdyTreesItems.BARK_BIRCH, "Birch Bark");
        tb.add(SturdyTreesItems.BARK_ACACIA, "Acacia Bark");
        tb.add(SturdyTreesItems.BARK_DARK_OAK, "Dark Oak Bark");
        tb.add(SturdyTreesItems.BARK_MANGROVE, "Mangrove Bark");
        tb.add(SturdyTreesItems.BARK_CHERRY, "Cherry Bark");
        tb.add(SturdyTreesItems.STUMP_REMOVER, "Stump Remover");
    }

    private void addConfigTranslations(TranslationBuilder tb) {
        this.addConfigMenuDefaults(tb);
        addConfigMenuTitle("Sturdy Trees Configuration Menu", tb);
        addConfigCategory("general", "General Options", tb);
        addConfig("isSaplingsFertilizable", "Fertilizable Saplings", tb);
        addConfigTooltip("isSaplingsFertilizable", "Toggles whether saplings can be fertilized with bone meal", tb);
    }

    private void addConfigMenuDefaults(TranslationBuilder tb) {
        this.addSimpleText("clientSettingsText", "Client Settings:", tb);
        this.addSimpleText("emptyClientConfigText", "§eNote:§r There are currently no client config settings.", tb);
        this.addSimpleText("serverSettingsText", "Server Settings:", tb);
        this.addSimpleText("serverSettingsNoAccessText", "§eNote:§r Server settings are not accessible in menus." +
                "\nThey can only be changed by editing the config file manually and require a world reload to take effect.", tb
        );
    }

    private void addItemGroup(String entryPath, String translation, TranslationBuilder tb) {
        tb.add("itemgroup." + entryPath, translation);
    }

    private void addConfigMenuTitle(String translation, TranslationBuilder tb) {
        tb.add("title." + SturdyTreesMod.MOD_ID + ".config", translation);
    }

    private void addConfigCategory(String categoryPath, String translation, TranslationBuilder tb) {
        tb.add("config." + SturdyTreesMod.MOD_ID + ".category." + categoryPath, translation);
    }

    private void addSimpleText(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + SturdyTreesMod.MOD_ID + ".text." + configPath, translation);
    }

    private void addConfig(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + SturdyTreesMod.MOD_ID + "." + configPath, translation);
    }

    private void addConfigTooltip(String configPath, String translation, TranslationBuilder tb) {
        tb.add("config." + SturdyTreesMod.MOD_ID + ".tooltip." + configPath, translation);
    }

}