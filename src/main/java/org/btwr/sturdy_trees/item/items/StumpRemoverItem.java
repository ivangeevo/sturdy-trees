package org.btwr.sturdy_trees.item.items;

import org.btwr.shared_library.api.tag.BTWRConventionalTags;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class StumpRemoverItem extends Item {

    public StumpRemoverItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        BlockPos pos = context.getBlockPos();
        BlockState stump = context.getWorld().getBlockState(pos);
        ItemStack stack = context.getStack();
        World world = context.getWorld();

        if (!world.isClient) {
            if (stump.isIn(BTWRConventionalTags.Blocks.STUMP_BLOCKS) || stump.isIn(SturdyTreesTags.Blocks.CRAFTING_STUMPS)) {
                this.spawnParticles((ServerWorld) world, pos);
                world.playSound(null, pos, SoundEvents.ENTITY_SLIME_ATTACK, SoundCategory.MASTER, 1.0F,
                        (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 0.6F);

                world.removeBlock(pos, false);
                stack.decrementUnlessCreative(1, context.getPlayer());

                return ActionResult.SUCCESS;
            }
        }

        return ActionResult.FAIL;
    }

    private void spawnParticles(ServerWorld world, BlockPos pos) {
        world.spawnParticles(ParticleTypes.EXPLOSION, pos.getX() + 0.5, pos.getY() + 0.6, pos.getZ() + 0.5,
                10, 0.2, 0.2, 0.2, 0.1
        );

        for (int i = 0; i < 20; i++) {
            double smokeX = pos.getX() + world.random.nextDouble() - 0.5D;
            double smokeY = pos.getY() + world.random.nextDouble() - 0.5D;
            double smokeZ = pos.getZ() + world.random.nextDouble() - 0.5D;

            double smokeVelX = (smokeX - pos.getX()) * 0.33D;
            double smokeVelY = (smokeY - pos.getY()) * 0.33D;
            double smokeVelZ = (smokeZ - pos.getZ()) * 0.33D;

            world.spawnParticles(ParticleTypes.SMOKE, smokeX, smokeY, smokeZ, 1, smokeVelX, smokeVelY, smokeVelZ, 2);
        }
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        super.onCraftByPlayer(stack, world, player);

        float pitch = (world.random.nextFloat() - world.random.nextFloat()) * 0.2F + 0.6F;

        if (stack.getItem() != null) {
            player.playSound(SoundEvents.ENTITY_SLIME_ATTACK, 0.1F, pitch);
        }
    }

}