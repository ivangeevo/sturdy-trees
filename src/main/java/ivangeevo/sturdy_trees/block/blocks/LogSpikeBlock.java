package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
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

public class LogSpikeBlock extends ConvertingLogBlock
{
    public static final EnumProperty<Direction> DIRECTION = EnumProperty.of("direction", Direction.class);
    public LogSpikeBlock(Settings settings) { super(settings); }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        VoxelShape shape = VoxelShapes.empty();
        int varInt = getShapeForState(state);

        // Define dimensions for each element
        for (int i = varInt; i <= 2; i++)
        {
            // Calculate 'from' coordinates dynamically
            double fromX = (3 - i) / 16.0;
            double fromY = 0.0;
            double fromZ = (3 - i) / 16.0;

            // Calculate 'to' coordinates dynamically
            double toX = (13 + i) / 16.0;
            double toY = (16 - i) / 16.0;
            double toZ = (13 + i) / 16.0;

            // Create VoxelShape for each element and add to the main shape
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ));
        }

        return shape;
    }

    private int getShapeForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 1; }
        else if (state.get(VARIATION) == 2) { return 2; }

        return 0;
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

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
            } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_BOT_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_CHERRY_BOT_VAR2.getDefaultState());
            }
            Direction miningDirection = getMiningDirection(player, world, pos);

            LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld)world)
                    .add(LootContextParameters.ORIGIN, pos.toCenterPos())
                    .add(LootContextParameters.BLOCK_STATE, state)
                    .add(LootContextParameters.TOOL, stack)
                    .build(LootContextTypes.BLOCK);


            if (miningDirection != null)
            {


                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos),  new LootContext.Builder(lootContextParameterSet).build(null));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}