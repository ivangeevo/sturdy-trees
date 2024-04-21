package ivangeevo.sturdy_trees.block;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

import java.util.Collections;
import java.util.List;

public class TreeBreakManager implements LogBlockStacks
{

    public static void onAfterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, ItemStack tool)
    {
        if (state.isOf(Blocks.OAK_LOG)) {
            handleLogBreak(world, pos, state, player, tool, SturdyTreesBlocks.LOG_OAK_STRIPPED);
        } else if (state.isOf(Blocks.BIRCH_LOG)) {
            handleLogBreak(world, pos, state, player, tool,  SturdyTreesBlocks.LOG_BIRCH_STRIPPED);
        } else if (state.isOf(Blocks.SPRUCE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_SPRUCE_STRIPPED);
        } else if (state.isOf(Blocks.JUNGLE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_JUNGLE_STRIPPED);
        } else if (state.isOf(Blocks.ACACIA_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_ACACIA_STRIPPED);
        } else if (state.isOf(Blocks.DARK_OAK_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED);
        } else if (state.isOf(Blocks.MANGROVE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_MANGROVE_STRIPPED);
        } else if (state.isOf(Blocks.CHERRY_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_CHERRY_STRIPPED);
        }
    }

    private static void handleLogBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack tool, Block... logVariants)
    {
        boolean isAxe = (tool.isOf(Items.STONE_AXE) || tool.isOf(Items.IRON_AXE) || tool.isOf(Items.DIAMOND_AXE) ||
                tool.isOf(Items.NETHERITE_AXE) || tool.isIn(SturdyTreesTags.Items.MODDED_AXES));

        if (isAxe)
        {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
        else
        {
            Block strippedLog = logVariants[0];
            world.setBlockState(pos, strippedLog.getDefaultState());

            for (int i = 0; i < logVariants.length - 1; i++)
            {
                if (state.isOf(logVariants[i]))
                {
                    world.setBlockState(pos, logVariants[i + 1].getDefaultState());
                    break;
                }
            }
        }

    }
}
