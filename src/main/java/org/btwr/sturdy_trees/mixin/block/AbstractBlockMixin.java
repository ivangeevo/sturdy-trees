package org.btwr.sturdy_trees.mixin.block;

import net.minecraft.block.*;
import net.minecraft.entity.Entity;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin  {

    @Inject(method = "getCollisionShape", at = @At("HEAD"), cancellable = true)
    private void getCollisionShapeForLeaves(BlockState state, BlockView world, BlockPos pos, ShapeContext context, CallbackInfoReturnable<VoxelShape> cir) {
        AbstractBlock self = (AbstractBlock)(Object)this;

        if (!(self instanceof LeavesBlock)) return;

        if (context instanceof EntityShapeContext entityCtx) {
            Entity entity = entityCtx.getEntity();

            if (entity != null && entity.getType().isIn(SturdyTreesTags.EntityTypes.COLLIDES_WITH_LEAVES)) {
                // Let it return the normal full shape — don't cancel
                return;
            }

            // Has an entity context but entity is NOT in the tag — make passable
            cir.setReturnValue(VoxelShapes.empty());
        }

        // No entity context at all (ShapeContext.absent(), block-only checks, etc.)
        // Do NOT cancel — let vanilla return its cached shape normally
    }

    @Inject(method = "onEntityCollision", at = @At("HEAD"))
    private void onEntityCollisionForLeaves(BlockState state, World world, BlockPos pos, Entity entity, CallbackInfo ci) {
        AbstractBlock self = (AbstractBlock)(Object)this;

        // Apply only for leaves
        if (!(self instanceof LeavesBlock)) return;

        // Do not apply to projectiles
        if (entity instanceof ProjectileEntity) return;

        // Falling blocks destroy the leaves block on contact
        if (entity instanceof FallingBlockEntity) {
            if (entity.getY() <= pos.getY() + 0.5f) {
                world.removeBlock(pos, false);
            }
        }

        Block blockBelow = world.getBlockState(pos.down()).getBlock();

        boolean shouldSlow = entity.getBoundingBox().maxY >= pos.getY() + 0.05
                        || blockBelow instanceof LeavesBlock;

        if (shouldSlow) {
            float modifier = 0.5f;

            // BTW hack: reduce slowdown when airborne so jumping in leaves feels OK
            if (entity instanceof LivingEntity && !entity.isOnGround()) {
                modifier = 1.0f - modifier / 8.0f;
            }

            Vec3d vel = entity.getVelocity();

            double newX = vel.x * modifier;
            double newZ = vel.z * modifier;
            double newY = vel.y < 0 ? vel.y * 0.5 : vel.y;

            entity.setVelocity(newX, newY, newZ);

            // Resets fall distance entirely when inside leaves
            entity.fallDistance = 0.0f;
        }
    }

}