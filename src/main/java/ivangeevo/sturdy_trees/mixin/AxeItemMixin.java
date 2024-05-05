package ivangeevo.sturdy_trees.mixin;


import com.google.common.collect.ImmutableMap;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.Optional;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin extends MiningToolItem {




    public AxeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }


    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state)
    {
        if (state.isIn(BlockTags.LEAVES))
        {
            return super.getMiningSpeedMultiplier(stack, state) * 2.5f;
        }

        return super.getMiningSpeedMultiplier(stack, state);
    }

    // Method to calculate random chance of conversion
    private boolean canConvertWithChance(World world) {
        return world.getRandom().nextFloat() >= 0.2f;
    }



}
