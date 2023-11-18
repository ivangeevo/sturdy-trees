package ivangeevo.sturdy_trees.mixin;


import net.minecraft.block.*;
import net.minecraft.state.property.IntProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block
        implements Waterloggable {

    @Shadow
    @Final
    public static IntProperty DISTANCE;

    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    //@Inject(method = "getDistanceFromLog", at = @At("HEAD"), cancellable = true)
    private static void injectedGetDistanceFromLog(BlockState state, CallbackInfoReturnable<Integer> cir) {
        if (state.isOf(Blocks.OAK_LOG)) {
            cir.setReturnValue(0);
        }
        if (state.getBlock() instanceof LeavesBlock) {
            cir.setReturnValue(state.get(DISTANCE));
        }
        cir.setReturnValue(7);

    }
}
