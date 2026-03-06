package org.btwr.sturdy_trees.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum LogCondition implements StringIdentifiable {
    NORMAL("normal"),
    STRIPPED("stripped"),
    SPIKE("spike"),
    CHEWED("chewed");

    private final String type;

    LogCondition(final String type) {
        this.type = type;
    }

    @Override
    public String asString() {
        return this.type;
    }

}