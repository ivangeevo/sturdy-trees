package ivangeevo.sturdy_trees.block;

import ivangeevo.sturdy_trees.SturdyTreesItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;

import java.util.ArrayList;
import java.util.List;

public interface LogBlockStacks {

    default List<ItemStack> getLesserDroppedSawStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(SturdyTreesItems.DUST_SAW, 1));
        return droppedStacks;
    }

    default List<ItemStack> getLesserDroppedShaftStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(SturdyTreesItems.SHAFT, 1));
        return droppedStacks;
    }

    default List<ItemStack> getLesserDroppedStickStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.STICK, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedOakPlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.OAK_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedBirchPlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.BIRCH_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedSprucePlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.SPRUCE_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedJunglePlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.JUNGLE_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedAcaciaPlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.ACACIA_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedDarkOakPlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.DARK_OAK_PLANKS, 1));
        return droppedStacks;
    }

    default List<ItemStack> getDroppedMangrovePlankStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(Items.MANGROVE_PLANKS, 1));
        return droppedStacks;
    }


    default List<ItemStack> getLesserDroppedStacksOak(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_OAK, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksBirch(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_BIRCH, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksSpruce(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_SPRUCE, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksJungle(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_JUNGLE, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksAcacia(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_ACACIA, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksDarkOak(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_DARK_OAK, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }
    default List<ItemStack> getLesserDroppedStacksMangrove(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack barkStack = new ItemStack(SturdyTreesItems.BARK_MANGROVE, 1); // Replace with your bark item
        droppedStacks.add(barkStack);
        return droppedStacks;
    }


    // Full dropped stacks
    default List<ItemStack> getFullDroppedStacksOak(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.OAK_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksBirch(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.BIRCH_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksSpruce(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.SPRUCE_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksJungle(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.JUNGLE_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksAcacia(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.ACACIA_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksDarkOak(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.DARK_OAK_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }
    default List<ItemStack> getFullDroppedStacksMangrove(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        ItemStack logStack = new ItemStack(Blocks.MANGROVE_LOG, 1); // Replace with your log item
        droppedStacks.add(logStack);
        return droppedStacks;
    }


}
