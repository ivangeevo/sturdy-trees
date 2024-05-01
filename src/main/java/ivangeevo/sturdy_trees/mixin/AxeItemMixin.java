package ivangeevo.sturdy_trees.mixin;


import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {


    @Shadow protected abstract Optional<BlockState> getStrippedState(BlockState state);

    public AxeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }

    // Removes stripping logic
    @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
    private void injectedUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir)
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> optional = this.getStrippedState(blockState);

        // check for the stripping logic and cancel if the optional is present.
        if (optional.isPresent())
        {
            cir.setReturnValue(ActionResult.FAIL);
        }
        else
        {
            cir.setReturnValue(ActionResult.PASS);
        }

    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        if (state.isIn(BlockTags.LEAVES))
        {
            return super.getMiningSpeedMultiplier(stack, state) * 2.5f;
        }

        return super.getMiningSpeedMultiplier(stack, state);
    }

    // Method to calculate random chance of conversion
    private boolean canConvertWithChance(World world) {
        return world.getRandom().nextFloat() >= 0.2f;
    }

}
