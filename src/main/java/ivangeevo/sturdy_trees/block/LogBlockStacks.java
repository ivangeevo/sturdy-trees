package ivangeevo.sturdy_trees.block;

import ivangeevo.sturdy_trees.SturdyTreesItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;

import java.util.ArrayList;
import java.util.List;

public interface LogBlockStacks {

    /** Private methods **/

    // Static methods

    private static ItemStack getItemStack(Item item, int count) {
        return new ItemStack(item, count);
    }

    private static ItemStack getBlockStack(Block block, int count) {
        Block blockItem = Block.getBlockFromItem(Item.fromBlock(block));
        return new ItemStack(blockItem, count);
    }

    // ItemStack methods


    private List<ItemStack> getLesserDroppedStacks(Item item, int count) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(getItemStack(item, count));
        return droppedStacks;
    }

    private List<ItemStack> getLesserDroppedStacks(Block block, int count) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(getBlockStack(block, count));
        return droppedStacks;
    }

    private List<ItemStack> getFullDroppedStacks(Block block, int count) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(getBlockStack(block, count));
        return droppedStacks;
    }

    /**                **/



    /** ItemStack methods **/

    // Lesser dropped stacks

    default List<ItemStack> getLesserDroppedSawStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.DUST_SAW, 1);
    }

    default List<ItemStack> getLesserDroppedShaftStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.SHAFT, 1);
    }

    default List<ItemStack> getLesserDroppedStickStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.STICK, 1);
    }

    default List<ItemStack> getLesserDroppedBarkStacksOak(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_OAK, 1);
    }

    default List<ItemStack> getLesserDroppedBarkStacksBirch(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_BIRCH, 1);
    }
    default List<ItemStack> getLesserDroppedBarkStacksSpruce(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_SPRUCE, 1);
    }
    default List<ItemStack> getLesserDroppedBarkStacksJungle(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_JUNGLE, 1);
    }
    default List<ItemStack> getLesserDroppedBarkStacksAcacia(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_ACACIA, 1);
    }
    default List<ItemStack> getLesserDroppedBarkStacksDarkOak(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_DARK_OAK, 1);
    }
    default List<ItemStack> getLesserDroppedBarkStacksMangrove(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_MANGROVE, 1);
    }

    // Dropped plank stacks

    default List<ItemStack> getDroppedOakPlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.OAK_PLANKS, 1);
    }

    default List<ItemStack> getDroppedBirchPlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.BIRCH_PLANKS, 1);
    }

    default List<ItemStack> getDroppedSprucePlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.SPRUCE_PLANKS, 1);
    }

    default List<ItemStack> getDroppedJunglePlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.JUNGLE_PLANKS, 1);
    }

    default List<ItemStack> getDroppedAcaciaPlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.ACACIA_PLANKS, 1);
    }

    default List<ItemStack> getDroppedDarkOakPlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(Items.DARK_OAK_PLANKS, 1);
    }

    default List<ItemStack> getDroppedMangrovePlankStacks(BlockState state, LootContext.Builder builder) {
        return getLesserDroppedStacks(SturdyTreesItems.BARK_MANGROVE, 1); // Replace with your plank item
    }


    // Full dropped stacks

    default List<ItemStack> getFullDroppedStacksOak(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.OAK_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksBirch(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.BIRCH_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksSpruce(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.SPRUCE_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksJungle(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.JUNGLE_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksAcacia(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.ACACIA_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksDarkOak(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.DARK_OAK_LOG, 1);
    }

    default List<ItemStack> getFullDroppedStacksMangrove(BlockState state, LootContext.Builder builder) {
        return getFullDroppedStacks(Blocks.MANGROVE_LOG, 1);
    }

    /**                  **/




}
