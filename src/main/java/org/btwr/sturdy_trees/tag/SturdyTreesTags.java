package org.btwr.sturdy_trees.tag;

import org.btwr.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SturdyTreesTags {

    public static class Blocks {

        public static final TagKey<Block> CRAFTING_STUMPS = registerTag("crafting_stumps");

        // All log blocks from the mod that have variation.(aka all ConvertingLogBlock's)
        public static final TagKey<Block> LOG_VARIATION_BLOCKS = registerTag("log_variation_blocks");

        // Types of trees based on what trunk placer they're used in
        public static final TagKey<Block> STRAIGHT_TRUNK_TREES = registerTag("straight_trunk_trees");
        public static final TagKey<Block> LARGE_OAK_TRUNK_TREES = registerTag("large_oak_trunk_trees");
        public static final TagKey<Block> DARK_OAK_TRUNK_TREES = registerTag("dark_oak_trunk_trees");
        public static final TagKey<Block> FORKING_TRUNK_TREES = registerTag("forking_trunk_trees");
        public static final TagKey<Block> MEGA_JUNGLE_TRUNK_TREES = registerTag("mega_jungle_trunk_trees");
        public static final TagKey<Block> GIANT_TRUNK_TREES = registerTag("giant_trunk_trees");
        public static final TagKey<Block> BENDING_TRUNK_TREES = registerTag("bending_trunk_trees");
        public static final TagKey<Block> UPWARDS_BRANCHING_TRUNK_TREES = registerTag("upwards_branching_trunk_trees");
        public static final TagKey<Block> CHERRY_TRUNK_TREES = registerTag("cherry_trunk_trees");

        private static TagKey<Block> registerTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(SturdyTreesMod.MOD_ID, id));
        }

    }

    public static class Items {

        public static final TagKey<Item> BARK_ITEMS = registerTag("bark_items");

        // All tools that can convert stumps into their crafting variant must be put under this tag.
        // While normally tools provide their efficient blocks in their ToolMaterial definition; this is necessary
        // because the vanilla suitableFor() check decides whether the block will drop loot as well, and we don't need that
        public static final TagKey<Item> STUMP_EFFICIENT = registerTag("stump_efficient");

        private static TagKey<Item> registerTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(SturdyTreesMod.MOD_ID, id));
        }

    }

}