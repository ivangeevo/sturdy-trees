package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.block.blocks.LogBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlocksMixin {



    /** Modifying constants on single blocks **/

    @ModifyConstant(method = "<clinit>",
            constant = @Constant(floatValue = 0.7f), slice = @Slice(
            from = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/block/Blocks;MANGROVE_LOG:Lnet/minecraft/block/Block;"),
            to = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/block/Blocks;MANGROVE_ROOTS:Lnet/minecraft/block/Block;"))
    )
    private static float modifyMangroveRootsStrength(float original) {
        return 1.5f;
    }

    /** Method modifications for registering of different blocks **/




    @Inject(method = "createLeavesBlock", at = @At("HEAD"), cancellable = true)
    private static void injectedCreateLeavesBlock(BlockSoundGroup soundGroup, CallbackInfoReturnable<LeavesBlock> cir) {
        cir.setReturnValue(new LeavesBlock(AbstractBlock.Settings.of(Material.LEAVES).strength(2.0f).ticksRandomly().sounds(soundGroup).nonOpaque().allowsSpawning(BlocksMixin::canSpawnOnLeaves).suffocates(BlocksMixin::never).blockVision(BlocksMixin::never)));
    }




    @Shadow
    private static Boolean canSpawnOnLeaves(BlockState state, BlockView world, BlockPos pos, EntityType<?> type) {
        return type == EntityType.OCELOT || type == EntityType.PARROT;
    }
    private static boolean always(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return true;
    }
    private static boolean never(BlockState blockState, BlockView blockView, BlockPos blockPos) {
        return false;
    }

}
