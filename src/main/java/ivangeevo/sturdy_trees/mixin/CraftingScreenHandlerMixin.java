package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import net.minecraft.block.Blocks;
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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CraftingScreenHandler.class)
public abstract class CraftingScreenHandlerMixin extends AbstractRecipeScreenHandler<CraftingRecipeInput, CraftingRecipe>
{

    @Shadow @Final private ScreenHandlerContext context;

    public CraftingScreenHandlerMixin(ScreenHandlerType<?> screenHandlerType, int i) {
        super(screenHandlerType, i);
    }

    @Inject(method = "canUse", at = @At("HEAD"), cancellable = true)
    private void onCanUse(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
                CraftingScreenHandler.canUse(this.context, player, Blocks.CRAFTING_TABLE)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_OAK_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_SPRUCE_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_BIRCH_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_JUNGLE_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_ACACIA_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_DARK_OAK_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_MANGROVE_CRAFTING)
                        || CraftingScreenHandler.canUse(this.context, player, SturdyTreesBlocks.STUMP_CHERRY_CRAFTING)
        );
    }

}
