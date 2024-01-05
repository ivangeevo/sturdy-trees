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

        // Define the dimensions for each element
        double fromX1 = 7.0 / 16.0; double fromY1 = 0.0; double fromZ1 = 7.0 / 16.0;
        double toX1 = 9.0 / 16.0; double toY1 = 15.0 / 16.0; double toZ1 = 9.0 / 16.0;

        double fromX2 = 6.0 / 16.0; double fromY2 = 0.0; double fromZ2 = 6.0 / 16.0;
        double toX2 = 10.0 / 16.0; double toY2 = 13.0 / 16.0; double toZ2 = 10.0 / 16.0;

        double fromX3 = 5.0 / 16.0; double fromY3 = 0.0; double fromZ3 = 5.0 / 16.0;
        double toX3 = 11.0 / 16.0; double toY3 = 11.0 / 16.0; double toZ3 = 11.0 / 16.0;

        double fromX4 = 4.0 / 16.0; double fromY4 = 0.0; double fromZ4 = 4.0 / 16.0;
        double toX4 = 12.0 / 16.0; double toY4 = 9.0 / 16.0; double toZ4 = 12.0 / 16.0;

        double fromX5 = 3.0 / 16.0; double fromY5 = 0.0; double fromZ5 = 3.0 / 16.0;
        double toX5 = 13.0 / 16.0; double toY5 = 7.0 / 16.0; double toZ5 = 13.0 / 16.0;

        double fromX6 = 2.0 / 16.0; double fromY6 = 0.0; double fromZ6 = 2.0 / 16.0;
        double toX6 = 14.0 / 16.0; double toY6 = 5.0 / 16.0; double toZ6 = 14.0 / 16.0;

        double fromX7 = 1.0 / 16.0; double fromY7 = 0.0; double fromZ7 = 1.0 / 16.0;
        double toX7 = 15.0 / 16.0; double toY7 = 3.0 / 16.0; double toZ7 = 15.0 / 16.0;

        // Create VoxelShapes for each element
        VoxelShape shape1 = VoxelShapes.cuboid(fromX1, fromY1, fromZ1, toX1, toY1, toZ1);
        VoxelShape shape2 = VoxelShapes.cuboid(fromX2, fromY2, fromZ2, toX2, toY2, toZ2);
        VoxelShape shape3 = VoxelShapes.cuboid(fromX3, fromY3, fromZ3, toX3, toY3, toZ3);
        VoxelShape shape4 = VoxelShapes.cuboid(fromX4, fromY4, fromZ4, toX4, toY4, toZ4);
        VoxelShape shape5 = VoxelShapes.cuboid(fromX5, fromY5, fromZ5, toX5, toY5, toZ5);
        VoxelShape shape6 = VoxelShapes.cuboid(fromX6, fromY6, fromZ6, toX6, toY6, toZ6);
        VoxelShape shape7 = VoxelShapes.cuboid(fromX7, fromY7, fromZ7, toX7, toY7, toZ7);

        // Combine the shapes using VoxelShapes.union()
        return VoxelShapes.union(shape1, shape2, shape3, shape4, shape5, shape6, shape7);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        player.addExhaustion(0.2F);

        if (!world.isClient) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());

            Direction miningDirection = getMiningDirection(player, world, pos);


            if (miningDirection != null) {

                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), new LootContext.Builder((ServerWorld) world)
                        .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                        .parameter(LootContextParameters.TOOL, stack)
                        .random(world.random));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}