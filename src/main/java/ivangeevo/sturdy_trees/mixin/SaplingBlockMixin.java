package ivangeevo.sturdy_trees.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.block.PlantBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SaplingBlock.class)
public abstract class SaplingBlockMixin extends PlantBlock implements Fertilizable {
    public SaplingBlockMixin(Settings settings) {
        super(settings);
    }

    // Make the saplings not able to be grown fast with Bone Meal
    @Override
    public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
        return false;
    }

    @Shadow public abstract void generate(ServerWorld world, BlockPos pos, BlockState state, Random random);


    @Shadow
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return (double)world.random.nextFloat() < 0.45;
    }
    @Shadow
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.generate(world, pos, state, random);
    }
}
