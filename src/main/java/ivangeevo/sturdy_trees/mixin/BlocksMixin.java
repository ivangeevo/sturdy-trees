package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.block.blocks.LogBlock;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Blocks.class)
public abstract class BlocksMixin implements SideModUtils {



    /** Modifying constants on single blocks **/

    @ModifyConstant(method = "<clinit>",
            constant = @Constant(floatValue = 0.7f), slice = @Slice(
            from = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/block/Blocks;MANGROVE_LOG:Lnet/minecraft/block/Block;"),
            to = @At(value = "FIELD", opcode = Opcodes.PUTSTATIC, target = "Lnet/minecraft/block/Blocks;MANGROVE_ROOTS:Lnet/minecraft/block/Block;"))
    )
    private static float modifyMangroveRootsStrength(float original) {
        float str;

        if (isBTWRLoaded) {
            str = 1.5f;
        } else {
            str = 0.7f;
        }

        return str;
    }

    /** Method modifications for registering of different blocks **/



    @Inject(method = "createLeavesBlock", at = @At("HEAD"), cancellable = true)
    private static void injectedCreateLeavesBlock(BlockSoundGroup soundGroup, CallbackInfoReturnable<LeavesBlock> cir) {

        AbstractBlock.Settings settings = AbstractBlock.Settings.of(Material.LEAVES).strength(2.0F).ticksRandomly().sounds(soundGroup).nonOpaque().allowsSpawning(BlocksMixin::canSpawnOnLeaves).suffocates(BlocksMixin::never).blockVision(BlocksMixin::always);
        LeavesBlock block = new LeavesBlock(settings);
        cir.setReturnValue(block);
    }



    @Inject(method = "createLogBlock", at = @At("HEAD"), cancellable = true)
    private static void injectedCreateLogBlock(MapColor topMapColor, MapColor sideMapColor, CallbackInfoReturnable<PillarBlock> cir) {

        float str;

        if (isBTWRLoaded) {
            str = 24f;
        } else {
            str = 2f;
        }

        AbstractBlock.Settings settings = AbstractBlock.Settings.of(Material.WOOD, state ->
                state.get(PillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor).strength(str).sounds(BlockSoundGroup.WOOD);
        PillarBlock block = new PillarBlock(settings);
        cir.setReturnValue(block);

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
