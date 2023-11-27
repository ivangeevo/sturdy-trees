package ivangeevo.sturdy_trees.tag;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class SturdyTreesTags {

    public static class Items {

        public static final TagKey<Item> MODDED_AXES = createTag("modded_axes");

        public static final TagKey<Item> MODERN_CHISELS = createTag("modern_chisels");



        private static TagKey<Item> createTag(String id) {
            return TagKey.of(Registry.ITEM_KEY, new Identifier(id));
        }
        private Items() {
        }
    }

    public static class Blocks {

        public static final TagKey<Block> STUMP_BLOCKS = createTag("stump_blocks");
        public static final TagKey<Block> LOG_BLOCKS = createTag("log_blocks");
        public static final TagKey<Block> STRIPPED_LOG_BLOCKS = createTag("stripped_log_blocks");


        private static TagKey<Block> createTag(String id) {
            return TagKey.of(Registry.BLOCK_KEY, new Identifier(id));
        }


        private Blocks() {
        }
    }

}