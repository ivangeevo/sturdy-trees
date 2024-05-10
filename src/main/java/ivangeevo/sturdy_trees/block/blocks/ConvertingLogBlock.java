package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ConvertingLogBlock extends PillarBlock
{
    public static final IntProperty VARIATION = IntProperty.of("variation", 0, 3);
    public static final BooleanProperty CHARRED = BooleanProperty.of("charred");


    public ConvertingLogBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(VARIATION,0).with(CHARRED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(VARIATION, CHARRED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        int var = state.get(VARIATION);
        double offset = (2 + var) / 16.0;
        double to = 1.0 - offset;

        double minY = isDownBlocks(state) ? -4 : 0f;
        double maxY = isUpBlocks(state) ? 1 : 0f;

        // Create a VoxelShape based on the dimensions
        return VoxelShapes.cuboid(offset, minY, offset, to, maxY, to);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack)
    {
        int variation = state.get(VARIATION);

        if (!world.isClient)
        {
            if (variation < 2)
            {
                world.setBlockState(pos, this.getDefaultState().with(VARIATION, variation + 1));
            }

        }

        super.afterBreak(world, player, pos, state, blockEntity, stack);

    }

    private static boolean isUpBlocks(BlockState state)
    {
        return state.isOf(SturdyTreesBlocks.LOG_OAK_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP)
                || state.isOf(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP);
    }

    private static boolean isDownBlocks(BlockState state)
    {
        return state.isOf(SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN)
                || state.isOf(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN);
    }

}
