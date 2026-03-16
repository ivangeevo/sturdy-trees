package org.btwr.sturdy_trees.block.blocks;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.btwr.sturdy_trees.config.SturdyTreesConfig;

public class SmallSaplingBlock extends PlantBlock implements Fertilizable {

    public static final MapCodec<SmallSaplingBlock> CODEC = Block.createCodec(SmallSaplingBlock::new);

    private Block matureVariant;

    public static final IntProperty STAGE = Properties.STAGE;
    public static final IntProperty GROW_STAGE = IntProperty.of("grow_stage", 0, 2);

    private static final double WIDTH = 0.8D;
    private static final double HALF_WIDTH = (WIDTH / 2D);

    public static final VoxelShape SHAPE = VoxelShapes.cuboid(
            0.5D - HALF_WIDTH, 0D, 0.5D - HALF_WIDTH,
            0.5D + HALF_WIDTH, HALF_WIDTH * 2D, 0.5D + HALF_WIDTH
    );

    public SmallSaplingBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(GROW_STAGE, 0));
    }

    public SmallSaplingBlock setMatureVariant(Block block) {
        this.matureVariant = block;
        return this;
    }

    @Override
    protected MapCodec<? extends PlantBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(STAGE, GROW_STAGE);
    }

    // Same growth conditions as vanilla saplings. We make them "daily growth crops" only in BTWR: DS
    @Override
    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getLightLevel(pos.up()) >= 9 && random.nextInt(7) == 0) {
            this.incrementGrowthLevel(state, world, pos);
        }
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    private void incrementGrowthLevel(BlockState state, ServerWorld world, BlockPos pos) {
        int stage = state.get(GROW_STAGE);

        if (state.get(STAGE) == 0) {
            world.setBlockState(pos, state.cycle(STAGE), Block.NO_REDRAW);
        } else {
            // if still young -> increment grow stage
            if (stage <= 1) {
                world.setBlockState(pos, state.with(GROW_STAGE, stage + 1));
                // if at stage 2, turn to mature
            } else {
                if (matureVariant != null) {
                    world.setBlockState(pos, matureVariant.getDefaultState());
                }
            }
        }
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return SturdyTreesConfig.saplingsFertilizable.get();
    }

    @Override
    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return world.random.nextFloat() < 0.45;
    }

    @Override
    public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
        this.incrementGrowthLevel(state, world, pos);
    }
}
