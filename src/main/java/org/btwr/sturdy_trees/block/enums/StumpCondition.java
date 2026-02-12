package org.btwr.sturdy_trees.block.enums;

import net.minecraft.util.StringIdentifiable;

public enum StumpCondition implements StringIdentifiable {
    NORMAL("normal"),
    CRAFTING("crafting"),
    STRIPPED("stripped"),
    SPIKE("spike"),
    CHEWED("chewed");

    private final String type;

    StumpCondition(final String type) {
        this.type = type;
    }

    @Override
    public String asString() {
        return this.type;
    }

}