package ivangeevo.sturdy_trees.util;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class LogVoxelShapes
{
    /** Experimental Voxelshapes helper class.
     * It holds the voxelshapes for Stripped & Chewed log blocks,
     * Spike Log shapes are set in it's own class.
     */

    public static class Stripped
    {
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_0 = VoxelShapes.cuboid(1.0 / 16.0,0.0,1.0 / 16.0,15.0 / 16.0,16.0 / 16.0,15.0 / 16.0);
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_1 = VoxelShapes.cuboid(2.0 / 16.0,0.0,2.0 / 16.0,14.0 / 16.0, 16.0 / 16.0, 14.0 / 16.0);
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_2 = VoxelShapes.cuboid(3.0 / 16.0, 0.0, 3.0 / 16.0, 13.0 / 16.0, 16.0 / 16.0, 13.0 / 16.0 );
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_3 = VoxelShapes.cuboid(4.0 / 16.0,0.0, 4.0 / 16.0, 12.0 / 16.0, 16.0 / 16.0, 12.0 / 16.0);

    }

    public static class Chewed
    {
        public static VoxelShape CHEWED_SHAPE_LEVEL_0()
        {
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

            return VoxelShapes.union(topSideShape, middleShape, bottomSideShape);
        }

        public static VoxelShape CHEWED_SHAPE_LEVEL_1()
        {
            // Define the dimensions for each element
            double topSideFromX = 1.0 / 16.0;double topSideFromY = 13.0 / 16.0;double topSideFromZ = 1.0 / 16.0;
            double topSideToX = 15.0 / 16.0;double topSideToY = 16.0 / 16.0;double topSideToZ = 15.0 / 16.0;

            double middleTopFromX = 2.0 / 16.0;double middleTopFromY = 11.0 / 16.0;double middleTopFromZ = 2.0 / 16.0;
            double middleTopToX = 14.0 / 16.0;double middleTopToY = 13.0 / 16.0;double middleTopToZ = 14.0 / 16.0;

            double middleFromX = 3.0 / 16.0;double middleFromY = 5.0 / 16.0;double middleFromZ = 3.0 / 16.0;
            double middleToX = 13.0 / 16.0;double middleToY = 11.0 / 16.0;double middleToZ = 13.0 / 16.0;

            double middleBotFromX = 2.0 / 16.0;double middleBotFromY = 3.0 / 16.0;double middleBotFromZ = 2.0 / 16.0;
            double middleBotToX = 14.0 / 16.0;double middleBotToY = 5.0 / 16.0;double middleBotToZ = 14.0 / 16.0;

            double bottomSideFromX = 1.0 / 16.0;double bottomSideFromY = 0.0;double bottomSideFromZ = 1.0 / 16.0;
            double bottomSideToX = 15.0 / 16.0;double bottomSideToY = 3.0 / 16.0;double bottomSideToZ = 15.0 / 16.0;

            // Create the VoxelShapes for each element
            VoxelShape topSideShape = VoxelShapes.cuboid(topSideFromX, topSideFromY, topSideFromZ, topSideToX, topSideToY, topSideToZ);
            VoxelShape middleTopShape = VoxelShapes.cuboid(middleTopFromX, middleTopFromY, middleTopFromZ, middleTopToX, middleTopToY, middleTopToZ);
            VoxelShape middleShape = VoxelShapes.cuboid(middleFromX, middleFromY, middleFromZ, middleToX, middleToY, middleToZ);
            VoxelShape middleBotShape = VoxelShapes.cuboid(middleBotFromX, middleBotFromY, middleBotFromZ, middleBotToX, middleBotToY, middleBotToZ);
            VoxelShape bottomSideShape = VoxelShapes.cuboid(bottomSideFromX, bottomSideFromY, bottomSideFromZ, bottomSideToX, bottomSideToY, bottomSideToZ);

            // Combine the VoxelShapes into a single shape

            return VoxelShapes.union(topSideShape, middleTopShape, middleShape, middleBotShape, bottomSideShape);
        }

        public static VoxelShape CHEWED_SHAPE_LEVEL_2()
        {
            // Define the dimensions for each element
            double topSideFromX = 1.0 / 16.0;double topSideFromY = 13.0 / 16.0;double topSideFromZ = 1.0 / 16.0;
            double topSideToX = 15.0 / 16.0;double topSideToY = 16.0 / 16.0;double topSideToZ = 15.0 / 16.0;

            double middleTopFromX = 2.0 / 16.0;double middleTopFromY = 11.0 / 16.0;double middleTopFromZ = 2.0 / 16.0;
            double middleTopToX = 14.0 / 16.0;double middleTopToY = 13.0 / 16.0;double middleTopToZ = 14.0 / 16.0;

            double middleTopMidFromX = 3.0 / 16.0;double middleTopMidFromY = 9.0 / 16.0;double middleTopMidFromZ = 3.0 / 16.0;
            double middleTopMidToX = 13.0 / 16.0;double middleTopMidToY = 11.0 / 16.0;double middleTopMidToZ = 13.0 / 16.0;

            double middleFromX = 4.0 / 16.0;double middleFromY = 7.0 / 16.0;double middleFromZ = 4.0 / 16.0;
            double middleToX = 12.0 / 16.0;double middleToY = 9.0 / 16.0;double middleToZ = 12.0 / 16.0;

            double middleBotMidFromX = 3.0 / 16.0;double middleBotMidFromY = 5.0 / 16.0;double middleBotMidFromZ = 3.0 / 16.0;
            double middleBotMidToX = 13.0 / 16.0;double middleBotMidToY = 7.0 / 16.0;double middleBotMidToZ = 13.0 / 16.0;

            double middleBotFromX = 2.0 / 16.0;double middleBotFromY = 3.0 / 16.0;double middleBotFromZ = 2.0 / 16.0;
            double middleBotToX = 14.0 / 16.0;double middleBotToY = 5.0 / 16.0;double middleBotToZ = 14.0 / 16.0;

            double bottomSideFromX = 1.0 / 16.0;double bottomSideFromY = 0.0 / 16.0;double bottomSideFromZ = 1.0 / 16.0;
            double bottomSideToX = 15.0 / 16.0;double bottomSideToY = 3.0 / 16.0;double bottomSideToZ = 15.0 / 16.0;

            // Create VoxelShapes for each element
            VoxelShape topSideShape = VoxelShapes.cuboid(topSideFromX, topSideFromY, topSideFromZ, topSideToX, topSideToY, topSideToZ);
            VoxelShape middleTopShape = VoxelShapes.cuboid(middleTopFromX, middleTopFromY, middleTopFromZ, middleTopToX, middleTopToY, middleTopToZ);
            VoxelShape middleTopMidShape = VoxelShapes.cuboid(middleTopMidFromX, middleTopMidFromY, middleTopMidFromZ, middleTopMidToX, middleTopMidToY, middleTopMidToZ);
            VoxelShape middleShape = VoxelShapes.cuboid(middleFromX, middleFromY, middleFromZ, middleToX, middleToY, middleToZ);
            VoxelShape middleBotMidShape = VoxelShapes.cuboid(middleBotMidFromX, middleBotMidFromY, middleBotMidFromZ, middleBotMidToX, middleBotMidToY, middleBotMidToZ);
            VoxelShape middleBotShape = VoxelShapes.cuboid(middleBotFromX, middleBotFromY, middleBotFromZ, middleBotToX, middleBotToY, middleBotToZ);
            VoxelShape bottomSideShape = VoxelShapes.cuboid(bottomSideFromX, bottomSideFromY, bottomSideFromZ, bottomSideToX, bottomSideToY, bottomSideToZ);

            // Combine the VoxelShapes into a single shape

            return VoxelShapes.union(topSideShape, middleTopShape, middleTopMidShape, middleShape, middleBotMidShape, middleBotShape, bottomSideShape);
        }

    }






}
