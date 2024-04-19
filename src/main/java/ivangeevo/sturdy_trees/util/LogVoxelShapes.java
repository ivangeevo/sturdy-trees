package ivangeevo.sturdy_trees.util;

import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class LogVoxelShapes
{

    public static class Stripped
    {
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_0 = VoxelShapes.cuboid(1.0 / 16.0,0.0,1.0 / 16.0,15.0 / 16.0,16.0 / 16.0,15.0 / 16.0);
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_1 = VoxelShapes.cuboid(2.0 / 16.0,0.0,2.0 / 16.0,14.0 / 16.0, 16.0 / 16.0, 14.0 / 16.0);
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_2 = VoxelShapes.cuboid(3.0 / 16.0, 0.0, 3.0 / 16.0, 13.0 / 16.0, 16.0 / 16.0, 13.0 / 16.0 );
        public static final VoxelShape STRIPPED_SHAPE_LEVEL_3 = VoxelShapes.cuboid(4.0 / 16.0,0.0, 4.0 / 16.0, 12.0 / 16.0, 16.0 / 16.0, 12.0 / 16.0);

    }

    public static class Top
    {

    }

    public static class Bottom
    {

    }






}
