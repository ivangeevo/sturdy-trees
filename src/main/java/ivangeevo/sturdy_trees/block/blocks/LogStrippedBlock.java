package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.util.LogType;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LogStrippedBlock extends ConvertingLogBlock
{
    public static final EnumProperty<LogType> LOG_TYPE = EnumProperty.of("log_type", LogType.class);


    public LogStrippedBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 1.0 / 16.0; double fromY = 0.0; double fromZ = 1.0 / 16.0;
        double toX = 15.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 15.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);


        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());


        // Logic to determine the block to replace with
        Block replacementBlock = determineReplacementBlock(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, replacementBlock.getDefaultState());

        dropLootTable(world, pos, state, player);
    }





    private Block determineReplacementBlock(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        Block strippedVar0 = null;
        Block strippedVar1 = null;
        Block strippedVar2 = null;
        Block strippedVar3 = null;

        Block midVar1 = null;
        Block midVar2 = null;
        Block midVar3 = null;

        Block botVar1 = null;
        Block botVar2 = null;
        Block botVar3 = null;

        Block topVar1 = null;
        Block topVar2 = null;
        Block topVar3 = null;



        // Assign the appropriate stripped variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_OAK_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_OAK_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_OAK_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_OAK_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_OAK_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_OAK_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_OAK_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_OAK_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_OAK_TOP_VAR3;

        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR3;


        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_CHERRY_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_CHERRY_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_CHERRY_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_CHERRY_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_CHERRY_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_CHERRY_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_CHERRY_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_CHERRY_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_CHERRY_TOP_VAR3;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            // Both block above and below, choose midVar1
            return midVar1;
        } else if (hasBlockAbove) {
            // Only block above, choose topVar2
            return topVar1;
        } else if (hasBlockBelow) {
            // Only block below, choose botVar2
            return botVar1;
        } else {
            // Default, choose strippedVar0
            return strippedVar1;
        }

    }











}