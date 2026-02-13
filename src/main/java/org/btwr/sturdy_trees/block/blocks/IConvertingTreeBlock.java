package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.World;

/**
 * Common interface for all tree-related blocks (logs, stumps, etc.)
 * that are able to transform into other block variants when broken
 * or interacted with by tools.
 *
 * <p>Implementing blocks typically support multiple visual or functional
 * variations such as stripped, chewed, or spiked forms. This interface
 * provides shared helper logic and structure for handling those conversions.</p>
 */
public interface IConvertingTreeBlock {

    /** The current break level of the block **/
    IntProperty getBreakLevel();

    /** Sets the amount to offset the outline by (outline shape) **/
    int getOutlineOffset();

    /** Returns true if the block successfully converted into another variant/block **/
    boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player);

    void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player);

    default void playSpecialBreakSound(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, SoundCategory.BLOCKS, 0.1F,
                1.25F + (player.getWorld().random.nextFloat() * 0.25F)
        );
    }

    /** Generates a spiked log block state while preserving existing properties.
     * <p>The facing property is only applied if the target block supports it. **/
    default BlockState getSpikeState(Block spikeVar, BlockState currentState, Direction facing) {
        if (spikeVar instanceof LogSpikeBlock) {
            BlockState base = spikeVar.getStateWithProperties(currentState);
            if (base.contains(LogSpikeBlock.FACING)) {
                return base.with(LogSpikeBlock.FACING, facing);
            }
        }
        return currentState;
    }

    /** Generates a chewed log block state while preserving compatible properties. **/
    default BlockState getChewedState(Block chewedVar, BlockState currentState) {
        if (chewedVar instanceof LogChewedBlock) {
            return chewedVar.getStateWithProperties(currentState);
        }
        return currentState;
    }

    /**
     * Rotates a voxel shape from Y-oriented coordinates to X-oriented coordinates.
     * Used to adapt shapes for blocks placed along different axes.
     *
     * @param shape the original voxel shape
     * @return a rotated voxel shape aligned to the X axis
     */
    default VoxelShape rotateYtoX(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and X
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minY, minX, minZ, maxY, maxX, maxZ));
        });
        return buffer[0];
    }

    /**
     * Rotates a voxel shape from Y-oriented coordinates to Z-oriented coordinates.
     * Used to adapt shapes for blocks placed along different axes.
     *
     * @param shape the original voxel shape
     * @return a rotated voxel shape aligned to the Z axis
     */
    default VoxelShape rotateYtoZ(VoxelShape shape) {
        VoxelShape[] buffer = new VoxelShape[]{VoxelShapes.empty()};
        shape.forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> {
            // Swap Y and Z
            buffer[0] = VoxelShapes.union(buffer[0],
                    VoxelShapes.cuboid(minX, minZ, minY, maxX, maxZ, maxY));
        });
        return buffer[0];
    }

    /**
     * Determines whether a neighboring block counts as a solid base for conversion logic.
     * <p> A valid base must be a full opaque cube and not be replaceable.
     */
    default boolean isSolidBlockAtBase(World world, BlockPos pos, BlockState base) {
        return base.isOpaqueFullCube(world, pos) && !base.isReplaceable();
    }

}
