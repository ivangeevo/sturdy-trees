package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LogStrippedBlock extends ConvertingLogBlock
{


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

    private int getShapeForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 1; }
        else if (state.get(VARIATION) == 2) { return 2; }
        else if (state.get(VARIATION) == 3) { return 3; }

        return 0;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);


        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());


        // Logic to determine the block to replace with
        Block replacementBlock = determineReplacementBlock(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, replacementBlock.getDefaultState());

    }





    private Block determineReplacementBlock(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        Block strippedVar1 = null;

        Block chewedVar1 = null;

        Block spikeDownVar1 = null;

        Block spikeUpVar1 = null;



        // Assign the appropriate stripped variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_OAK_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_OAK_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_OAK_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_OAK_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_BIRCH_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_BIRCH_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_BIRCH_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_SPRUCE_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_SPRUCE_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_SPRUCE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_JUNGLE_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_JUNGLE_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_JUNGLE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_ACACIA_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_ACACIA_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_ACACIA_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_MANGROVE_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_MANGROVE_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_MANGROVE_SPIKE;
        } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_CHERRY_CHEWED;
            spikeDownVar1 = SturdyTreesBlocks.LOG_CHERRY_SPIKE;
            spikeUpVar1 = SturdyTreesBlocks.LOG_CHERRY_SPIKE;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            // Both block above and below, choose midVar1
            return chewedVar1;
        } else if (hasBlockAbove) {
            // Only block above, choose topVar2
            return spikeUpVar1;
        } else if (hasBlockBelow) {
            // Only block below, choose botVar2
            return spikeDownVar1;
        } else {
            // Default, choose strippedVar0
            return strippedVar1;
        }

    }











}