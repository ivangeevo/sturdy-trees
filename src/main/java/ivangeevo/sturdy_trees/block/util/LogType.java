package ivangeevo.sturdy_trees.block.util;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.StringIdentifiable;

public enum LogType implements StringIdentifiable {
    OAK("oak", SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0),
    BIRCH("birch", SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0),
    SPRUCE("spruce", SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0),
    JUNGLE("jungle", SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0),
    ACACIA("acacia", SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0),
    DARK_OAK("dark_oak", SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0),
    MANGROVE("mangrove", SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0),

    CHERRY("cherry", SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR0),


    // FIX BLOCK -> NO WARPED VARIANT //
    WARPED("warped", SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0);

    private final String name;
    private final Block block;

    LogType(String name, Block block) {
        this.name = name;
        this.block = block;
    }

    public Block getBlock() {
        return this.block;
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
