package ivangeevo.sturdy_trees.mixin;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Collections;
import java.util.List;

@Mixin(PillarBlock.class)
public abstract class PillarBlockMixin extends Block implements LogBlockStacks {
    public PillarBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.addExhaustion(0.2F);

        if (state.isOf(Blocks.OAK_LOG)) {
            handleLogBreak(world, pos, state, player, tool, SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.BIRCH_LOG)) {
            handleLogBreak(world, pos, state, player, tool,  SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.SPRUCE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.JUNGLE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.ACACIA_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.DARK_OAK_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.MANGROVE_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0);
        } else if (state.isOf(Blocks.CHERRY_LOG)) {
            handleLogBreak(world, pos, state, player, tool,SturdyTreesBlocks.LOG_CHERRY_STRIPPED_VAR0);
        }
        // Add similar blocks for other log types as needed
    }

    private void handleLogBreak(World world, BlockPos pos, BlockState state, PlayerEntity player, ItemStack tool, Block... logVariants) {
        boolean isAxe = (tool.isOf(Items.STONE_AXE) || tool.isOf(Items.IRON_AXE) || tool.isOf(Items.DIAMOND_AXE) ||
                tool.isOf(Items.NETHERITE_AXE) || tool.isIn(SturdyTreesTags.Items.MODDED_AXES));

        if (isAxe) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
            Block strippedLog = logVariants[0];
            world.setBlockState(pos, strippedLog.getDefaultState());

            for (int i = 0; i < logVariants.length - 1; i++) {
                if (state.isOf(logVariants[i])) {
                    world.setBlockState(pos, logVariants[i + 1].getDefaultState());
                    break;
                }
            }
        }

        // Logic for dropping items (stacks)
        List<ItemStack> droppedStacks = getDroppedStacks(state, (ServerWorld) world, pos, tool, player);
        for (ItemStack stack : droppedStacks) {
            dropItemStack(world, pos, stack, player);
        }
    }



    private void dropItemStack(World world, BlockPos pos, ItemStack stack, PlayerEntity player) {
        Direction playerFacing = player.getHorizontalFacing();
        double offsetX = -playerFacing.getOffsetX() * 0.5;
        double offsetY = 0.2;
        double offsetZ = -playerFacing.getOffsetZ() * 0.5;
        Vec3d dropPos = new Vec3d(pos.getX() + 0.5 + offsetX, pos.getY() + offsetY, pos.getZ() + 0.5 + offsetZ);
        ItemEntity itemEntity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
        itemEntity.setToDefaultPickupDelay();
        world.spawnEntity(itemEntity);
    }

    public List<ItemStack> getDroppedStacks(BlockState state, ServerWorld serverWorld, BlockPos pos, ItemStack tool, PlayerEntity player) {

        // Use the LootContextParameterSet appropriate for your context
        LootContext lootContext = buildBlockLootContext(serverWorld, pos, state, tool);

        boolean isAxe = (tool.isOf(Items.STONE_AXE) || tool.isOf(Items.IRON_AXE) || (tool.isOf(Items.DIAMOND_AXE) || (tool.isIn(SturdyTreesTags.Items.MODDED_AXES))));

        boolean isLogOak = (state.isOf(Blocks.OAK_LOG));
        boolean isLogBirch = (state.isOf(Blocks.BIRCH_LOG));
        boolean isLogSpruce = (state.isOf(Blocks.SPRUCE_LOG));
        boolean isLogJungle = (state.isOf(Blocks.JUNGLE_LOG));
        boolean isLogAcacia = (state.isOf(Blocks.ACACIA_LOG));
        boolean isLogDarkOak = (state.isOf(Blocks.DARK_OAK_LOG));
        boolean isLogMangrove = (state.isOf(Blocks.MANGROVE_LOG));
        boolean isLogCherry = (state.isOf(Blocks.CHERRY_LOG));




        if (isAxe) {
            if (isLogOak) {
                return getFullDroppedStacksOak(state, lootContext);
            } else if (isLogBirch) {
                return getFullDroppedStacksBirch(state, lootContext);
            } else if (isLogSpruce) {
                return getFullDroppedStacksSpruce(state, lootContext);
            } else if (isLogJungle) {
                return getFullDroppedStacksJungle(state, lootContext);
            } else if (isLogAcacia) {
                return getFullDroppedStacksAcacia(state, lootContext);
            } else if (isLogDarkOak) {
                return getFullDroppedStacksDarkOak(state, lootContext);
            } else if (isLogMangrove) {
                return getFullDroppedStacksMangrove(state, lootContext);
            } else if (isLogCherry) {
                return getFullDroppedStacksCherry(state, lootContext);
            }
        } else  {
            if (isLogOak) {
                return getLesserDroppedBarkStacksOak(state, lootContext);
            } else if (isLogBirch) {
                return getLesserDroppedBarkStacksBirch(state, lootContext);
            } else if (isLogSpruce) {
                return getLesserDroppedBarkStacksSpruce(state, lootContext);
            } else if (isLogJungle) {
                return getLesserDroppedBarkStacksJungle(state, lootContext);
            } else if (isLogAcacia) {
                return getLesserDroppedBarkStacksAcacia(state, lootContext);
            } else if (isLogDarkOak) {
                return getLesserDroppedBarkStacksDarkOak(state, lootContext);
            } else if (isLogMangrove) {
                return getLesserDroppedBarkStacksMangrove(state, lootContext);
            } else if (isLogCherry) {
                return getLesserDroppedBarkStacksCherry(state, lootContext);
            }
        }

        return Collections.emptyList(); // Return an empty list if none of the conditions match
    }

}
