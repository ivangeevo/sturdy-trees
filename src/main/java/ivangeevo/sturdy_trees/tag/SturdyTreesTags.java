package ivangeevo.sturdy_trees.tag;

import ivangeevo.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SturdyTreesTags
{

    public static class Items {

        public static final TagKey<Item> BARK_ITEMS = registerTag("bark_items");

        public static final TagKey<Item> MODERN_AXES = registerTag("modern_axes");





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