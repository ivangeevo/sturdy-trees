package ivangeevo.sturdy_trees.mixin;


import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
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

    // Adds conditions to stripping logic
    @Inject(method = "useOnBlock", at = @At(value = "HEAD"), cancellable = true)
    private void injectedUseOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir)
    {

        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        PlayerEntity playerEntity = context.getPlayer();
        BlockState blockState = world.getBlockState(blockPos);
        Optional<BlockState> optional = this.getStrippedState(blockState);
        Optional<BlockState> optional2 = Oxidizable.getDecreasedOxidationState(blockState);
        Optional<BlockState> optional3 = Optional.ofNullable(HoneycombItem.WAXED_TO_UNWAXED_BLOCKS.get().get(blockState.getBlock())).map(block -> block.getStateWithProperties(blockState));
        ItemStack itemStack = context.getStack();
        Optional<Object> optional4 = Optional.empty();
        Random random = context.getWorld().getRandom();

        /** BTWR **/
        // Only modern (iron & above) axes can right click function.

        if (itemStack.isIn(SturdyTreesTags.Items.MODERN_AXES) && random.nextFloat() >= 0.2f)
        {

        if (optional.isPresent() )
        {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0f, 1.0f);
            optional4 = Optional.of(optional);
        }
        else if (optional2.isPresent())
        {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(playerEntity, WorldEvents.BLOCK_SCRAPED, blockPos, 0);
            optional4 = Optional.of(optional2);
        }
        else if (optional3.isPresent())
        {
            world.playSound(playerEntity, blockPos, SoundEvents.ITEM_AXE_WAX_OFF, SoundCategory.BLOCKS, 1.0f, 1.0f);
            world.syncWorldEvent(playerEntity, WorldEvents.WAX_REMOVED, blockPos, 0);
            optional4 = Optional.of(optional3);
        }
        if (optional4.isPresent()) {
            if (playerEntity instanceof ServerPlayerEntity) {
                Criteria.ITEM_USED_ON_BLOCK.trigger((ServerPlayerEntity) playerEntity, blockPos, itemStack);
            }
            world.setBlockState(blockPos, (BlockState) optional4.get(), Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
            world.emitGameEvent(GameEvent.BLOCK_CHANGE, blockPos, GameEvent.Emitter.of(playerEntity, (BlockState) optional4.get()));
            if (playerEntity != null) {
                itemStack.damage(5, playerEntity, p -> p.sendToolBreakStatus(context.getHand()));
            }
        }

            cir.setReturnValue( ActionResult.success(world.isClient) );
        }
        cir.setReturnValue( ActionResult.PASS );


    }

}
