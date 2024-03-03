package ivangeevo.sturdy_trees.loot.context;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ModLootContextTypes  {

    private static final BiMap<Identifier, LootContextType> MAP = HashBiMap.create();
    public static final LootContextType DIRECTIONAL_DROPPING = register("directional_dropping", builder -> builder.require(LootContextParameters.ORIGIN).require(LootContextParameters.TOOL).allow(LootContextParameters.THIS_ENTITY));


    private static LootContextType register(String name, Consumer<LootContextType.Builder> type) {
        LootContextType.Builder builder = new LootContextType.Builder();
        type.accept(builder);
        LootContextType lootContextType = builder.build();
        Identifier identifier = new Identifier(name);
        LootContextType lootContextType2 = MAP.put(identifier, lootContextType);
        if (lootContextType2 != null) {
            throw new IllegalStateException("Loot table parameter set " + identifier + " is already registered");
        }
        return lootContextType;
    }}
