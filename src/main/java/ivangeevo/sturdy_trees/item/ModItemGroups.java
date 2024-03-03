package ivangeevo.sturdy_trees.item;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.SturdyTreesMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {

    public static final ItemGroup STURDY_TREES = Registry.register(Registries.ITEM_GROUP,
            new Identifier(SturdyTreesMod.MOD_ID, "sturdy_trees"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.sturdy_trees"))
                    .icon(() -> new ItemStack(Items.STRIPPED_OAK_LOG)).entries((displayContext, entries) -> {

                        entries.add(SturdyTreesBlocks.LOG_SPIKE_OAK);

                    }).build());


    public static void registerItemGroups() {
        SturdyTreesMod.LOGGER.info("Registering Item Groups for " + SturdyTreesMod.MOD_ID);
    }
}
