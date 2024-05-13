package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
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

public class LogSpikeBlock extends ConvertingLogBlock
{
    public LogSpikeBlock(Settings settings)
    {
        super(settings);
    }

    // TODO: FIX OUTLINE FOR THIS LOG BLOCK
    // Needs its top/bottom part cut by few pixels to account for spike's pointy part being different than parent's
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        int var = state.get(VARIATION);
        double offset = (2 + var) / 16.0;
        double to = 1.0 - offset;

        /**
        if (var == 2 && isDownBlocks(state))
        {
            double minY = 0.6;
            double maxY = 1f;
            return VoxelShapes.cuboid(offset, minY, offset, to, maxY, to);
        }
        else if (isUpBlocks(state))
        {
            double minY = 0;
            double maxY = 1f;
            return VoxelShapes.cuboid(offset, minY, offset, to, maxY, to);

        }
         **/

        // Create a VoxelShape based on the dimensions
        return VoxelShapes.cuboid(offset, 0, offset, to, 1.0f, to);
    }
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        int variation = state.get(VARIATION);

        if (world.isClient)
        {
            switch (variation)
            {
                case 1: world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                        1.25F + (player.getWorld().random.nextFloat() * 0.25F));
                case 2: world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                        1.25F + (player.getWorld().random.nextFloat() * 0.25F));
            }
        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

    private static boolean isUpBlocks(BlockState state)
    {
        return state.isOf(SturdyTreesBlocks.LOG_OAK_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP);
    }

    private static boolean isDownBlocks(BlockState state)
    {
        return state.isOf(SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN);
    }


}