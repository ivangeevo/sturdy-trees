package org.btwr.sturdy_trees.block;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.piston.PistonBehavior;
import org.btwr.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.btwr.sturdy_trees.block.blocks.*;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;

public class SturdyTreesBlocks {

    /** STUMP BLOCKS **/

    public static final Block STUMP_OAK_CRAFTING = registerBlockWithoutItem(
            "stump_oak_crafting", createStumpCrafting(MapColor.OAK_TAN)
    );
    public static final Block STUMP_SPRUCE_CRAFTING = registerBlockWithoutItem(
            "stump_spruce_crafting", createStumpCrafting(MapColor.SPRUCE_BROWN)
    );
    public static final Block STUMP_BIRCH_CRAFTING = registerBlockWithoutItem(
            "stump_birch_crafting", createStumpCrafting(MapColor.PALE_YELLOW)
    );
    public static final Block STUMP_JUNGLE_CRAFTING = registerBlockWithoutItem(
            "stump_jungle_crafting", createStumpCrafting(MapColor.DIRT_BROWN)
    );
    public static final Block STUMP_ACACIA_CRAFTING = registerBlockWithoutItem(
            "stump_acacia_crafting", createStumpCrafting(MapColor.ORANGE)
    );
    public static final Block STUMP_DARK_OAK_CRAFTING = registerBlockWithoutItem(
            "stump_dark_oak_crafting", createStumpCrafting(MapColor.BROWN)
    );
    public static final Block STUMP_MANGROVE_CRAFTING = registerBlockWithoutItem(
            "stump_mangrove_crafting", createStumpCrafting(MapColor.RED)
    );
    public static final Block STUMP_CHERRY_CRAFTING = registerBlockWithoutItem(
            "stump_cherry_crafting", createStumpCrafting(MapColor.TERRACOTTA_WHITE)
    );

