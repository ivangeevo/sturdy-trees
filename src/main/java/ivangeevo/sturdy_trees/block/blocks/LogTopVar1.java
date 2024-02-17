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

public class LogTopVar1 extends ConvertingBlock implements LogBlockStacks {




    public LogTopVar1(Settings settings) {
        super(settings);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double sideFromX = 1.0 / 16.0; double sideFromY = 13.0 / 16.0; double sideFromZ = 1.0 / 16.0;
        double sideToX = 15.0 / 16.0; double sideToY = 16.0 / 16.0; double sideToZ = 15.0 / 16.0;

        double lowerFromX = 2.0 / 16.0; double lowerFromY = 0.0;double lowerFromZ = 2.0 / 16.0;
        double lowerToX = 14.0 / 16.0; double lowerToY = 13.0 / 16.0; double lowerToZ = 14.0 / 16.0;

        // Create VoxelShapes for the side and lower parts of the block
        VoxelShape sideShape = VoxelShapes.cuboid(sideFromX, sideFromY, sideFromZ, sideToX, sideToY, sideToZ);
        VoxelShape lowerShape = VoxelShapes.cuboid(lowerFromX, lowerFromY, lowerFromZ, lowerToX, lowerToY, lowerToZ);

        // Combine the shapes using VoxelShapes.union
        VoxelShape shape = VoxelShapes.union(sideShape, lowerShape);

        return shape;
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {
            if (state.isOf(SturdyTreesBlocks.LOG_OAK_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_OAK_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_BIRCH_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_ACACIA_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_TOP_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_CHERRY_TOP_VAR2.getDefaultState());
            }
            Direction miningDirection = getMiningDirection(player, world, pos);


            if (miningDirection != null) {

                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), buildBlockLootContext((ServerWorld) world,pos,state, stack));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}