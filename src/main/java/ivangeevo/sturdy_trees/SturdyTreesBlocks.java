package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.block.blocks.*;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.block.util.StumpType;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SturdyTreesBlocks implements SideModUtils {

    /** STUMP BLOCKS **/
    public static final Block STUMP_OAK = registerBlockWithoutItem("stump_oak", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_OAK_VAR1 = registerBlockWithoutItem("stump_oak_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.OAK));
    public static final Block STUMP_SPRUCE = registerBlockWithoutItem("stump_spruce", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_SPRUCE_VAR1 = registerBlockWithoutItem("stump_spruce_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_BIRCH = registerBlockWithoutItem("stump_birch", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_BIRCH_VAR1 = registerBlockWithoutItem("stump_birch_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.BIRCH));
    public static final Block STUMP_JUNGLE = registerBlockWithoutItem("stump_jungle", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_JUNGLE_VAR1 = registerBlockWithoutItem("stump_jungle_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.JUNGLE));
    public static final Block STUMP_ACACIA = registerBlockWithoutItem("stump_acacia", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_ACACIA_VAR1 = registerBlockWithoutItem("stump_acacia_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.ACACIA));
    public static final Block STUMP_DARK_OAK = registerBlockWithoutItem("stump_dark_oak", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.DARK_OAK));
    public static final Block STUMP_DARK_OAK_VAR1 = registerBlockWithoutItem("stump_dark_oak_var1", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.SPRUCE));
    public static final Block STUMP_MANGROVE = registerBlockWithoutItem("stump_mangrove", new StumpBlock(FabricBlockSettings.of(Material.WOOD).strength(50.0f).sounds(BlockSoundGroup.WOOD).requiresTool(), StumpType.MANGROVE));



    // Added variables to change the log strength based on whether BTWR Core is loaded or not.
    private static final float vanillaLogStrength = 2F;
    private static final float btwrLogStrength = 24F;

    // Initialize logStrength based on whether BTWR Core is loaded or not.
    private static final float logStrength = isBTWRLoaded ? btwrLogStrength : vanillaLogStrength;

    // ----------------------------------------------------------------------------- //

    






    /** VARIANTS FOR LOG BLOCKS **/

    public static final Block LOG_OAK_BOT_VAR1 = registerBlockWithoutItem("log_oak_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_BOT_VAR2 = registerBlockWithoutItem("log_oak_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_BOT_VAR3 = registerBlockWithoutItem("log_oak_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_SPRUCE_BOT_VAR1 = registerBlockWithoutItem("log_spruce_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_BOT_VAR2 = registerBlockWithoutItem("log_spruce_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_BOT_VAR3 = registerBlockWithoutItem("log_spruce_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_BIRCH_BOT_VAR1 = registerBlockWithoutItem("log_birch_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_BOT_VAR2 = registerBlockWithoutItem("log_birch_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_BOT_VAR3 = registerBlockWithoutItem("log_birch_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_JUNGLE_BOT_VAR1 = registerBlockWithoutItem("log_jungle_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_BOT_VAR2 = registerBlockWithoutItem("log_jungle_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_BOT_VAR3 = registerBlockWithoutItem("log_jungle_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_ACACIA_BOT_VAR1 = registerBlockWithoutItem("log_acacia_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_BOT_VAR2 = registerBlockWithoutItem("log_acacia_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_BOT_VAR3 = registerBlockWithoutItem("log_acacia_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_DARK_OAK_BOT_VAR1 = registerBlockWithoutItem("log_dark_oak_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_BOT_VAR2 = registerBlockWithoutItem("log_dark_oak_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_BOT_VAR3 = registerBlockWithoutItem("log_dark_oak_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_MANGROVE_BOT_VAR1 = registerBlockWithoutItem("log_mangrove_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_BOT_VAR2 = registerBlockWithoutItem("log_mangrove_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_BOT_VAR3 = registerBlockWithoutItem("log_mangrove_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_WARPED_BOT_VAR1 = registerBlockWithoutItem("log_warped_bot_var1", new LogBotVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_BOT_VAR2 = registerBlockWithoutItem("log_warped_bot_var2", new LogBotVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_BOT_VAR3 = registerBlockWithoutItem("log_warped_bot_var3", new LogBotVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_OAK_TOP_VAR1 = registerBlockWithoutItem("log_oak_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_TOP_VAR2 = registerBlockWithoutItem("log_oak_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_TOP_VAR3 = registerBlockWithoutItem("log_oak_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_BIRCH_TOP_VAR1 = registerBlockWithoutItem("log_birch_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_TOP_VAR2 = registerBlockWithoutItem("log_birch_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_TOP_VAR3 = registerBlockWithoutItem("log_birch_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_SPRUCE_TOP_VAR1 = registerBlockWithoutItem("log_spruce_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_TOP_VAR2 = registerBlockWithoutItem("log_spruce_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_TOP_VAR3 = registerBlockWithoutItem("log_spruce_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_JUNGLE_TOP_VAR1 = registerBlockWithoutItem("log_jungle_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_TOP_VAR2 = registerBlockWithoutItem("log_jungle_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_TOP_VAR3 = registerBlockWithoutItem("log_jungle_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_ACACIA_TOP_VAR1 = registerBlockWithoutItem("log_acacia_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_TOP_VAR2 = registerBlockWithoutItem("log_acacia_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_TOP_VAR3 = registerBlockWithoutItem("log_acacia_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_DARK_OAK_TOP_VAR1 = registerBlockWithoutItem("log_dark_oak_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_TOP_VAR2 = registerBlockWithoutItem("log_dark_oak_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_TOP_VAR3 = registerBlockWithoutItem("log_dark_oak_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_MANGROVE_TOP_VAR1 = registerBlockWithoutItem("log_mangrove_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_TOP_VAR2 = registerBlockWithoutItem("log_mangrove_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_TOP_VAR3 = registerBlockWithoutItem("log_mangrove_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_WARPED_TOP_VAR1 = registerBlockWithoutItem("log_warped_top_var1", new LogTopVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_TOP_VAR2 = registerBlockWithoutItem("log_warped_top_var2", new LogTopVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_TOP_VAR3 = registerBlockWithoutItem("log_warped_top_var3", new LogTopVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_OAK_MID_VAR1 = registerBlockWithoutItem("log_oak_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_MID_VAR2 = registerBlockWithoutItem("log_oak_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_MID_VAR3 = registerBlockWithoutItem("log_oak_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_SPRUCE_MID_VAR1 = registerBlockWithoutItem("log_spruce_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_MID_VAR2 = registerBlockWithoutItem("log_spruce_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_MID_VAR3 = registerBlockWithoutItem("log_spruce_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_BIRCH_MID_VAR1 = registerBlockWithoutItem("log_birch_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_MID_VAR2 = registerBlockWithoutItem("log_birch_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_MID_VAR3 = registerBlockWithoutItem("log_birch_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_JUNGLE_MID_VAR1 = registerBlockWithoutItem("log_jungle_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_MID_VAR2 = registerBlockWithoutItem("log_jungle_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_MID_VAR3 = registerBlockWithoutItem("log_jungle_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_ACACIA_MID_VAR1 = registerBlockWithoutItem("log_acacia_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_MID_VAR2 = registerBlockWithoutItem("log_acacia_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_MID_VAR3 = registerBlockWithoutItem("log_acacia_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_DARK_OAK_MID_VAR1 = registerBlockWithoutItem("log_dark_oak_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_MID_VAR2 = registerBlockWithoutItem("log_dark_oak_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_MID_VAR3 = registerBlockWithoutItem("log_dark_oak_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_MANGROVE_MID_VAR1 = registerBlockWithoutItem("log_mangrove_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_MID_VAR2 = registerBlockWithoutItem("log_mangrove_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_MID_VAR3 = registerBlockWithoutItem("log_mangrove_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    public static final Block LOG_WARPED_MID_VAR1 = registerBlockWithoutItem("log_warped_mid_var1", new LogMidVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_MID_VAR2 = registerBlockWithoutItem("log_warped_mid_var2", new LogMidVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_WARPED_MID_VAR3 = registerBlockWithoutItem("log_warped_mid_var3", new LogMidVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_OAK_STRIPPED_VAR0 = registerBlockWithoutItem("log_oak_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_STRIPPED_VAR1 = registerBlockWithoutItem("log_oak_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_STRIPPED_VAR2 = registerBlockWithoutItem("log_oak_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_OAK_STRIPPED_VAR3 = registerBlockWithoutItem("log_oak_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block LOG_BIRCH_STRIPPED_VAR0 = registerBlockWithoutItem("log_birch_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_STRIPPED_VAR1 = registerBlockWithoutItem("log_birch_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_STRIPPED_VAR2 = registerBlockWithoutItem("log_birch_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_BIRCH_STRIPPED_VAR3 = registerBlockWithoutItem("log_birch_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    // For Spruce
    public static final Block LOG_SPRUCE_STRIPPED_VAR0 = registerBlockWithoutItem("log_spruce_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_STRIPPED_VAR1 = registerBlockWithoutItem("log_spruce_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_STRIPPED_VAR2 = registerBlockWithoutItem("log_spruce_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_SPRUCE_STRIPPED_VAR3 = registerBlockWithoutItem("log_spruce_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    // For Jungle
    public static final Block LOG_JUNGLE_STRIPPED_VAR0 = registerBlockWithoutItem("log_jungle_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_STRIPPED_VAR1 = registerBlockWithoutItem("log_jungle_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_STRIPPED_VAR2 = registerBlockWithoutItem("log_jungle_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_JUNGLE_STRIPPED_VAR3 = registerBlockWithoutItem("log_jungle_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    // For Acacia
    public static final Block LOG_ACACIA_STRIPPED_VAR0 = registerBlockWithoutItem("log_acacia_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_STRIPPED_VAR1 = registerBlockWithoutItem("log_acacia_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_STRIPPED_VAR2 = registerBlockWithoutItem("log_acacia_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_ACACIA_STRIPPED_VAR3 = registerBlockWithoutItem("log_acacia_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    // For Dark Oak
    public static final Block LOG_DARK_OAK_STRIPPED_VAR0 = registerBlockWithoutItem("log_dark_oak_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_STRIPPED_VAR1 = registerBlockWithoutItem("log_dark_oak_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_STRIPPED_VAR2 = registerBlockWithoutItem("log_dark_oak_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_DARK_OAK_STRIPPED_VAR3 = registerBlockWithoutItem("log_dark_oak_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));

    // For Mangrove
    public static final Block LOG_MANGROVE_STRIPPED_VAR0 = registerBlockWithoutItem("log_mangrove_stripped_var0", new LogStripped(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_STRIPPED_VAR1 = registerBlockWithoutItem("log_mangrove_stripped_var1", new LogStrippedVar1(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_STRIPPED_VAR2 = registerBlockWithoutItem("log_mangrove_stripped_var2", new LogStrippedVar2(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));
    public static final Block LOG_MANGROVE_STRIPPED_VAR3 = registerBlockWithoutItem("log_mangrove_stripped_var3", new LogStrippedVar3(FabricBlockSettings.of(Material.WOOD).strength(logStrength).sounds(BlockSoundGroup.WOOD).nonOpaque()));


    // Helper methods to get stripped log variants
    public static Block getStrippedDefaultVariant(LogType logType) {
        switch (logType) {
            case OAK:
                return LOG_OAK_STRIPPED_VAR1;
            // Add cases for other log types

            default:
                return Blocks.AIR; // or throw an exception
        }
    }

    public static Block getStrippedMidVariant(LogType logType) {
        switch (logType) {
            case OAK:
                return LOG_OAK_STRIPPED_VAR2; // Adjust as needed
            // Add cases for other log types

            default:
                return Blocks.AIR; // or throw an exception
        }
    }

    public static Block getStrippedBotVariant(LogType logType) {
        switch (logType) {
            case OAK:
                return LOG_OAK_STRIPPED_VAR3; // Adjust as needed
            // Add cases for other log types

            default:
                return Blocks.AIR; // or throw an exception
        }
    }

    public static Block getStrippedTopVariant(LogType logType) {
        switch (logType) {
            case OAK:
                return LOG_OAK_STRIPPED_VAR0; // Adjust as needed
            // Add cases for other log types

            default:
                return Blocks.AIR; // or throw an exception
        }
    }

    private static Block registerBlockWithoutItem(String name, Block block) {
        return Registry.register(Registry.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Block registerBlock(String name, Block block, ItemGroup tab) {
        registerBlockItem(name, block, tab);
        return Registry.register(Registry.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block, ItemGroup tab) {
        return Registry.register(Registry.ITEM, new Identifier(SturdyTreesMod.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(tab)));
    }

    public static void registerModBlocks() {
        SturdyTreesMod.LOGGER.debug("Registering ModBlocks for " + SturdyTreesMod.MOD_ID);
    }


}
