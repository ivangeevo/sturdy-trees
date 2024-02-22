package ivangeevo.sturdy_trees.block.util;

import net.minecraft.block.BlockState;
import net.minecraft.util.StringIdentifiable;

public enum LogType implements StringIdentifiable {
    OAK("oak"),
    BIRCH("birch"),
    SPRUCE("spruce"),
    JUNGLE("jungle"),
    ACACIA("acacia"),
    DARK_OAK("dark_oak"),
    MANGROVE("mangrove"),
    CHERRY("cherry"),
    WARPED("warped"),
    DEFAULT("default"); // Add a DEFAULT value


    private final String name;

    LogType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String asString() {
        return this.name;
    }
}
