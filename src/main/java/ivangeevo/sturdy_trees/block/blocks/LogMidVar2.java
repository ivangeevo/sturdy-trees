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

public class LogMidVar2 extends ConvertingBlock implements LogBlockStacks, SideModUtils {

    public LogMidVar2(Settings settings) {
        super(settings);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions for each element
        double topSideFromX = 1.0 / 16.0;double topSideFromY = 13.0 / 16.0;double topSideFromZ = 1.0 / 16.0;
        double topSideToX = 15.0 / 16.0;double topSideToY = 16.0 / 16.0;double topSideToZ = 15.0 / 16.0;

        double middleTopFromX = 2.0 / 16.0;double middleTopFromY = 11.0 / 16.0;double middleTopFromZ = 2.0 / 16.0;
        double middleTopToX = 14.0 / 16.0;double middleTopToY = 13.0 / 16.0;double middleTopToZ = 14.0 / 16.0;

        double middleFromX = 3.0 / 16.0;double middleFromY = 5.0 / 16.0;double middleFromZ = 3.0 / 16.0;
        double middleToX = 13.0 / 16.0;double middleToY = 11.0 / 16.0;double middleToZ = 13.0 / 16.0;

        double middleBotFromX = 2.0 / 16.0;double middleBotFromY = 3.0 / 16.0;double middleBotFromZ = 2.0 / 16.0;
        double middleBotToX = 14.0 / 16.0;double middleBotToY = 5.0 / 16.0;double middleBotToZ = 14.0 / 16.0;

        double bottomSideFromX = 1.0 / 16.0;double bottomSideFromY = 0.0;double bottomSideFromZ = 1.0 / 16.0;
        double bottomSideToX = 15.0 / 16.0;double bottomSideToY = 3.0 / 16.0;double bottomSideToZ = 15.0 / 16.0;

        // Create the VoxelShapes for each element
        VoxelShape topSideShape = VoxelShapes.cuboid(topSideFromX, topSideFromY, topSideFromZ, topSideToX, topSideToY, topSideToZ);
        VoxelShape middleTopShape = VoxelShapes.cuboid(middleTopFromX, middleTopFromY, middleTopFromZ, middleTopToX, middleTopToY, middleTopToZ);
        VoxelShape middleShape = VoxelShapes.cuboid(middleFromX, middleFromY, middleFromZ, middleToX, middleToY, middleToZ);
        VoxelShape middleBotShape = VoxelShapes.cuboid(middleBotFromX, middleBotFromY, middleBotFromZ, middleBotToX, middleBotToY, middleBotToZ);
        VoxelShape bottomSideShape = VoxelShapes.cuboid(bottomSideFromX, bottomSideFromY, bottomSideFromZ, bottomSideToX, bottomSideToY, bottomSideToZ);

        // Combine the VoxelShapes into a single shape

        return VoxelShapes.union(topSideShape, middleTopShape, middleShape, middleBotShape, bottomSideShape);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {
            if (state.isOf(SturdyTreesBlocks.LOG_OAK_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_OAK_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_BIRCH_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_SPRUCE_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_JUNGLE_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_ACACIA_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR3.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_MID_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_MANGROVE_MID_VAR3.getDefaultState());
            }
            Direction miningDirection = getMiningDirection(player, world, pos);


            if (miningDirection != null) {

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