package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.tag.SturdyTreesItemTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LogBlock extends PillarBlock implements LogBlockStacks {

    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public static final EnumProperty<LogType> TYPE = EnumProperty.of("type", LogType.class);


    public LogBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return LogBlock.changeRotation(state, rotation);
    }



    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState();
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.addExhaustion(0.2F);


        boolean isAxe = (tool.isOf(Items.STONE_AXE) || tool.isOf(Items.IRON_AXE) || tool.isOf(Items.DIAMOND_AXE) || tool.isIn(SturdyTreesItemTags.MODDED_AXES));

        if (isAxe)  {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        } else {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR0.getDefaultState());

             if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR0)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR1.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR1)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR2.getDefaultState());
            } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR2)) {
                world.setBlockState(pos, SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR3.getDefaultState());
            }
        }

        // Logic for dropping items (stacks)
        List<ItemStack> droppedStacks = getDroppedStacks(state, world, pos, tool, player);

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

    public List<ItemStack> getDroppedStacks(BlockState state, World world, BlockPos pos, ItemStack tool, PlayerEntity player) {
        LootContext.Builder builder = new LootContext.Builder((ServerWorld) world)
                .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                .parameter(LootContextParameters.TOOL, tool);

        boolean isAxe = (tool.isOf(Items.STONE_AXE) || tool.isOf(Items.IRON_AXE) || (tool.isOf(Items.DIAMOND_AXE) || (tool.isIn(SturdyTreesItemTags.MODDED_AXES))));

        boolean isLogOak = (state.isOf(Blocks.OAK_LOG));
        boolean isLogBirch = (state.isOf(Blocks.BIRCH_LOG));
        boolean isLogSpruce = (state.isOf(Blocks.SPRUCE_LOG));
        boolean isLogJungle = (state.isOf(Blocks.JUNGLE_LOG));
        boolean isLogAcacia = (state.isOf(Blocks.ACACIA_LOG));
        boolean isLogDarkOak = (state.isOf(Blocks.DARK_OAK_LOG));
        boolean isLogMangrove = (state.isOf(Blocks.MANGROVE_LOG));


        if (isAxe) {
            if (isLogOak) {
                return getFullDroppedStacksOak(state, builder);
            } else if (isLogBirch) {
                return getFullDroppedStacksBirch(state, builder);
            } else if (isLogSpruce) {
                return getFullDroppedStacksSpruce(state, builder);
            } else if (isLogJungle) {
                return getFullDroppedStacksJungle(state, builder);
            } else if (isLogAcacia) {
                return getFullDroppedStacksAcacia(state, builder);
            } else if (isLogDarkOak) {
                return getFullDroppedStacksDarkOak(state, builder);
            } else if (isLogMangrove) {
                return getFullDroppedStacksMangrove(state, builder);
            }
        } else  {
            if (isLogOak) {
                return getLesserDroppedStacksOak(state, builder);
            } else if (isLogBirch) {
                return getLesserDroppedStacksBirch(state, builder);
            } else if (isLogSpruce) {
                return getLesserDroppedStacksSpruce(state, builder);
            } else if (isLogJungle) {
                return getLesserDroppedStacksJungle(state, builder);
            } else if (isLogAcacia) {
                return getLesserDroppedStacksAcacia(state, builder);
            } else if (isLogDarkOak) {
                return getLesserDroppedStacksDarkOak(state, builder);
            } else if (isLogMangrove) {
                return getLesserDroppedStacksMangrove(state, builder);
            }
        }

        return Collections.emptyList(); // Return an empty list if none of the conditions match
    }



}


