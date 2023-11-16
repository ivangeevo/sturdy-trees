package ivangeevo.sturdy_trees.mixin;


import ivangeevo.sturdy_trees.block.blocks.LogBlock;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block
        implements Waterloggable {
    @Shadow protected abstract boolean shouldDecay(BlockState state);

    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

   // @Inject(method = "randomTick", at = @At("HEAD"), cancellable = true)
    private void injectedRandomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        if (this.shouldDecay(state) && (!(state.getBlock() instanceof LogBlock))) {
            LeavesBlock.dropStacks(state, world, pos);
            world.removeBlock(pos, false);
        }
        ci.cancel();
    }
}
