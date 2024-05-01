package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.block.blocks.*;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

public class SturdyTreesBlocks implements SideModUtils {

    /** STUMP BLOCKS **/

    public static final Block STUMP_OAK = registerBlockWithoutItem("stump_oak", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_OAK_VAR1 = registerBlockWithoutItem("stump_oak_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_SPRUCE = registerBlockWithoutItem("stump_spruce", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_SPRUCE_VAR1 = registerBlockWithoutItem("stump_spruce_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_BIRCH = registerBlockWithoutItem("stump_birch", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_BIRCH_VAR1 = registerBlockWithoutItem("stump_birch_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_JUNGLE = registerBlockWithoutItem("stump_jungle", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_JUNGLE_VAR1 = registerBlockWithoutItem("stump_jungle_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_ACACIA = registerBlockWithoutItem("stump_acacia", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_ACACIA_VAR1 = registerBlockWithoutItem("stump_acacia_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_DARK_OAK = registerBlockWithoutItem("stump_dark_oak", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_DARK_OAK_VAR1 = registerBlockWithoutItem("stump_dark_oak_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_MANGROVE = registerBlockWithoutItem("stump_mangrove", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_MANGROVE_VAR1 = registerBlockWithoutItem("stump_mangrove_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_CHERRY = registerBlockWithoutItem("stump_cherry", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));
    public static final Block STUMP_CHERRY_VAR1 = registerBlockWithoutItem("stump_cherry_var1", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD)));



    /** VARIANTS FOR LOG BLOCKS **/

    // Spike
    public static final Block LOG_OAK_SPIKE_DOWN = registerBlockWithoutItem("log_oak_spike_down", createSpike(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_SPRUCE_SPIKE_DOWN = registerBlockWithoutItem("log_spruce_spike_down", createSpike(MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final Block LOG_BIRCH_SPIKE_DOWN = registerBlockWithoutItem("log_birch_spike_down", createSpike(MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final Block LOG_JUNGLE_SPIKE_DOWN = registerBlockWithoutItem("log_jungle_spike_down", createSpike(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_ACACIA_SPIKE_DOWN = registerBlockWithoutItem("log_acacia_spike_down", createSpike(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final Block LOG_DARK_OAK_SPIKE_DOWN = registerBlockWithoutItem("log_dark_oak_spike_down", createSpike(MapColor.BROWN, MapColor.BROWN));
    public static final Block LOG_MANGROVE_SPIKE_DOWN = registerBlockWithoutItem("log_mangrove_spike_down", createSpike(MapColor.RED, MapColor.SPRUCE_BROWN));
    public static final Block LOG_CHERRY_SPIKE_DOWN = registerBlockWithoutItem("log_cherry_spike_down", createBambooSpike(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD));

    public static final Block LOG_OAK_SPIKE_UP = registerBlockWithoutItem("log_oak_spike_up", createSpike(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_SPRUCE_SPIKE_UP = registerBlockWithoutItem("log_spruce_spike_up", createSpike(MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final Block LOG_BIRCH_SPIKE_UP = registerBlockWithoutItem("log_birch_spike_up", createSpike(MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final Block LOG_JUNGLE_SPIKE_UP = registerBlockWithoutItem("log_jungle_spike_up", createSpike(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_ACACIA_SPIKE_UP = registerBlockWithoutItem("log_acacia_spike_up", createSpike(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final Block LOG_DARK_OAK_SPIKE_UP = registerBlockWithoutItem("log_dark_oak_spike_up", createSpike(MapColor.BROWN, MapColor.BROWN));
    public static final Block LOG_MANGROVE_SPIKE_UP = registerBlockWithoutItem("log_mangrove_spike_up", createSpike(MapColor.RED, MapColor.SPRUCE_BROWN));
    public static final Block LOG_CHERRY_SPIKE_UP = registerBlockWithoutItem("log_cherry_spike_up", createBambooSpike(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD));


    // Chewed
    public static final Block LOG_OAK_CHEWED = registerBlockWithoutItem("log_oak_chewed", createChewed(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_SPRUCE_CHEWED = registerBlockWithoutItem("log_spruce_chewed", createChewed(MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final Block LOG_BIRCH_CHEWED = registerBlockWithoutItem("log_birch_chewed", createChewed(MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final Block LOG_JUNGLE_CHEWED = registerBlockWithoutItem("log_jungle_chewed", createChewed(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_ACACIA_CHEWED = registerBlockWithoutItem("log_acacia_chewed", createChewed(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final Block LOG_DARK_OAK_CHEWED = registerBlockWithoutItem("log_dark_oak_chewed", createChewed(MapColor.BROWN, MapColor.BROWN));
    public static final Block LOG_MANGROVE_CHEWED = registerBlockWithoutItem("log_mangrove_chewed", createChewed(MapColor.RED, MapColor.SPRUCE_BROWN));
    public static final Block LOG_CHERRY_CHEWED = registerBlockWithoutItem("log_cherry_chewed", createBambooChewed(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD));

    // Stripped
    public static final Block LOG_OAK_STRIPPED = registerBlockWithoutItem("log_oak_stripped", createStripped(MapColor.OAK_TAN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_BIRCH_STRIPPED = registerBlockWithoutItem("log_birch_stripped", createStripped(MapColor.SPRUCE_BROWN, MapColor.BROWN));
    public static final Block LOG_SPRUCE_STRIPPED = registerBlockWithoutItem("log_spruce_stripped", createStripped(MapColor.PALE_YELLOW, MapColor.OFF_WHITE));
    public static final Block LOG_JUNGLE_STRIPPED = registerBlockWithoutItem("log_jungle_stripped", createStripped(MapColor.DIRT_BROWN, MapColor.SPRUCE_BROWN));
    public static final Block LOG_ACACIA_STRIPPED = registerBlockWithoutItem("log_acacia_stripped", createStripped(MapColor.ORANGE, MapColor.STONE_GRAY));
    public static final Block LOG_DARK_OAK_STRIPPED = registerBlockWithoutItem("log_dark_oak_stripped", createStripped(MapColor.BROWN, MapColor.BROWN));
    public static final Block LOG_MANGROVE_STRIPPED = registerBlockWithoutItem("log_mangrove_stripped", createStripped(MapColor.RED, MapColor.SPRUCE_BROWN));
    public static final Block LOG_CHERRY_STRIPPED = registerBlockWithoutItem("log_cherry_stripped", createBambooStripped(MapColor.TERRACOTTA_WHITE, MapColor.TERRACOTTA_GRAY, BlockSoundGroup.CHERRY_WOOD));





    public static LogSpikeBlock createSpike(MapColor topMapColor, MapColor sideMapColor) {
        return new LogSpikeBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().burnable());
    }

    public static LogSpikeBlock createBambooSpike(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogSpikeBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(soundGroup).nonOpaque().burnable());
    }

    public static LogChewedBlock createChewed(MapColor topMapColor, MapColor sideMapColor) {
        return new LogChewedBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().burnable());
    }

    public static LogChewedBlock createBambooChewed(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogChewedBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(soundGroup).nonOpaque().burnable());
    }

    public static LogStrippedBlock createStripped(MapColor topMapColor, MapColor sideMapColor) {
        return new LogStrippedBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(BlockSoundGroup.WOOD).nonOpaque().burnable());
    }

    public static LogStrippedBlock createBambooStripped(MapColor topMapColor, MapColor sideMapColor, BlockSoundGroup soundGroup) {
        return new LogStrippedBlock(AbstractBlock.Settings.create().mapColor((state) ->
                        state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                .instrument(Instrument.BASS).strength(2.0F).sounds(soundGroup).nonOpaque().burnable());
    }
    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registries.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registries.ITEM, new Identifier(SturdyTreesMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        SturdyTreesMod.LOGGER.debug("Registering ModBlocks for " + SturdyTreesMod.MOD_ID);
    }


}
