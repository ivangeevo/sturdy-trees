package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.block.blocks.*;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.block.util.StumpType;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class SturdyTreesBlocks {

    /** STUMP BLOCKS **/
    public static final Block STUMP_OAK = registerBlockWithoutItem("stump_oak",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_OAK_VAR1 = registerBlockWithoutItem("stump_oak_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_SPRUCE = registerBlockWithoutItem("stump_spruce",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_SPRUCE_VAR1 = registerBlockWithoutItem("stump_spruce_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_BIRCH = registerBlockWithoutItem("stump_birch",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_BIRCH_VAR1 = registerBlockWithoutItem("stump_birch_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_JUNGLE = registerBlockWithoutItem("stump_jungle",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_JUNGLE_VAR1 = registerBlockWithoutItem("stump_jungle_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_ACACIA = registerBlockWithoutItem("stump_acacia",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_ACACIA_VAR1 = registerBlockWithoutItem("stump_acacia_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_DARK_OAK = registerBlockWithoutItem("stump_dark_oak",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.DARK_OAK));
    public static final Block STUMP_DARK_OAK_VAR1 = registerBlockWithoutItem("stump_dark_oak_var1",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_MANGROVE = registerBlockWithoutItem("stump_mangrove",
            new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.MANGROVE));

    //public static final Block STUMP_CHERRY = registerBlockWithoutItem("stump_cherry", new StumpBlock(FabricBlockSettings.create().strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.CHERRY));


    // ----------------------------------------------------------------------------- //


    /** VARIANTS FOR LOG BLOCKS **/

    public static final Block LOG_BOT_VAR1 = registerBlockWithoutItem("log_bot_var1",
            new LogBotVar1(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BOT_VAR2 = registerBlockWithoutItem("log_bot_var2",
            new LogBotVar2(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BOT_VAR3 = registerBlockWithoutItem("log_bot_var3",
            new LogBotVar3(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_TOP_VAR1 = registerBlockWithoutItem("log_top_var1",
            new LogTopVar1(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_TOP_VAR2 = registerBlockWithoutItem("log_top_var2",
            new LogTopVar2(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_TOP_VAR3 = registerBlockWithoutItem("log_top_var3",
            new LogTopVar3(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_MID_VAR1 = registerBlockWithoutItem("log_mid_var1",
            new LogMidVar1(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MID_VAR2 = registerBlockWithoutItem("log_mid_var2",
            new LogMidVar2(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MID_VAR3 = registerBlockWithoutItem("log_mid_var3",
            new LogMidVar3(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_STRIPPED_VAR0 = registerBlockWithoutItem("log_stripped_var0",
            new LogStripped(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_STRIPPED_VAR1 = registerBlockWithoutItem("log_stripped_var1",
            new LogStrippedVar1(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_STRIPPED_VAR2 = registerBlockWithoutItem("log_stripped_var2",
            new LogStrippedVar2(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_STRIPPED_VAR3 = registerBlockWithoutItem("log_stripped_var3",
            new LogStrippedVar3(FabricBlockSettings.create().strength(2F).sounds(BlockSoundGroup.WOOD).nonOpaque()));



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
