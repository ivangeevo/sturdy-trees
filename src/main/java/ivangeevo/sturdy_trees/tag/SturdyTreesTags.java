package ivangeevo.sturdy_trees.tag;

import ivangeevo.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SturdyTreesTags
{

    public static class Conventional
    {
        public static class Blocks
        {
            public static final TagKey<Block> MODDED_CONVERTING_BLOCKS = createTag("modded_converting_blocks");
            public static final TagKey<Block> STUMP_BLOCKS = createTag("stump_blocks");


            private static TagKey<Block> createTag(String name) {
                return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
            }
        }

        public static class Items
        {
            /**
             * Tool levels;
             *
             * 1.Primitive
             * 2.Modern
             * 3.Advanced
             *
             */

            public static final TagKey<Item> PRIMITIVE_CHISELS = createTag("primitive_chisels");
            public static final TagKey<Item> MODERN_CHISELS = createTag("modern_chisels");

            public static final TagKey<Item> PRIMITIVE_PICKAXES = createTag("primitive_pickaxes");
            public static final TagKey<Item> MODERN_PICKAXES = createTag("modern_pickaxes");
            public static final TagKey<Item> ADVANCED_PICKAXES = createTag("advanced_pickaxes");

            public static final TagKey<Item> PRIMITIVE_AXES = createTag("primitive_axes");
            public static final TagKey<Item> MODERN_AXES = createTag("modern_axes");
            public static final TagKey<Item> ADVANCED_AXES = createTag("advanced_axes");


            public static final TagKey<Item> PRIMITIVE_SHOVELS = createTag("primitive_shovels");
            public static final TagKey<Item> MODERN_SHOVELS = createTag("modern_shovels");
            public static final TagKey<Item> ADVANCED_SHOVELS = createTag("advanced_shovels");


            public static final TagKey<Item> PRIMITIVE_HOES = createTag("primitive_hoes");
            public static final TagKey<Item> MODERN_HOES = createTag("modern_hoes");
            public static final TagKey<Item> ADVANCED_HOES = createTag("advanced_hoes");

            private static TagKey<Item> createTag(String name) {
                return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
            }

        }
    }

    public static class Items {

        public static final TagKey<Item> MODDED_AXES = registerTag("modded_axes");
        public static final TagKey<Item> BARK_ITEMS = registerTag("bark_items");
        public static final TagKey<Item> MODERN_CHISELS = registerTag("modern_chisels");

        public static final TagKey<Item> MODERN_AXES = registerTag("modern_axes");


        public static final TagKey<Item> CREEPER_OYSTERS_TAG = registerTag("creeper_oysters_tag");



        private static TagKey<Item> registerTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(SturdyTreesMod.MOD_ID, id));
        }
        private Items() {
        }
    }

    public static class Blocks {

        public static final TagKey<Block> STRIPPED_LOG_BLOCKS = registerTag("stripped_log_blocks");


        private static TagKey<Block> registerTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, id));
        }


        private Blocks() {
        }
    }

}