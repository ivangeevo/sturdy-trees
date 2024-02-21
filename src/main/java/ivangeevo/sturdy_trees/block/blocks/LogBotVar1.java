package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingLogBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogBotVar1 extends ConvertingLogBlock implements LogBlockStacks {


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

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {


            LogType logType = getLogType(state);

            if (logType != null) {

                world.setBlockState(pos, SturdyTreesBlocks.LOG_BOT_VAR2.getDefaultState().with(LOG_TYPE, logType));
            }

            Direction miningDirection = getMiningDirection(player, world, pos);

            LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld)world)
                    .add(LootContextParameters.ORIGIN, pos.toCenterPos())
                    .add(LootContextParameters.BLOCK_STATE, state)
                    .add(LootContextParameters.TOOL, stack)
                    .build(LootContextTypes.BLOCK);


            if (miningDirection != null) {


                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos),  new LootContext.Builder(lootContextParameterSet).build(null));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}