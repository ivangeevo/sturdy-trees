package org.btwr.sturdy_trees.util;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import org.btwr.shared_library.api.loot.condition.DirectionalDropConditions;
import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;

import java.util.function.Predicate;

public class DirectionalDropManager {

    public static void register() {
        addCondition(
                stack -> !stack.isIn(BTWRConventionalTags.Items.AXES_HARVEST_FULL_BLOCK),
                DirectionalDropManager::isLogBlockOrVariant
        );
    }

    // We use the LOGS_THAT_BURN tag instead of LOGS, because crimson and warped stems do not have layered breaking
    private static boolean isLogBlockOrVariant(BlockState state) {
        return state.isIn(BlockTags.LOGS_THAT_BURN) || state.isIn(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS);
    }

    private static void addCondition(Predicate<ItemStack> stackCheck, Predicate<BlockState> stateCheck) {
        DirectionalDropConditions.register(stackCheck, stateCheck);
    }
}
