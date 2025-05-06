package ivangeevo.sturdy_trees.block.blocks;

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

public class LogChewedBlock extends ConvertingLogBlock {

    public LogChewedBlock(Settings settings)
    {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
        if (!world.isClient()) {
            if (state.get(VARIATION) == 1) {
                this.playSpecialBreakSound(world, pos, player);
            }
        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);
    }

}