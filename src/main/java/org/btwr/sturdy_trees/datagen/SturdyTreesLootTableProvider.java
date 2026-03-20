package org.btwr.sturdy_trees.datagen;

import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.condition.TableBonusLootCondition;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
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

    // tough wood types require an axe to break fully
    private static final String[] overworldToughWoodTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };

    private static final String[] smallSaplings = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "cherry"
    };

    private static final float[] LEAVES_STICK_DROP_CHANCE = new float[]{0.02F, 0.022222223F, 0.025F, 0.033333335F, 0.1F};

    private static final float[] JUNGLE_SAPLING_DROP_CHANCE = new float[]{0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F};

    public final LootCondition.Builder WITH_SILK_TOUCH_OR_SHEARS = WITH_SHEARS.or(this.createSilkTouchCondition());

    public SturdyTreesLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }



    @Override
    public void generate() {
        this.generateVanillaTables();

        // Stripped log type blocks (when wood block is broken partially)
        this.generateCustomLogsTables();
        // Small saplings
        this.generateSmallSaplingTables();
    }

    private void generateVanillaTables() {
        this.addDrop(Blocks.OAK_LEAVES, block -> this.oakLeavesDrops(block, SturdyTreesBlocks.OAK_SAPLING_SMALL, SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.SPRUCE_LEAVES, block -> this.leavesDrops(block, SturdyTreesBlocks.SPRUCE_SAPLING_SMALL, SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.BIRCH_LEAVES, block -> this.leavesDrops(block, SturdyTreesBlocks.BIRCH_SAPLING_SMALL, SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.JUNGLE_LEAVES, block -> this.leavesDrops(block, SturdyTreesBlocks.JUNGLE_SAPLING_SMALL, JUNGLE_SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.ACACIA_LEAVES, block -> this.leavesDrops(block, SturdyTreesBlocks.ACACIA_SAPLING_SMALL, SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.DARK_OAK_LEAVES, block -> this.oakLeavesDrops(block, SturdyTreesBlocks.DARK_OAK_SAPLING_SMALL, SAPLING_DROP_CHANCE));
        this.addDrop(Blocks.CHERRY_LEAVES, block -> this.leavesDrops(block, SturdyTreesBlocks.CHERRY_SAPLING_SMALL, SAPLING_DROP_CHANCE));
    }


    public LootTable.Builder leavesDrops(Block leaves, Block drop, float... chance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return dropsWithSilkTouchOrShears(
                leaves, this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(drop)).conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), chance)
                ))
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(WITH_SILK_TOUCH_OR_SHEARS.invert())
                                .with(
                                        this.applyExplosionDecay(leaves, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F))))
                                                .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), LEAVES_STICK_DROP_CHANCE)
                                                )));
    }

    public LootTable.Builder oakLeavesDrops(Block leaves, Block drop, float... chance) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return this.leavesDrops(leaves, drop, chance)
                .pool(
                        LootPool.builder()
                                .rolls(ConstantLootNumberProvider.create(1.0F))
                                .conditionally(WITH_SILK_TOUCH_OR_SHEARS.invert())
                                .with(
                                        this.addSurvivesExplosionCondition(leaves, ItemEntry.builder(Items.APPLE))
                                                .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F))
                                )
                );
    }

    public LootTable.Builder mangroveLeavesDrops(Block leaves) {
        RegistryWrapper.Impl<Enchantment> impl = this.registryLookup.getWrapperOrThrow(RegistryKeys.ENCHANTMENT);
        return dropsWithSilkTouchOrShears(
                leaves,
                this.applyExplosionDecay(
                                Blocks.MANGROVE_LEAVES, ItemEntry.builder(Items.STICK).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0F, 2.0F)))
                        )
                        .conditionally(TableBonusLootCondition.builder(impl.getOrThrow(Enchantments.FORTUNE), LEAVES_STICK_DROP_CHANCE))
        );
    }

    public LootTable.Builder dropsWithSilkTouchOrShears(Block drop, LootPoolEntry.Builder<?> child) {
        return drops(drop, WITH_SILK_TOUCH_OR_SHEARS, child);
    }

    public static LootTable.Builder dropsWithShears(ItemConvertible drop) {
        return LootTable.builder().pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0f)).conditionally(WITH_SHEARS).with(ItemEntry.builder(drop)));
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

    private void generateSmallSaplingTables() {
        for (Identifier id : getSmallSaplingIDs()) {
            Block block = Registries.BLOCK.get(id);
            addDrop(block, LootTable.builder().pool(
                    LootPool.builder()
                            .rolls(ConstantLootNumberProvider.create(1.0f))
                            .with(this.applyExplosionDecay(block, ItemEntry.builder(block.asItem())))
            ));
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

    private static List<Identifier> getSmallSaplingIDs() {
        List<Identifier> list = new ArrayList<>();

        for (String type : smallSaplings) {
            list.add(Identifier.of(SturdyTreesMod.MOD_ID, type + "_sapling_small"));
        }

        return list;
    }

}