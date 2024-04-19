package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

import java.util.List;

public class LogBotVar3 extends ConvertingBlock implements LogBlockStacks {


    public LogBotVar3(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape shape = VoxelShapes.empty();

        // Define dimensions for each element
        for (int i = 1; i <= 7; i++) {
            double fromX = (8 - i) / 16.0; // Calculate 'from' coordinates dynamically
            double fromY = 0.0;
            double fromZ = (8 - i) / 16.0;
            double toX = (8 + i) / 16.0;   // Calculate 'to' coordinates dynamically
            double toY = (16 - i) / 16.0;
            double toZ = (8 + i) / 16.0;

            // Create VoxelShape for each element and add to the main shape
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ));
        }

        return shape;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());

            Direction miningDirection = getMiningDirection(player, world, pos);


            if (miningDirection != null) {

                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), LogBlockStacks.buildBlockLootContext((ServerWorld) world,pos,state, stack));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}