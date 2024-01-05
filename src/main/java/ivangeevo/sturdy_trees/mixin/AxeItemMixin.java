package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.*;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.TagKey;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin  extends MiningToolItem implements SideModUtils {
    protected AxeItemMixin(float attackDamage, float attackSpeed, ToolMaterial material, TagKey<Block> effectiveBlocks, Settings settings) {
        super(attackDamage, attackSpeed, material, effectiveBlocks, settings);
    }



    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {

        if (isBTWRLoaded) {

            // (state.isOf(Blocks.OAK_LOG)) || (state.isOf(Blocks.SPRUCE_LOG)) || (state.isOf(Blocks.BIRCH_LOG)) || (state.isOf(Blocks.JUNGLE_LOG)) || (state.isOf(Blocks.ACACIA_LOG)) || (state.isOf(Blocks.DARK_OAK_LOG)) || (state.isOf(Blocks.MANGROVE_LOG))

            if (state.isIn(BlockTags.LOGS) || state.isIn(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS)) {

                if (stack.isOf(Items.STONE_AXE)) {
                    return 15.0F;
                } else if (stack.isOf(Items.IRON_AXE)) {
                    return 35.0F;
                } else if (stack.isOf(Items.DIAMOND_AXE)) {
                    return 55F;
                } else if (stack.isOf(Items.NETHERITE_AXE)) {
                    return 70F;
                } else if (stack.isIn(SturdyTreesTags.Items.MODDED_AXES)) {
                    return 75F;
                }

            }
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }

    @Override
    public boolean isSuitableFor(BlockState state) {
        return super.isSuitableFor(state);
    }

}
