/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package net.ivangeevo.btwr.core.tag;

import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class SturdyTreesBlockTags {

    public static final TagKey<Block> STRIPPED_MODDED_LOGS = SturdyTreesBlockTags.of("stripped_modded_logs");


    private SturdyTreesBlockTags() {
    }

    private static TagKey<Block> of(String id) {
        return TagKey.of(Registry.BLOCK_KEY, new Identifier(id));
    }
}

