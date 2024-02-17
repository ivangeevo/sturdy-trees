package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LogStrippedVar1 extends ConvertingBlock implements LogBlockStacks {

    public LogStrippedVar1(AbstractBlock.Settings settings) {
        super(settings);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 2.0 / 16.0; double fromY = 0.0; double fromZ = 2.0 / 16.0;
        double toX = 14.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 14.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR2.getDefaultState());
    } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR1)) {
        world.setBlockState(pos, SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR2.getDefaultState());
    }

        // Set the new block state

        Direction miningDirection = getMiningDirection(player, world, pos);


        if (miningDirection != null) {


            List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), buildBlockLootContext((ServerWorld) world,pos,state, stack));


            for (ItemStack itemStack : droppedStacks) {
                dropStack(world, pos, miningDirection, itemStack);
            }
        }
    }


    private List<ItemStack> getLesserDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(SturdyTreesItems.DUST_SAW, 1));
        return droppedStacks;
    }

}