package org.btwr.sturdy_trees.datagen;

import org.btwr.sturdy_trees.item.SturdyTreesItems;
import org.btwr.sturdy_trees.SturdyTreesMod;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Property;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.btwr.sturdy_trees.block.blocks.LogStrippedBlock.VARIATION;

public class SturdyTreesLootTableProvider extends FabricBlockLootTableProvider {

    public SturdyTreesLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    // tough wood types require an axe to break fully
    private static final String[] overworldToughWoodTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };


    @Override
    public void generate() {
        // Loot tables for stripped log type blocks (when wood block is broken partially)
        this.generateCustomLogsTables();
    }

    private void generateCustomLogsTables() {
        // Stripped logs
        for (Identifier logBlock : getStrippedLogsIDs()) {
            Block block = Registries.BLOCK.get(logBlock);
            Item planksItem = getPlanksForLog(logBlock);
            addDrop(block, dropsForStrippedLog(block, planksItem));
        }

        // Chewed logs
        for (Identifier logBlock : getChewedLogsIDs()) {
            Block block = Registries.BLOCK.get(logBlock);
            addDrop(block, dropsForChewedLog(block));
        }

        // Spike logs
        for (Identifier logBlock : getSpikeLogsIDs()) {
            Block block = Registries.BLOCK.get(logBlock);
            addDrop(block, dropsForSpikeLog(block));
        }
    }

    /**
     * Generates a loot table for a stripped log with a given planks item.
     */
    public LootTable.Builder dropsForStrippedLog(Block logBlock, Item planksItem) {
        // Create the loot entries
        LeafEntry.Builder<?> planksEntry = conditionalEntry(planksItem, doIntStateCheck(logBlock, VARIATION, 0));
        LeafEntry.Builder<?> sawDustEntry1 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 1));
        LeafEntry.Builder<?> stickEntry = conditionalEntry(Items.STICK, doIntStateCheck(logBlock, VARIATION, 2));
        LeafEntry.Builder<?> sawDustEntry2 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 3));

        // Build and return the loot table
        return LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(planksEntry)
                        .with(sawDustEntry1)
                        .with(stickEntry)
                        .with(sawDustEntry2)
        );
    }

    /**
     * Generates a loot table for chewed logs.
     */
    public LootTable.Builder dropsForChewedLog(Block logBlock) {
        LeafEntry.Builder<?> sawDustEntry1 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 0));
        LeafEntry.Builder<?> stickEntry = conditionalEntry(Items.STICK, doIntStateCheck(logBlock, VARIATION, 1));
        LeafEntry.Builder<?> sawDustEntry2 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 2));

        return LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(sawDustEntry1)
                        .with(stickEntry)
                        .with(sawDustEntry2)
        );
    }

    /**
     * Generates a loot table for spike logs.
     */
    public LootTable.Builder dropsForSpikeLog(Block logBlock) {
        LeafEntry.Builder<?> sawDustEntry1 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 0));
        LeafEntry.Builder<?> stickEntry = conditionalEntry(Items.STICK, doIntStateCheck(logBlock, VARIATION, 1));
        LeafEntry.Builder<?> sawDustEntry2 = conditionalEntry(SturdyTreesItems.DUST_SAW, doIntStateCheck(logBlock, VARIATION, 2));

        return LootTable.builder().pool(
                LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f))
                        .with(sawDustEntry1)
                        .with(stickEntry)
                        .with(sawDustEntry2)
        );
    }

    /**
     * Maps a log identifier to its corresponding planks item.
     */
    private Item getPlanksForLog(Identifier logBlockId) {
        String woodType = extractWoodType(logBlockId.getPath()); // Extract the wood type from the identifier path
        return switch (woodType) {
            case "oak" -> Items.OAK_PLANKS;
            case "birch" -> Items.BIRCH_PLANKS;
            case "spruce" -> Items.SPRUCE_PLANKS;
            case "jungle" -> Items.JUNGLE_PLANKS;
            case "acacia" -> Items.ACACIA_PLANKS;
            case "dark_oak" -> Items.DARK_OAK_PLANKS;
            case "mangrove" -> Items.MANGROVE_PLANKS;
            case "cherry" -> Items.CHERRY_PLANKS;
            default -> throw new IllegalArgumentException("Unknown wood type: " + woodType);
        };
    }

    private String extractWoodType(String logBlockPath) {
        if (logBlockPath.startsWith("log_")) {
            logBlockPath = logBlockPath.substring(4); // Remove "log_"
        }
        int suffixIndex = logBlockPath.lastIndexOf('_');
        if (suffixIndex != -1) {
            logBlockPath = logBlockPath.substring(0, suffixIndex); // Remove the last suffix
        }
        return logBlockPath;
    }


    private LeafEntry.Builder<?> conditionalEntry(ItemConvertible drop, LootCondition.Builder condition) {
        return simpleEntry(drop).conditionally(condition);
    }

    private LeafEntry.Builder<?> simpleEntry(ItemConvertible drop) {
        return ItemEntry.builder(drop);
    }

    private BlockStatePropertyLootCondition.Builder doIntStateCheck(ItemConvertible block, Property<Integer> property, int value) {
        return BlockStatePropertyLootCondition.builder((Block) block).properties(
                StatePredicate.Builder.create().exactMatch(property, value));
    }

    private BlockStatePropertyLootCondition.Builder doBooleanStateCheck(Block block, Property<Boolean> property, boolean value) {
        return BlockStatePropertyLootCondition.builder(block).properties(
                StatePredicate.Builder.create().exactMatch(property, value));
    }

    /**
     * Creates a list of ItemEntry.Builder from a list of item identifiers.
     *
     * @param identifiers List of item identifiers
     * @return List of ItemEntry.Builder
     */
    public static List<ItemEntry.Builder> createItemEntryBuilders(List<Identifier> identifiers) {
        List<ItemEntry.Builder> builders = new ArrayList<>();

        for (Identifier id : identifiers) {
            Item item = Registries.ITEM.get(id); // Resolve the item from the identifier
            builders.add(ItemEntry.builder(item)); // Create an ItemEntry.Builder and add it to the list
        }

        return builders;
    }

    private static List<Identifier> getStrippedLogsIDs() {
        List<Identifier> strippedLogs = new ArrayList<>();
        for (String woodType : overworldToughWoodTypes) {
            strippedLogs.add(Identifier.of(SturdyTreesMod.MOD_ID, "log_" + woodType + "_stripped"));
        }

        return strippedLogs;
    }

    private static List<Identifier> getSpikeLogsIDs() {
        List<Identifier> list = new ArrayList<>();

        for (String woodType : overworldToughWoodTypes) {
            list.add(Identifier.of(SturdyTreesMod.MOD_ID, "log_" + woodType + "_spike"));
        }

        return list;
    }

    private static List<Identifier> getChewedLogsIDs() {
        List<Identifier> list = new ArrayList<>();

        for (String woodType : overworldToughWoodTypes) {
            list.add(Identifier.of(SturdyTreesMod.MOD_ID, "log_" + woodType + "_chewed"));
        }

        return list;
    }

}