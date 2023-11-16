package ivangeevo.sturdy_trees.tag;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class SturdyTreesItemTags {
    public static final TagKey<Item> MODDED_AXES = SturdyTreesItemTags.of("modded_axes");

    public static final TagKey<Item> MODERN_CHISELS = SturdyTreesItemTags.of("modern_chisels");


    private SturdyTreesItemTags() {
    }

    private static TagKey<Item> of(String id) {
        return TagKey.of(Registry.ITEM_KEY, new Identifier(id));
    }
}