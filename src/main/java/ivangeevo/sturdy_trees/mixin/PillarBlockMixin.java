package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Collections;
import java.util.List;

import static ivangeevo.sturdy_trees.ConvertingLogBlock.LOG_TYPE;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block implements LogBlockStacks {
    public PillarBlockMixin(Settings settings) {
        super(settings);
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.addExhaustion(0.2F);

        // Check if the broken block is a vanilla oak log
        if (state.isOf(Blocks.OAK_LOG)) {
            // Get the direction of the broken log (used as a placeholder)
            BlockState strippedVariant = setStrippedBlockState(state).getDefaultState();

            // Set the block to the stripped variant with the corresponding LogType
            world.setBlockState(pos, strippedVariant, 3);

            // Optionally, drop the corresponding stripped log item
            if (!world.isClient && world instanceof ServerWorld) {
                ItemStack strippedLog = new ItemStack(SturdyTreesBlocks.LOG_STRIPPED_VAR0.asItem());
                Block.dropStack(world, pos, strippedLog);
            }
        }
    }

    @Unique
    private Block setStrippedBlockState(BlockState state) {
        if (state.isOf(Blocks.OAK_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.OAK).getBlock();
        } else if (state.isOf(Blocks.BIRCH_LOG)) {
            return SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.BIRCH).getBlock();
        }
        return SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.OAK).getBlock();
    }

}
