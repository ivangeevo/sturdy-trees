package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.block.TreeBreakManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin
{

    @Inject(method = "afterBreak", at = @At("HEAD"))
    private void handleModLogBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack tool, CallbackInfo ci)
    {
        if (state.isIn(BlockTags.LOGS))
        {
            TreeBreakManager.onAfterBreak(world, player, pos, state, tool);
        }
    }




}
