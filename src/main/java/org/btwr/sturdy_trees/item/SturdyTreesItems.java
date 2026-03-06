package org.btwr.sturdy_trees.item;

import org.btwr.sturdy_trees.SturdyTreesMod;
import org.btwr.sturdy_trees.item.items.StumpRemoverItem;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class SturdyTreesItems {

    public static final Item STUMP_REMOVER = registerItem(
            "stump_remover", new StumpRemoverItem(new Item.Settings().maxCount(16))
    );

    public static final Item DUST_SAW = registerItem("dust_saw", new Item (new Item.Settings()));

    public static final Item BARK_OAK = registerItem("bark_oak", new Item (new Item.Settings()));
    public static final Item BARK_BIRCH = registerItem("bark_birch", new Item (new Item.Settings()));
    public static final Item BARK_SPRUCE = registerItem("bark_spruce", new Item (new Item.Settings()));
    public static final Item BARK_JUNGLE = registerItem("bark_jungle", new Item (new Item.Settings()));
    public static final Item BARK_ACACIA = registerItem("bark_acacia", new Item (new Item.Settings()));
    public static final Item BARK_DARK_OAK = registerItem("bark_dark_oak", new Item (new Item.Settings()));
    public static final Item BARK_MANGROVE = registerItem("bark_mangrove", new Item (new Item.Settings()));
    public static final Item BARK_CHERRY = registerItem("bark_cherry", new Item (new Item.Settings()));

    private static void addToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(DUST_SAW);
        entries.add(BARK_OAK);
        entries.add(BARK_SPRUCE);
        entries.add(BARK_BIRCH);
        entries.add(BARK_JUNGLE);
        entries.add(BARK_ACACIA);
        entries.add(BARK_DARK_OAK);
        entries.add(BARK_MANGROVE);
        entries.add(BARK_CHERRY);
    }

    private static void addToToolsItemGroup(FabricItemGroupEntries entries) {
        entries.add(STUMP_REMOVER);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(SturdyTreesMod.MOD_ID, name), item);
    }

    public static void register() {
        SturdyTreesMod.LOGGER.info("Registering Mod Items for " + SturdyTreesMod.MOD_ID);

        // Register to item groups
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(SturdyTreesItems::addToIngredientItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(SturdyTreesItems::addToToolsItemGroup);

        // Register fuel items
        FuelRegistry.INSTANCE.add(SturdyTreesItems.DUST_SAW, 25);
        FuelRegistry.INSTANCE.add(SturdyTreesTags.Items.BARK_ITEMS, 25);
    }

}