    public static final Block STUMP_OAK = registerBlockWithoutItem(
            "stump_oak", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_OAK_CRAFTING)
    );
    public static final Block STUMP_SPRUCE = registerBlockWithoutItem(
            "stump_spruce", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_SPRUCE_CRAFTING)
    );
    public static final Block STUMP_BIRCH = registerBlockWithoutItem(
            "stump_birch", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_BIRCH_CRAFTING)
    );
    public static final Block STUMP_JUNGLE = registerBlockWithoutItem(
            "stump_jungle", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_JUNGLE_CRAFTING)
    );
    public static final Block STUMP_ACACIA = registerBlockWithoutItem(
            "stump_acacia", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_ACACIA_CRAFTING)
    );
    public static final Block STUMP_DARK_OAK = registerBlockWithoutItem(
            "stump_dark_oak", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_DARK_OAK_CRAFTING)
    );
    public static final Block STUMP_MANGROVE = registerBlockWithoutItem(
            "stump_mangrove", createStump(MapColor.OAK_TAN, BlockSoundGroup.WOOD, STUMP_MANGROVE_CRAFTING)
    );
    public static final Block STUMP_CHERRY = registerBlockWithoutItem(
            "stump_cherry", createStump(MapColor.OAK_TAN, BlockSoundGroup.CHERRY_WOOD, STUMP_CHERRY_CRAFTING)
    );

    /** LOG BLOCKS **/

    // Spike
    public static final Block LOG_OAK_SPIKE = registerBlockWithoutItem(
            "log_oak_spike", createSpike(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_SPRUCE_SPIKE = registerBlockWithoutItem(
            "log_spruce_spike", createSpike(MapColor.SPRUCE_BROWN, MapColor.BROWN)
    );
    public static final Block LOG_BIRCH_SPIKE = registerBlockWithoutItem(
            "log_birch_spike", createSpike(MapColor.PALE_YELLOW, MapColor.OFF_WHITE)
    );
    public static final Block LOG_JUNGLE_SPIKE = registerBlockWithoutItem(
            "log_jungle_spike", createSpike(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_ACACIA_SPIKE = registerBlockWithoutItem(
            "log_acacia_spike", createSpike(MapColor.ORANGE, MapColor.STONE_GRAY)
    );
    public static final Block LOG_DARK_OAK_SPIKE = registerBlockWithoutItem(
            "log_dark_oak_spike", createSpike(MapColor.BROWN, MapColor.BROWN)
    );
    public static final Block LOG_MANGROVE_SPIKE = registerBlockWithoutItem(
            "log_mangrove_spike", createSpike(MapColor.RED, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_CHERRY_SPIKE = registerBlockWithoutItem(
            "log_cherry_spike", createCustomSoundSpike(
                    MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD)
    );

    // Chewed
    public static final Block LOG_OAK_CHEWED = registerBlockWithoutItem(
            "log_oak_chewed", createChewed(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_SPRUCE_CHEWED = registerBlockWithoutItem(
            "log_spruce_chewed", createChewed(MapColor.SPRUCE_BROWN, MapColor.BROWN)
    );
    public static final Block LOG_BIRCH_CHEWED = registerBlockWithoutItem(
            "log_birch_chewed", createChewed(MapColor.PALE_YELLOW, MapColor.OFF_WHITE)
    );
    public static final Block LOG_JUNGLE_CHEWED = registerBlockWithoutItem(
            "log_jungle_chewed", createChewed(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_ACACIA_CHEWED = registerBlockWithoutItem(
            "log_acacia_chewed", createChewed(MapColor.ORANGE, MapColor.STONE_GRAY)
    );
    public static final Block LOG_DARK_OAK_CHEWED = registerBlockWithoutItem(
            "log_dark_oak_chewed", createChewed(MapColor.BROWN, MapColor.BROWN)
    );
    public static final Block LOG_MANGROVE_CHEWED = registerBlockWithoutItem(
            "log_mangrove_chewed", createChewed(MapColor.RED, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_CHERRY_CHEWED = registerBlockWithoutItem(
            "log_cherry_chewed", createCustomSoundChewed(
                    MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD)
    );

    // Stripped
    public static final Block LOG_OAK_STRIPPED = registerBlockWithoutItem(
            "log_oak_stripped", createStripped(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_BIRCH_STRIPPED = registerBlockWithoutItem(
            "log_birch_stripped", createStripped(MapColor.SPRUCE_BROWN, MapColor.BROWN)
    );
    public static final Block LOG_SPRUCE_STRIPPED = registerBlockWithoutItem(
            "log_spruce_stripped", createStripped(MapColor.PALE_YELLOW, MapColor.OFF_WHITE)
    );
    public static final Block LOG_JUNGLE_STRIPPED = registerBlockWithoutItem(
            "log_jungle_stripped", createStripped(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_ACACIA_STRIPPED = registerBlockWithoutItem(
            "log_acacia_stripped", createStripped(MapColor.ORANGE, MapColor.STONE_GRAY)
    );
    public static final Block LOG_DARK_OAK_STRIPPED = registerBlockWithoutItem(
            "log_dark_oak_stripped", createStripped(MapColor.BROWN, MapColor.BROWN)
    );
    public static final Block LOG_MANGROVE_STRIPPED = registerBlockWithoutItem(
            "log_mangrove_stripped", createStripped(MapColor.RED, MapColor.SPRUCE_BROWN)
    );
    public static final Block LOG_CHERRY_STRIPPED = registerBlockWithoutItem(
            "log_cherry_stripped", createCustomSoundStripped(
                    MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD)
    );

    public static final Block LOG_SMOULDERING = registerBlockWithoutItem(
            "log_smouldering", new SmoulderingLogBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .ticksRandomly()
            )
    );
    public static final Block STUMP_SMOULDERING = registerBlockWithoutItem(
            "stump_smouldering", new SmoulderingStumpBlock(AbstractBlock.Settings.create()
                    .strength(3f,15f)
                    .mapColor(MapColor.BLACK)
                    .instrument(NoteBlockInstrument.BASS)
                    .sounds(BlockSoundGroup.WOOD)
                    .ticksRandomly()
            )
    );

    public static final Block WOOD_CINDERS = registerBlockWithoutItem(
            "wood_cinders", new WoodCindersBlock(AbstractBlock.Settings.create()
                    .strength(0.25F)
                    .mapColor(MapColor.GRAY)
                    .instrument(NoteBlockInstrument.BIT)
                    .sounds(BlockSoundGroup.GRAVEL)
                    .nonOpaque()
                    .ticksRandomly()
            )
    );

    public static final Block WOOD_CINDERS_STUMP = registerBlockWithoutItem(
            "wood_cinders_stump", new WoodCindersStumpBlock(AbstractBlock.Settings.create()
                    .strength(0.25F)
                    .mapColor(MapColor.GRAY)
                    .instrument(NoteBlockInstrument.BIT)
                    .sounds(BlockSoundGroup.GRAVEL)
                    .nonOpaque()
                    .ticksRandomly()
            )
    );

    public static final Block ASH_GROUND_COVER = registerBlockWithoutItem(
            "ash_ground_cover", new AshGroundCoverBlock(AbstractBlock.Settings.create()
                    .strength(0.1f)
                    .sounds(BlockSoundGroup.SAND)
                    .replaceable()
                    .ticksRandomly()
            )
    );

    public static final Block OAK_SAPLING_SMALL = registerBlock(
            "oak_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.OAK_SAPLING)
    );

    public static final Block SPRUCE_SAPLING_SMALL = registerBlock(
            "spruce_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.SPRUCE_SAPLING)
    );

    public static final Block BIRCH_SAPLING_SMALL = registerBlock(
            "birch_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.BIRCH_SAPLING)
    );

    public static final Block JUNGLE_SAPLING_SMALL = registerBlock(
            "jungle_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.JUNGLE_SAPLING)
    );

    public static final Block ACACIA_SAPLING_SMALL = registerBlock(
            "acacia_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.ACACIA_SAPLING)
    );

    public static final Block DARK_OAK_SAPLING_SMALL = registerBlock(
            "dark_oak_sapling_small", new SmallSaplingBlock(generalSaplingSettings())
                    .setMatureVariant(Blocks.DARK_OAK_SAPLING)
    );

    public static final Block CHERRY_SAPLING_SMALL = registerBlock(
            "cherry_sapling_small", new SmallSaplingBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.PINK).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
            ).setMatureVariant(Blocks.CHERRY_SAPLING)
    );

    public static StumpBlock createStump(MapColor mapColor, BlockSoundGroup soundGroup, Block craftingVariant) {
        return new StumpBlock(AbstractBlock.Settings.create().strength(6f,30f).sounds(soundGroup)
                .mapColor(mapColor).instrument(NoteBlockInstrument.BASS).burnable(), craftingVariant);
    }

    public static CraftingTableBlock createStumpCrafting(MapColor mapColor) {
        return new CraftingTableBlock(AbstractBlock.Settings.create().hardness(1.25f)
                .sounds(BlockSoundGroup.WOOD).mapColor(mapColor).instrument(NoteBlockInstrument.BASS).burnable());
    }

    public static LogStrippedBlock createStripped(MapColor topMapColor, MapColor sideMapColor) {
        return new LogStrippedBlock(logSettings(topMapColor, sideMapColor));
    }

    public static LogChewedBlock createChewed(MapColor topMapColor, MapColor sideMapColor) {
        return new LogChewedBlock(logSettings(topMapColor, sideMapColor));
    }

    public static LogSpikeBlock createSpike(MapColor topMapColor, MapColor sideMapColor) {
        return new LogSpikeBlock(logSettings(topMapColor, sideMapColor));
    }

    public static LogStrippedBlock createCustomSoundStripped(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogStrippedBlock(customSoundsLogSettings(topMapColor, sideMapColor, soundGroup));
    }

    public static LogChewedBlock createCustomSoundChewed(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogChewedBlock(customSoundsLogSettings(topMapColor, sideMapColor, soundGroup));
    }
    public static LogSpikeBlock createCustomSoundSpike(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogSpikeBlock(customSoundsLogSettings(topMapColor, sideMapColor, soundGroup));
    }

    private static AbstractBlock.Settings logSettings(MapColor topMapColor, MapColor sideMapColor) {
        return AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(2.0F).nonOpaque().burnable();
    }

    private static AbstractBlock.Settings charredLogSettings() {
        return AbstractBlock.Settings.create().mapColor(MapColor.BLACK).instrument(NoteBlockInstrument.BASS)
                .strength(2.0F).nonOpaque().burnable().sounds(BlockSoundGroup.WOOD);
    }

    private static AbstractBlock.Settings customSoundsLogSettings(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(NoteBlockInstrument.BASS).strength(2.0F).sounds(soundGroup).nonOpaque().burnable();
    }

    private static AbstractBlock.Settings generalSaplingSettings() {
       return AbstractBlock.Settings.create()
               .mapColor(MapColor.DARK_GREEN)
               .noCollision()
               .ticksRandomly()
               .breakInstantly()
               .sounds(BlockSoundGroup.GRASS)
               .pistonBehavior(PistonBehavior.DESTROY);
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, Identifier.of(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, Identifier.of(SturdyTreesMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }

    public static void register() {
        SturdyTreesMod.LOGGER.debug("Registering ModBlocks for " + SturdyTreesMod.MOD_ID);

        registerFlammableBlocks();
    }

    private static void registerFlammableBlocks() {
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();

        instance.add(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS, 5 ,5);
        instance.add(SturdyTreesTags.Blocks.STUMPS, 5 ,5);
        instance.add(SturdyTreesTags.Blocks.CRAFTING_STUMPS, 5 ,5);

    }

}