package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block implements LogBlockStacks {
    public PillarBlockMixin(Settings settings) {
        super(settings);
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        // Logic to determine BlockState to replace with
        BlockState replacementState = setStrippedState(state);
        world.setBlockState(pos, replacementState);

        // dropLootTable(world, pos, state, player);
    }



    @Unique
    private BlockState setStrippedState(BlockState state) {
        BlockState strippedVar0 = null;
        if (state.isOf(Blocks.OAK_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_OAK.getDefaultState();
        } else if (state.isOf(Blocks.BIRCH_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_BIRCH.getDefaultState();
        } else if (state.isOf(Blocks.SPRUCE_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_SPRUCE.getDefaultState();
        } else if (state.isOf(Blocks.BIRCH_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_BIRCH.getDefaultState();
        } else if (state.isOf(Blocks.JUNGLE_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_JUNGLE.getDefaultState();
        } else if (state.isOf(Blocks.ACACIA_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_ACACIA.getDefaultState();
        } else if (state.isOf(Blocks.DARK_OAK_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_DARK_OAK.getDefaultState();
        } else if (state.isOf(Blocks.MANGROVE_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_MANGROVE.getDefaultState();
        } else if (state.isOf(Blocks.CHERRY_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_CHERRY.getDefaultState();
        } else if (state.isOf(Blocks.WARPED_STEM)) {
            return SturdyTreesBlocks.LOG_STRIPPED_WARPED.getDefaultState();
        }
        return strippedVar0;
    }


}
