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

public class LogMidVar3 extends ConvertingBlock implements LogBlockStacks {

    public LogMidVar3(Settings settings) {
        super(settings);

    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions for each element
        double topSideFromX = 1.0 / 16.0;double topSideFromY = 13.0 / 16.0;double topSideFromZ = 1.0 / 16.0;
        double topSideToX = 15.0 / 16.0;double topSideToY = 16.0 / 16.0;double topSideToZ = 15.0 / 16.0;

        double middleTopFromX = 2.0 / 16.0;double middleTopFromY = 11.0 / 16.0;double middleTopFromZ = 2.0 / 16.0;
        double middleTopToX = 14.0 / 16.0;double middleTopToY = 13.0 / 16.0;double middleTopToZ = 14.0 / 16.0;

        double middleTopMidFromX = 3.0 / 16.0;double middleTopMidFromY = 9.0 / 16.0;double middleTopMidFromZ = 3.0 / 16.0;
        double middleTopMidToX = 13.0 / 16.0;double middleTopMidToY = 11.0 / 16.0;double middleTopMidToZ = 13.0 / 16.0;

        double middleFromX = 4.0 / 16.0;double middleFromY = 7.0 / 16.0;double middleFromZ = 4.0 / 16.0;
        double middleToX = 12.0 / 16.0;double middleToY = 9.0 / 16.0;double middleToZ = 12.0 / 16.0;

        double middleBotMidFromX = 3.0 / 16.0;double middleBotMidFromY = 5.0 / 16.0;double middleBotMidFromZ = 3.0 / 16.0;
        double middleBotMidToX = 13.0 / 16.0;double middleBotMidToY = 7.0 / 16.0;double middleBotMidToZ = 13.0 / 16.0;

        double middleBotFromX = 2.0 / 16.0;double middleBotFromY = 3.0 / 16.0;double middleBotFromZ = 2.0 / 16.0;
        double middleBotToX = 14.0 / 16.0;double middleBotToY = 5.0 / 16.0;double middleBotToZ = 14.0 / 16.0;

        double bottomSideFromX = 1.0 / 16.0;double bottomSideFromY = 0.0 / 16.0;double bottomSideFromZ = 1.0 / 16.0;
        double bottomSideToX = 15.0 / 16.0;double bottomSideToY = 3.0 / 16.0;double bottomSideToZ = 15.0 / 16.0;

        // Create VoxelShapes for each element
        VoxelShape topSideShape = VoxelShapes.cuboid(topSideFromX, topSideFromY, topSideFromZ, topSideToX, topSideToY, topSideToZ);
        VoxelShape middleTopShape = VoxelShapes.cuboid(middleTopFromX, middleTopFromY, middleTopFromZ, middleTopToX, middleTopToY, middleTopToZ);
        VoxelShape middleTopMidShape = VoxelShapes.cuboid(middleTopMidFromX, middleTopMidFromY, middleTopMidFromZ, middleTopMidToX, middleTopMidToY, middleTopMidToZ);
        VoxelShape middleShape = VoxelShapes.cuboid(middleFromX, middleFromY, middleFromZ, middleToX, middleToY, middleToZ);
        VoxelShape middleBotMidShape = VoxelShapes.cuboid(middleBotMidFromX, middleBotMidFromY, middleBotMidFromZ, middleBotMidToX, middleBotMidToY, middleBotMidToZ);
        VoxelShape middleBotShape = VoxelShapes.cuboid(middleBotFromX, middleBotFromY, middleBotFromZ, middleBotToX, middleBotToY, middleBotToZ);
        VoxelShape bottomSideShape = VoxelShapes.cuboid(bottomSideFromX, bottomSideFromY, bottomSideFromZ, bottomSideToX, bottomSideToY, bottomSideToZ);

        // Combine the VoxelShapes into a single shape

        return VoxelShapes.union(topSideShape, middleTopShape, middleTopMidShape, middleShape, middleBotMidShape, middleBotShape, bottomSideShape);
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());

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