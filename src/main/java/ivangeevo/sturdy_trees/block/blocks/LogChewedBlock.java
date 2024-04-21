package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogChewedBlock extends ConvertingLogBlock
{


    public LogChewedBlock(Settings settings) {
        super(settings);

    }

    /**
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions for each element
        double topSideFromX = 1.0 / 16.0; double topSideFromY = 13.0 / 16.0; double topSideFromZ = 1.0 / 16.0;
        double topSideToX = 15.0 / 16.0; double topSideToY = 16.0 / 16.0;double topSideToZ = 15.0 / 16.0;

        double middleFromX = 2.0 / 16.0; double middleFromY = 3.0 / 16.0;double middleFromZ = 2.0 / 16.0;
        double middleToX = 14.0 / 16.0; double middleToY = 13.0 / 16.0;double middleToZ = 14.0 / 16.0;

        double bottomSideFromX = 1.0 / 16.0; double bottomSideFromY = 0.0;double bottomSideFromZ = 1.0 / 16.0;
        double bottomSideToX = 15.0 / 16.0; double bottomSideToY = 3.0 / 16.0;double bottomSideToZ = 15.0 / 16.0;

        // Create the VoxelShapes for each element
        VoxelShape topSideShape = VoxelShapes.cuboid(topSideFromX, topSideFromY, topSideFromZ, topSideToX, topSideToY, topSideToZ);
        VoxelShape middleShape = VoxelShapes.cuboid(middleFromX, middleFromY, middleFromZ, middleToX, middleToY, middleToZ);
        VoxelShape bottomSideShape = VoxelShapes.cuboid(bottomSideFromX, bottomSideFromY, bottomSideFromZ, bottomSideToX, bottomSideToY, bottomSideToZ);

        // Combine the VoxelShapes into a single shape
        return VoxelShapes.union(topSideShape, middleShape, bottomSideShape);
    }
     **/

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context)
    {
        VoxelShape shape = VoxelShapes.empty();
        int varInt = getShapeForState(state);
        int shapesAmount = getShapesAmountForState(state);

        // Define dimensions for each element
        for (int i = varInt; i <= shapesAmount; i++)
        {
            // Calculate 'from' coordinates dynamically
            double fromX = (3 - i) / 16.0;
            double fromY = 0.0;
            double fromZ = (3 - i) / 16.0;

            // Calculate 'to' coordinates dynamically
            double toX = (13 + i) / 16.0;
            double toY = (16 - i) / 16.0;
            double toZ = (13 + i) / 16.0;

            // Create VoxelShape for each element and add to the main shape
            shape = VoxelShapes.union(shape, VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ));
        }

        return shape;
    }

    private int getShapeForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 1; }
        else if (state.get(VARIATION) == 2) { return 2; }

        return 0;
    }

    private int getShapesAmountForState(BlockState state)
    {
        if (state.get(VARIATION) == 1) { return 3; }
        else if (state.get(VARIATION) == 2) { return 7; }

        return 2;
    }



}