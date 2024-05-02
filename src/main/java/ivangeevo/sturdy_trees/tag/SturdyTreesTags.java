package ivangeevo.sturdy_trees.tag;

import ivangeevo.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public final class SturdyTreesTags
{
    public static class Blocks
    {

        // All log blocks from the mod that have variation.(aka all ConvertingLogBlock's)
        public static final TagKey<Block> LOG_VARIATION_BLOCKS = registerTag("log_variation_blocks");

        public static final TagKey<Block> LOG_SPIKE_UP_BLOCKS =  registerTag("log_spike_up_blocks");
        public static final TagKey<Block> LOG_SPIKE_DOWN_BLOCKS =  registerTag("log_spike_down_blocks");

        private static TagKey<Block> registerTag(String id) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(SturdyTreesMod.MOD_ID, id));
        }

    }

    public static class Items
    {

        public static final TagKey<Item> BARK_ITEMS = registerTag("bark_items");


        private static TagKey<Item> registerTag(String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(SturdyTreesMod.MOD_ID, id));
        }

    }



}