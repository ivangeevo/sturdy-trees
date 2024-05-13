package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
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
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        int var = state.get(VARIATION);
        double offset = (1 + var) / 16.0;
        double to = 1.0 - offset;

        // Create a VoxelShape based on the dimensions
        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    // TODO: Fix breaking a log multiple times & then placing a block above it and breaking it again, resets to the default value here;
    // Maybe fixed ? Check again. 13MAY/24
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack)
    {


        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());

        // Logic to determine the block to replace with
        BlockState newState = getReplacementState(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, newState);

        int variation = state.get(VARIATION);


        if (world.isClient)
        {
            switch (variation)
            {
                case 0: world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                        1.25F + (player.getWorld().random.nextFloat() * 0.25F));
                case 2: world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                        1.25F + (player.getWorld().random.nextFloat() * 0.25F));
            }
        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);


    }

    private BlockState getReplacementState(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        Block strippedVar = null;
        Block chewedVar = null;
        Block spikeUpVar = null;
        Block spikeDownVar = null;

        // Assign the appropriate block variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_OAK_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_OAK_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_BIRCH_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_BIRCH_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_SPRUCE_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_JUNGLE_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_ACACIA_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_ACACIA_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_DARK_OAK_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_MANGROVE_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN;
        } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED)) {
            strippedVar = SturdyTreesBlocks.LOG_CHERRY_STRIPPED;
            chewedVar = SturdyTreesBlocks.LOG_CHERRY_CHEWED;
            spikeUpVar = SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP;
            spikeDownVar = SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow)
        {
            return chewedVar != null ? chewedVar.getDefaultState() : state;
        }
        else if (hasBlockAbove)
        {
            return spikeDownVar != null ? spikeDownVar.getDefaultState() : state;
        }
        else if (hasBlockBelow)
        {
            return spikeUpVar != null ? spikeUpVar.getDefaultState() : state;
        }
        else
        {
            // If the variation is 3 for stripped, break to air
            if (state.get(VARIATION) == 3)
            {
                return Blocks.AIR.getDefaultState();
            }
            // Default, choose the next stripped variation
            int var = state.get(VARIATION);
            return strippedVar != null ? strippedVar.getStateWithProperties(state.with(VARIATION, (var + 1) % 4)) : state;
        }
    }




}