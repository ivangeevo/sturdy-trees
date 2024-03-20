package ivangeevo.sturdy_trees.block.util;


import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.sound.BlockSoundGroup;

import java.util.Set;

public record StumpType(String name, BlockSoundGroup soundType, BlockSoundGroup hangingSignSoundType) {
    private static final Set<StumpType> VALUES = new ObjectArraySet<>();
    public static final StumpType OAK;
    public static final StumpType SPRUCE;
    public static final StumpType BIRCH;
    public static final StumpType ACACIA;
    public static final StumpType JUNGLE;
    public static final StumpType DARK_OAK;
    public static final StumpType CRIMSON;
    public static final StumpType WARPED;
    public static final StumpType MANGROVE;
    public static final StumpType CHERRY;


    public StumpType(String name) {
        this(name, BlockSoundGroup.WOOD, BlockSoundGroup.HANGING_ROOTS);
    }
    
    private static StumpType register(StumpType type) {
        VALUES.add(type);
        return type;
    }


    public String name() {
        return this.name;
    }


    static {
        OAK = register(new StumpType("oak"));
        SPRUCE = register(new StumpType("spruce"));
        BIRCH = register(new StumpType("birch"));
        ACACIA = register(new StumpType("acacia"));
        JUNGLE = register(new StumpType("jungle"));
        DARK_OAK = register(new StumpType("dark_oak"));
        CRIMSON = register(new StumpType("crimson"));
        WARPED = register(new StumpType("warped"));
        MANGROVE = register(new StumpType("mangrove"));
        CHERRY = register(new StumpType("cherry"));

    }
}
