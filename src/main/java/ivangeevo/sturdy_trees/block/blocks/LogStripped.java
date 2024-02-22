package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingLogBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class LogStripped extends ConvertingLogBlock implements LogBlockStacks {

    public LogStripped(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {

        double fromX = 1.0 / 16.0; double fromY = 0.0; double fromZ = 1.0 / 16.0;
        double toX = 15.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 15.0 / 16.0;

        return VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());

        // Dynamically get LogType based on the block state
        LogType logType = getLogType(state);

        // Logic to determine the block to replace with
        Block replacementBlock = determineReplacementBlock(blockBelowState, blockAboveState, logType);
        world.setBlockState(pos, replacementBlock.getDefaultState());

        dropLootTable(world, pos, state, player);
    }




    private Block determineReplacementBlock(BlockState blockBelowState, BlockState blockAboveState, LogType logType) {
        // Get the appropriate stripped variation based on the log type
        Block strippedVar1 = SturdyTreesBlocks.LOG_STRIPPED_VAR1.getDefaultState().with(LOG_TYPE, logType).getBlock();
        Block midVar1 = SturdyTreesBlocks.LOG_MID_VAR1.getDefaultState().with(LOG_TYPE, logType).getBlock();
        Block botVar1 = SturdyTreesBlocks.LOG_BOT_VAR1.getDefaultState().with(LOG_TYPE, logType).getBlock();
        Block topVar1 = SturdyTreesBlocks.LOG_TOP_VAR1.getDefaultState().with(LOG_TYPE, logType).getBlock();

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            // Both block above and below, choose midVar1
            return midVar1;
        } else if (hasBlockAbove) {
            // Only block above, choose topVar1
            return topVar1;
        } else if (hasBlockBelow) {
            // Only block below, choose botVar1
            return botVar1;
        } else {
            // Default, choose strippedVar1
            return strippedVar1;
        }
    }




    private void dropLootTable(World world, BlockPos pos, BlockState state, PlayerEntity player) {
       // Check the tool used to break the block
        ItemStack toolStack = player.getMainHandStack();
        
        List<ItemStack> droppedStacks = getDroppedPlankStacks(state, world, pos, toolStack);

        // Rest of the code to drop the items
        Direction playerFacing = player.getHorizontalFacing();

        for (ItemStack stack : droppedStacks) {
            // Calculate the drop position in the opposite direction of the player's facing
            double offsetX = -playerFacing.getOffsetX() * 0.5;
            double offsetY = 0.2;
            double offsetZ = -playerFacing.getOffsetZ() * 0.5;
            Vec3d dropPos = new Vec3d(pos.getX() + 0.5 + offsetX, pos.getY() + offsetY, pos.getZ() + 0.5 + offsetZ);

            // Drop the stack at the calculated position
            ItemEntity itemEntity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }


    private List<ItemStack> getDroppedPlankStacks(BlockState state, World world, BlockPos pos, ItemStack stack) {

        LootContext lootContext = buildBlockLootContext((ServerWorld) world, pos,state,stack);

        if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.OAK).getBlock())) {
            return getDroppedOakPlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.BIRCH).getBlock())) {
            return getDroppedBirchPlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.SPRUCE).getBlock())) {
            return getDroppedSprucePlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.JUNGLE).getBlock())) {
            return getDroppedJunglePlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.ACACIA).getBlock())) {
            return getDroppedAcaciaPlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.DARK_OAK).getBlock())) {
            return getDroppedDarkOakPlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.MANGROVE).getBlock())) {
            return getDroppedMangrovePlankStacks(state, lootContext);
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_VAR0.getDefaultState().with(LOG_TYPE, LogType.CHERRY).getBlock())) {
            return getDroppedCherryPlankStacks(state, lootContext);
        } else {
            return Collections.emptyList(); // Handle other log types if needed
        }
    }



}