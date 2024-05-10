package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
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


}