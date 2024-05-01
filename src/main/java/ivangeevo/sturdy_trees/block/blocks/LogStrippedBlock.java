package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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

    /**
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        // Define the dimensions from the model JSON
        double fromX = 1.0 / 16.0; double fromY = 0.0; double fromZ = 1.0 / 16.0;
        double toX = 15.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 15.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }
    **/

    private int getShapeForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 1; }
        else if (state.get(VARIATION) == 2) { return 2; }
        else if (state.get(VARIATION) == 3) { return 3; }

        return 0;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack)
    {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

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
                case 0: player.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.1F, 1.25F + (player.getWorld().random.nextFloat() * 0.25F) );
                case 2: player.playSound(SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 0.1F, 1.25F + (player.getWorld().random.nextFloat() * 0.25F) );
            }
        }

    }





    private BlockState getReplacementState(BlockState state, BlockState blockBelowState, BlockState blockAboveState)
    {

        Block strippedVar1 = null;
        Block chewedVar1 = null;
        Block spikeUpVar1 = null;
        Block spikeDownVar1 = null;


        // Assign the appropriate stripped variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_OAK_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_OAK_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_OAK_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_BIRCH_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_SPRUCE_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_JUNGLE_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_ACACIA_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_MANGROVE_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN;

        } else if (state.isOf(SturdyTreesBlocks.LOG_CHERRY_STRIPPED)) {
            strippedVar1 = SturdyTreesBlocks.LOG_CHERRY_STRIPPED;
            chewedVar1 = SturdyTreesBlocks.LOG_CHERRY_CHEWED;
            spikeUpVar1 = SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP;
            spikeDownVar1 = SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN;

        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow)
        {
            return chewedVar1.getDefaultState();
        }
        else if (hasBlockAbove)
        {
            return spikeDownVar1.getDefaultState();
        }
        else if (hasBlockBelow)
        {
            return spikeUpVar1.getDefaultState();
        }
            // Default, choose strippedVar0
            int var = state.get(VARIATION);
            assert strippedVar1 != null;
            return strippedVar1.getDefaultState().with(VARIATION, var + 1);


    }











}