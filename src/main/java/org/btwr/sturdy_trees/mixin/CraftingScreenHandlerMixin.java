package org.btwr.sturdy_trees.mixin;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.CraftingScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin {

    @Shadow @Final private ScreenHandlerContext context;

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void onCanUse(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(tryUseCraftingTables(this.context, player));
    }

    // Checks against the Fabric API common block tag for crafting tables
    @Unique
    private static boolean tryUseCraftingTables(ScreenHandlerContext context, PlayerEntity player) {
        return context.get((world, pos) -> {
            if (!world.getBlockState(pos).isIn(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES)) {
                return false;
            }
            return player.canInteractWithBlockAt(pos, 4.0);
        }, true);
    }

}