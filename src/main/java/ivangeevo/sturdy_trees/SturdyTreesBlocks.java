package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.block.blocks.*;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.block.util.StumpType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class SturdyTreesBlocks {

    /** STUMP BLOCKS **/
    public static final Block STUMP_OAK = registerBlock("stump_oak",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_OAK_VAR1 = registerBlock("stump_oak_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_SPRUCE = registerBlock("stump_spruce",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_SPRUCE_VAR1 = registerBlock("stump_spruce_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_BIRCH = registerBlock("stump_birch",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_BIRCH_VAR1 = registerBlock("stump_birch_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_JUNGLE = registerBlock("stump_jungle",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_JUNGLE_VAR1 = registerBlock("stump_jungle_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_ACACIA = registerBlock("stump_acacia",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_ACACIA_VAR1 = registerBlock("stump_acacia_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_DARK_OAK = registerBlock("stump_dark_oak",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.DARK_OAK));
    public static final Block STUMP_DARK_OAK_VAR1 = registerBlock("stump_dark_oak_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_MANGROVE = registerBlock("stump_mangrove",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.MANGROVE));

    //public static final Block STUMP_CHERRY = registerBlockWithoutItem("stump_cherry", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.CHERRY));


    // ----------------------------------------------------------------------------- //


    /** VARIANTS FOR LOG BLOCKS **/



    // Stripped Logs
    public static final Block LOG_STRIPPED_OAK = registerBlock("log_stripped_oak",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_BIRCH = registerBlock("log_stripped_birch",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_SPRUCE = registerBlock("log_stripped_spruce",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_JUNGLE = registerBlock("log_stripped_jungle",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_ACACIA = registerBlock("log_stripped_acacia",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_DARK_OAK = registerBlock("log_stripped_dark_oak",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_MANGROVE = registerBlock("log_stripped_mangrove",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_CHERRY = registerBlock("log_stripped_cherry",
            new LogStripped(setNonOpaqueLogSettings()));
    public static final Block LOG_STRIPPED_WARPED = registerBlock("log_stripped_warped",
            new LogStripped(setNonOpaqueLogSettings()));


    // Chewed Logs
    public static final Block LOG_CHEWED_OAK = registerBlock("log_chewed_oak",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_BIRCH = registerBlock("log_chewed_birch",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_SPRUCE = registerBlock("log_chewed_spruce",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_JUNGLE = registerBlock("log_chewed_jungle",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_ACACIA = registerBlock("log_chewed_acacia",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_DARK_OAK = registerBlock("log_chewed_dark_oak",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_MANGROVE = registerBlock("log_chewed_mangrove",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_CHERRY = registerBlock("log_chewed_cherry",
            new LogChewed(setNonOpaqueLogSettings()));
    public static final Block LOG_CHEWED_WARPED = registerBlock("log_chewed_warped",
            new LogChewed(setNonOpaqueLogSettings()));

    // Spike Logs
    public static final Block LOG_SPIKE_OAK = registerBlock("log_spike_oak",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_BIRCH = registerBlock("log_spike_birch",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_SPRUCE = registerBlock("log_spike_spruce",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_JUNGLE = registerBlock("log_spike_jungle",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_ACACIA = registerBlock("log_spike_acacia",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_DARK_OAK = registerBlock("log_spike_dark_oak",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_MANGROVE = registerBlock("log_spike_mangrove",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_CHERRY = registerBlock("log_spike_cherry",
            new LogSpike(setNonOpaqueLogSettings()));
    public static final Block LOG_SPIKE_WARPED = registerBlock("log_spike_warped",
            new LogSpike(setNonOpaqueLogSettings()));





    private static FabricBlockSettings setNonOpaqueLogSettings() {
       return FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque();
    }


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(SturdyTreesMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        SturdyTreesMod.LOGGER.debug("Registering ModBlocks for " + SturdyTreesMod.MOD_ID);
    }


}
