
package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.entity.player.PlayerEntity;
import org.btwr.sturdy_trees.block.SturdyTreesBlocks;
import org.btwr.sturdy_trees.block.interfaces.IConvertingTreeBlock;
import net.minecraft.state.property.IntProperty;

public class SmoulderingStumpBlock extends SmoulderingLogBlock implements IConvertingTreeBlock {

    public static final MapCodec<SmoulderingStumpBlock> CODEC = createCodec(SmoulderingStumpBlock::new);

    public SmoulderingStumpBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends SmoulderingLogBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected boolean shouldFall(BlockState state) {
        return false; // Stumps don't fall
    }

    @Override
    protected int getParticleCount() {
        return 3; // Stumps produce fewer particles
    }

    @Override
    protected int getSoundChance() {
        return 32; // Stumps make sounds less frequently
    }

    @Override
    protected Block getCindersBlock() {
        return SturdyTreesBlocks.WOOD_CINDERS_STUMP;
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        int burnLevel = state.get(BURN_LEVEL);
        double offset = (this.getOutlineOffset() + burnLevel) / 16.0;
        double to = 1.0 - offset;
        return VoxelShapes.cuboid(offset, 0.0, offset, to, 1.0, to);
    }

    @Override
    protected VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.fullCube();
    }

    // IConvertingTreeBlock implementation
    @Override
    public IntProperty getBreakLevel() {
        return BURN_LEVEL;
    }

    @Override
    public int getOutlineOffset() {
        return 1;
    }

    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // Smouldering stumps explode when interacted with
        if (!world.isClient) {
            world.createExplosion(null, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                    1.0F, World.ExplosionSourceType.BLOCK
            );
        }
        return true;
    }

    @Override
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        // Sound handled by explosion
    }
}