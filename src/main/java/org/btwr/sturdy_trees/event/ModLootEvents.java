package org.btwr.sturdy_trees.event;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.entry.AlternativeEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.btwr.shared_library.BTWRSLMod;

public class ModLootEvents {

    // tough wood types require an axe to break fully
    private static final String[] overworldToughWoodTypes = new String[] {
            "oak", "birch", "spruce", "jungle", "acacia", "dark_oak", "mangrove", "cherry"
    };

    public static void register() {
        /**
        LootTableEvents.REPLACE.register((key, original, source, registries) -> {

            // Replace vanilla log blocks loot tables with ones from the mod
            for (String leavesType : overworldToughWoodTypes) {
                String path = leavesType + "_log";
                if (key.equals(getLootTableId(path))) {
                    return createLogLootTables(leavesType);
                }
            }

            return original;
        });
         **/
    }

    private static LootTable createLogLootTables(String type) {
        LootPool.Builder mainPool = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1));

        AlternativeEntry.Builder alt = AlternativeEntry.builder();

        // Add the item entries based on the log type
        for (LeafEntry.Builder<?> item : getLogLeafItemEntries(type)) {
            alt.alternatively(item);
        }
        mainPool.with(alt);

        return LootTable.builder().pool(mainPool).build();
    }

    private static ItemEntry.Builder<?>[] getLogLeafItemEntries(String type) {
        // Try to find the log item dynamically based on the prefix
        Identifier logId = Identifier.of("minecraft", type + "_log");
        Item log = Registries.ITEM.get(logId);

        // Fallback to oak log if that type doesn’t exist
        if (log == Items.AIR) {
            log = Items.OAK_LOG;
        }

        return new LeafEntry.Builder[]{ItemEntry.builder(log)};
    }

    private static RegistryKey<LootTable> getLootTableId(String path) {
        return getLootTableId(BTWRSLMod.MOD_ID, path);
    }

    private static RegistryKey<LootTable> getLootTableId(String namespace, String path) {
        return RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(namespace, path));
    }

}