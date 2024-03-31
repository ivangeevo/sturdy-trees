package ivangeevo.sturdy_trees.tag;

import ivangeevo.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SturdyTreesTags {

    public static class Items {

        public static final TagKey<Item> MODDED_AXES = createTag("modded_axes");
        public static final TagKey<Item> BARK_ITEMS = createTag("bark_items");
        public static final TagKey<Item> MODERN_CHISELS = createTag("modern_chisels");

        public static final TagKey<Item> MODERN_AXES = createTag("modern_axes");


        public static final TagKey<Item> CREEPER_OYSTERS_TAG = createTag("creeper_oysters_tag");



        private static TagKey<Item> createTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(SturdyTreesMod.MOD_ID, id));
        }
        private Items() {
        }
    }

    public static class Blocks {

        public static final TagKey<Block> STUMP_BLOCKS = createTag("stump_blocks");
        public static final TagKey<Block> LOG_BLOCKS = createTag("log_blocks");
        public static final TagKey<Block> STRIPPED_LOG_BLOCKS = createTag("stripped_log_blocks");


        private static TagKey<Block> createTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, id));
        }


        private Blocks() {
        }
    }

}