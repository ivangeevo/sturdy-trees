package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.btwr.sturdy_trees.block.enums.StumpCondition;
import org.btwr.sturdy_trees.block.enums.StumpConditionProperty;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import org.jetbrains.annotations.Nullable;

public class StumpBlock extends Block implements IConvertingTreeBlock {

    public static final IntProperty BREAK_LEVEL = IntProperty.of("break_level", 0, 3);
    public static final StumpConditionProperty CONDITION = StumpConditionProperty.of("condition");

    /** The crafting table variant of the stump block **/
    Block craftingVariant;

    public StumpBlock(Settings settings, Block craftingVariant) {
        super(settings);
        this.setDefaultState(getStateManager().getDefaultState()
                .with(BREAK_LEVEL, 0)
                .with(CONDITION, StumpCondition.NORMAL)
        );
        this.craftingVariant = craftingVariant;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BREAK_LEVEL, CONDITION);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (this.tryConvert(world, pos, state, player)) {
            this.playSoundsOnBreak(world, pos, state, player);
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int var = state.get(BREAK_LEVEL);

        // Don't apply the offset amount for the initial stripped condition
        boolean initialStrippedLog = var == 0 && state.get(CONDITION) != StumpCondition.STRIPPED;
        int baseOffset = initialStrippedLog ? this.getOutlineOffset() - 1 : this.getOutlineOffset();

        double offset =  (baseOffset + var) / 16.0;
        double to = 1.0 - offset;

        // Create a VoxelShape based on the dimensions
        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public boolean tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        boolean isValidTool = player.getMainHandStack().isIn(SturdyTreesTags.Items.STUMP_EFFICIENT);

        BlockState newState;
        if (isValidTool) {
            newState = this.getStateForEfficientBreak(state);
        }
        else {
            newState = this.getStateForInefficientBreak(world, pos, state);
        }

        world.setBlockState(pos, newState);
        return true;
    }

    @Override
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.playSpecialBreakSound(world, pos, player);
    }

    private BlockState getStateForEfficientBreak(BlockState state) {
        if (state.get(BREAK_LEVEL) <= 0) {
            return state.with(BREAK_LEVEL, 1);
        }

        return craftingVariant.getDefaultState();
    }

    private BlockState getStateForInefficientBreak(World world, BlockPos pos, BlockState state) {
        BlockState stateUp = world.getBlockState(pos.up());
        BlockState stateDown = world.getBlockState(pos.down());

        boolean hasUp = !stateUp.isAir() && isSolidBlockAtBase(world, pos.up(), stateUp);
        boolean hasDown = !stateDown.isAir() && isSolidBlockAtBase(world, pos.down(), stateDown);

        int currentLevel = state.get(BREAK_LEVEL);
        StumpCondition currentCondition = state.get(CONDITION);

        if (currentLevel >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        int nextLevel = currentLevel + 1;

        // First check if we already started converting the stump to a crafting one and set back to stripped
        if (currentCondition == StumpCondition.NORMAL && currentLevel == 1) {
            return state.with(CONDITION, StumpCondition.STRIPPED).with(BREAK_LEVEL, 0);
        }

        // If already SPIKE or CHEWED; just degrade further
        if (currentCondition == StumpCondition.SPIKE || currentCondition == StumpCondition.CHEWED) {
            return state.with(BREAK_LEVEL, nextLevel);
        }

        // If not yet stripped; convert to stripped first
        if (currentCondition != StumpCondition.STRIPPED) {
            return state.with(CONDITION, StumpCondition.STRIPPED).with(BREAK_LEVEL, currentLevel);
        }

        // Already STRIPPED – decide next form
        if (hasUp && hasDown) {
            return state.with(CONDITION, StumpCondition.CHEWED).with(BREAK_LEVEL, nextLevel);
        }

        if (hasUp || hasDown) {
            return state.with(CONDITION, StumpCondition.SPIKE).with(BREAK_LEVEL, nextLevel);
        }

        // Remain stripped and increment
        return state.with(CONDITION, StumpCondition.STRIPPED).with(BREAK_LEVEL, nextLevel);
    }

    @Override
    public int getOutlineOffset() {
        return 1;
    }

    @Override
    public IntProperty getVariation() {
        return BREAK_LEVEL;
    }

}