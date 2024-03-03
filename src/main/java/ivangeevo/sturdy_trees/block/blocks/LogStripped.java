package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingLogBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.state.property.ModProperties;
import ivangeevo.sturdy_trees.util.ModVoxelShapes;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.State;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogStripped extends ConvertingLogBlock implements LogBlockStacks {
    public static final IntProperty BREAK_LEVEL = ModProperties.BREAK_LEVEL;


    public LogStripped(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BREAK_LEVEL, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BREAK_LEVEL);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int breakLevel = state.get(BREAK_LEVEL);

        if (state.get(AXIS) == Direction.Axis.Y)
        {
            switch (breakLevel) {
                case 0 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_0;}
                case 1 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_1;}
                case 2 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_2;}
                case 3 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_3;}

            }
        }
        else if (state.get(AXIS) == Direction.Axis.X )
        {
            switch (breakLevel) {
                case 0 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_0_HORIZONTAL_Z;}
                case 1 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_1_HORIZONTAL_Z;}
                case 2 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_2_HORIZONTAL_Z;}
                case 3 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_3_HORIZONTAL_Z;}

            }
        }
        else if (state.get(AXIS) == Direction.Axis.Z){
            switch (breakLevel) {
                case 0 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_0_HORIZONTAL_Z;}
                case 1 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_1_HORIZONTAL_Z;}
                case 2 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_2_HORIZONTAL_Z;}
                case 3 -> {return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_3_HORIZONTAL_Z;}

            }
        }
        return ModVoxelShapes.STRIPPED_SHAPE_LEVEL_0;
    }




    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());

        // Logic to determine BlockState to replace with
        BlockState replacementState = determineReplacementState(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, replacementState);

       // dropLootTable(world, pos, state, player);
    }




    private BlockState determineReplacementState(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        // Get the appropriate stripped variation based on the log type
        BlockState strippedVar1 = null;
        BlockState chewedVar1 = null;
        BlockState spikeVar1 = null;

        // Assign the appropriate stripped variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_OAK)) {

            strippedVar1 = SturdyTreesBlocks.LOG_STRIPPED_OAK.getDefaultState().with(BREAK_LEVEL_STRIPPED,1);
            chewedVar1 = SturdyTreesBlocks.LOG_CHEWED_OAK.getDefaultState().with(ModProperties.BREAK_LEVEL_SPIKEANDCHEW,0);
            spikeVar1 = SturdyTreesBlocks.LOG_SPIKE_OAK.getDefaultState().with(ModProperties.BREAK_LEVEL_SPIKEANDCHEW,0);

        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_BIRCH)) {

            strippedVar1 = SturdyTreesBlocks.LOG_STRIPPED_BIRCH.getDefaultState().with(BREAK_LEVEL_STRIPPED,1);
            chewedVar1 = SturdyTreesBlocks.LOG_CHEWED_BIRCH.getDefaultState().with(ModProperties.BREAK_LEVEL_SPIKEANDCHEW,0);
            spikeVar1 = SturdyTreesBlocks.LOG_SPIKE_BIRCH.getDefaultState().with(ModProperties.BREAK_LEVEL_SPIKEANDCHEW,0);

        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_SPRUCE)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_JUNGLE)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_ACACIA)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_DARK_OAK)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_MANGROVE)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_CHERRY)) {
        } else if (state.isOf(SturdyTreesBlocks.LOG_STRIPPED_WARPED)) {
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();

        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            // Both block above and below, choose midVar1
            return chewedVar1;
        } else if (hasBlockAbove) {
            // Only block above, choose topVar1
            return spikeVar1;
        } else if (hasBlockBelow) {
            // Only block below, choose botVar1
            return spikeVar1;
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

        LootContext lootContext = buildBlockLootContext((ServerWorld) world, pos, state, stack);

        return null;
    }



}