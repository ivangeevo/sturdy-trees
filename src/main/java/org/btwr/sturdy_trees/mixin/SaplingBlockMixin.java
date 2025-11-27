package org.btwr.sturdy_trees.mixin;

import org.btwr.sturdy_trees.SturdyTreesMod;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.btwr.sturdy_trees.config.SturdyTreesConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin extends PlantBlock implements Fertilizable {

    @Shadow public abstract void generate(ServerWorld world, BlockPos pos, BlockState state, Random random);

    @Shadow
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45;
    }

    @Shadow
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.generate(world, pos, state, random);
    }

    public SaplingBlockMixin(Settings settings) {
        super(settings);
    }

    // Make the saplings not able to be grown fast with Bone Meal
    @Inject(method = "isFertilizable", at = @At("HEAD"), cancellable = true)
    private void setIsFertilizable(WorldView world, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (!SturdyTreesConfig.Settings.saplingsFertilizable.get()) {
            cir.setReturnValue(false);
        }
    }

}