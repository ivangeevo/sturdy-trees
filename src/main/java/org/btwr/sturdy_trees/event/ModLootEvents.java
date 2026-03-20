package org.btwr.sturdy_trees.event;

import com.google.common.collect.ImmutableList;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import org.btwr.shared_library.mixin.accessors.ItemEntryAccessor;
import org.btwr.shared_library.mixin.accessors.LootPoolBuilderAccessor;

import java.util.ArrayList;
import java.util.List;

public class ModLootEvents {

    // tough wood types require an axe to break fully
    private static final String[] overworldToughWoodTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };

    // All vanilla saplings (excluding mangrove propagule)
    private static final String[] vanillaSmallSaplingTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "cherry"
    };

    public static void register() {

    }

    private static void modifySpecificItem(RegistryKey<LootTable> registryKey, Item target, Item toReplace) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {

            // Check if the key is for target's loot table
            if (registryKey != key) return;

            tableBuilder.modifyPools(builder -> {
                List<LootPoolEntry> l = new ArrayList<>(((LootPoolBuilderAccessor) builder).getEntries().build());
                l.replaceAll(entry -> {
                    if (!(entry instanceof ItemEntry itemEntry))
                        return entry;
                    if (((ItemEntryAccessor) itemEntry).getItem().value() != target)
                        return entry;
                    ((ItemEntryAccessor) entry).setItem(Registries.ITEM.getEntry(toReplace));
                    return entry;
                });

                ((LootPoolBuilderAccessor) builder).setEntries(ImmutableList.<LootPoolEntry>builder().addAll(l));
            });
        });
    }

}