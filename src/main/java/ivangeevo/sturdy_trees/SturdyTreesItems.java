package ivangeevo.sturdy_trees;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SturdyTreesItems {

    public static final Item SHAFT = registerItem( "shaft", new Item(new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item DUST_SAW = registerItem( "dust_saw", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item BARK_OAK = registerItem( "bark_oak", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));
    public static final Item BARK_BIRCH = registerItem( "bark_birch", new Item (new FabricItemSettings()));

    public static final Item BARK_SPRUCE = registerItem( "bark_spruce", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item BARK_JUNGLE = registerItem( "bark_jungle", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));
    public static final Item BARK_ACACIA = registerItem( "bark_acacia", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));
    public static final Item BARK_DARK_OAK = registerItem( "bark_dark_oak", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));

    public static final Item BARK_MANGROVE = registerItem( "bark_mangrove", new Item (new FabricItemSettings().group(ItemGroup.MATERIALS)));


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(SturdyTrees.MOD_ID, name), item);
    }

    public static void registerModItems() {
        SturdyTrees.LOGGER.info("Registering Mod Items for " + SturdyTrees.MOD_ID);
    }


}
