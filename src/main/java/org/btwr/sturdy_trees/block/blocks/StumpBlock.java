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
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.block.interfaces.IConvertingTreeBlock;
import org.btwr.sturdy_trees.block.enums.LogCondition;
import org.btwr.sturdy_trees.block.enums.LogConditionProperty;
import org.btwr.sturdy_trees.tag.SturdyTreesTags;
import org.jetbrains.annotations.Nullable;

public class StumpBlock extends Block implements IConvertingTreeBlock {

    public static final IntProperty BREAK_LEVEL = IntProperty.of("break_level", 0, 3);
    public static final LogConditionProperty CONDITION = LogConditionProperty.of("condition");

    /** The crafting table variant of the stump block **/
    @Nullable Block craftingVariant;

    public StumpBlock(Settings settings, @Nullable Block craftingVariant) {
        super(settings);
        this.setDefaultState(getStateManager().getDefaultState()
                .with(BREAK_LEVEL, 0)
                .with(CONDITION, LogCondition.NORMAL)
        );
        this.craftingVariant = craftingVariant;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(BREAK_LEVEL, CONDITION);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        if (this.convertBlock(world, pos, state, player)) {
            this.playSoundsOnBreak(world, pos, state, player);
        }

        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int breakLevel = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        int level;

        if (condition == LogCondition.NORMAL) {
            // Always reduced, no break scaling
            level = -1;
        } else if (condition == LogCondition.STRIPPED && breakLevel == 0) {
            // Full shape
            level = 0;
        } else {
            // All other non-normal cases scale normally
            level = breakLevel;
        }

        double offset = (this.getOutlineOffset() + level) / 16.0;
        double to = 1.0 - offset;

        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        boolean isValidTool = player.getMainHandStack().isIn(SturdyTreesTags.Items.STUMP_EFFICIENT);

        BlockState newState;

        if (isValidTool) {
            newState = this.getStateForEfficientBreak(world, pos, state);
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

    @Override
    public boolean btwr$hasCustomFireDestructionBehavior() {
        return true;
    }

    @Override
    public void btwr$onDestroyedByFire(World world, BlockPos pos, int fireAge, boolean forcedFireSpread) {
        world.setBlockState(pos, SturdyTreesBlocks.STUMP_SMOULDERING.getDefaultState());
    }

    private BlockState getStateForEfficientBreak(World world, BlockPos pos, BlockState state) {
        int level = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        if (condition == LogCondition.NORMAL) {
            if (level <= 0) {
                return state.with(BREAK_LEVEL, 1);
            }
            assert craftingVariant != null;
            return craftingVariant.getDefaultState();
        }

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        LogCondition newCondition = resolveConditionFromNeighbors(world, pos);

        return state.with(CONDITION, newCondition).with(BREAK_LEVEL, level + 1);
    }

    private BlockState getStateForInefficientBreak(World world, BlockPos pos, BlockState state) {
        int level = state.get(BREAK_LEVEL);
        LogCondition condition = state.get(CONDITION);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        // Special rollback case
        if (condition == LogCondition.NORMAL && level == 1) {
            return state.with(CONDITION, LogCondition.STRIPPED).with(BREAK_LEVEL, 0);
        }

        // Already spike or chewed; just increment
        if (condition == LogCondition.SPIKE || condition == LogCondition.CHEWED) {
            return incrementOrDestroy(state);
        }

        // Not yet stripped; convert first
        if (condition != LogCondition.STRIPPED) {
            return state.with(CONDITION, LogCondition.STRIPPED);
        }

        // Already stripped; apply neighbor logic
        LogCondition newCondition = resolveConditionFromNeighbors(world, pos);
        return state.with(CONDITION, newCondition).with(BREAK_LEVEL, level + 1);
    }

    private BlockState incrementOrDestroy(BlockState state) {
        int level = state.get(BREAK_LEVEL);

        if (level >= 3) {
            return Blocks.AIR.getDefaultState();
        }

        return state.with(BREAK_LEVEL, level + 1);
    }

    @Override
    public int getOutlineOffset() {
        return 1;
    }

    @Override
    public IntProperty getBreakLevel() {
        return BREAK_LEVEL;
    }

}