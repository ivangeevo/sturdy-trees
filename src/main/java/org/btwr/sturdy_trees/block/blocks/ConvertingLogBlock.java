package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ConvertingLogBlock extends PillarBlock {

    // Charring functionality not added fully yet, so we exclude mentions in code for now
    public static final BooleanProperty CHARRED = BooleanProperty.of("charred");

    public ConvertingLogBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                //.with(CHARRED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(/**, CHARRED**/);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        this.tryConvert(world, pos, state, player);
        this.playSoundsOnBreak(world, pos, state, player);
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {}

    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {}

    /**
     * Sets the amount to offset the outline by (outline shape)
     **/
    protected abstract int getOutlineOffset();

    protected void playSpecialBreakSound(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                1.25F + (player.getWorld().random.nextFloat() * 0.25F));
    }

    protected static VoxelShape rotateYtoX(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and X
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minY, minX, minZ, maxY, maxX, maxZ));
        });
        return buffer[0];
    }

    protected static VoxelShape rotateYtoZ(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and Z
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minX, minZ, minY, maxX, maxZ, maxY));
        });
        return buffer[0];
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

}