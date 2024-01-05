package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.loader.api.FabricLoader;
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

public class LogStrippedVar2 extends ConvertingBlock implements LogBlockStacks, SideModUtils {




    public LogStrippedVar2(Settings settings) {
        super(settings);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 3.0 / 16.0; double fromY = 0.0; double fromZ = 3.0 / 16.0;
        double toX = 13.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 13.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        player.addExhaustion(0.2F);



        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR3.getDefaultState());
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR2)) {
            world.setBlockState(pos, SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR3.getDefaultState());
        }
        Direction miningDirection = getMiningDirection(player, world, pos);


        if (miningDirection != null) {


            if (isBTWRLoaded) {
                List<ItemStack> droppedStacks = getLesserDroppedShaftStacks(world.getBlockState(pos), new LootContext.Builder((ServerWorld) world)
                        .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                        .parameter(LootContextParameters.TOOL, stack)
                        .random(world.random));


                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            } else {
                List<ItemStack> droppedStacks = getLesserDroppedStickStacks(world.getBlockState(pos), new LootContext.Builder((ServerWorld) world)
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