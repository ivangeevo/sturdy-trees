package ivangeevo.sturdy_trees.block.blocks.old;

import ivangeevo.sturdy_trees.ConvertingLogBlock;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogStrippedVar3 extends ConvertingLogBlock implements LogBlockStacks {

    public LogStrippedVar3(AbstractBlock.Settings settings) {
        super(settings);


    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 4.0 / 16.0; double fromY = 0.0; double fromZ = 4.0 / 16.0;
        double toX = 12.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 12.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }






    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        // Set the new block state
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        Direction miningDirection = getMiningDirection(player, world, pos);

        LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld)world)
                .add(LootContextParameters.ORIGIN, pos.toCenterPos())
                .add(LootContextParameters.BLOCK_STATE, state)
                .add(LootContextParameters.TOOL, stack).build(LootContextTypes.BLOCK);


        if (miningDirection != null) {

            List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), new LootContext.Builder(lootContextParameterSet).build(null));


            for (ItemStack itemStack : droppedStacks) {
                dropStack(world, pos, miningDirection, itemStack);
            }
        }
    }




}