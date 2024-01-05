package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
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

import java.util.List;

public class LogBotVar1 extends ConvertingBlock implements LogBlockStacks {


    public LogBotVar1(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions for the first element
        double fromX1 = 2.0 / 16.0; double fromY1 = 0.0; double fromZ1 = 2.0 / 16.0;
        double toX1 = 14.0 / 16.0; double toY1 = 16.0 / 16.0; double toZ1 = 14.0 / 16.0;

        // Define the dimensions for the second element
        double fromX2 = 1.0 / 16.0; double fromY2 = 0.0; double fromZ2 = 1.0 / 16.0;
        double toX2 = 15.0 / 16.0; double toY2 = 3.0 / 16.0; double toZ2 = 15.0 / 16.0;

        // Create VoxelShapes for each element
        VoxelShape shape1 = VoxelShapes.cuboid(fromX1, fromY1, fromZ1, toX1, toY1, toZ1);
        VoxelShape shape2 = VoxelShapes.cuboid(fromX2, fromY2, fromZ2, toX2, toY2, toZ2);

        // Combine the shapes using VoxelShapes.union()

        return VoxelShapes.union(shape1, shape2);
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.addExhaustion(0.2F);
        if (!world.isClient) {
            if (state.isOf(SturdyTreesBlocks.LOG_OAK_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_OAK_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_BIRCH_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_ACACIA_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR2.getDefaultState());
            }
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