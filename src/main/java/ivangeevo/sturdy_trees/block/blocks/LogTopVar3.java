package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LogTopVar3 extends ConvertingBlock implements LogBlockStacks {




    public LogTopVar3(Settings settings) {
        super(settings);

    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double sideFromX = 1.0 / 16.0; double sideFromY = 13.0 / 16.0; double sideFromZ = 1.0 / 16.0;
        double sideToX = 15.0 / 16.0; double sideToY = 16.0 / 16.0; double sideToZ = 15.0 / 16.0;

        double lower1FromX = 2.0 / 16.0; double lower1FromY = 11.0 / 16.0; double lower1FromZ = 2.0 / 16.0;
        double lower1ToX = 14.0 / 16.0; double lower1ToY = 13.0 / 16.0; double lower1ToZ = 14.0 / 16.0;

        double lower2FromX = 3.0 / 16.0; double lower2FromY = 9.0 / 16.0; double lower2FromZ = 3.0 / 16.0;
        double lower2ToX = 13.0 / 16.0; double lower2ToY = 11.0 / 16.0; double lower2ToZ = 13.0 / 16.0;

        double lower3FromX = 4.0 / 16.0; double lower3FromY = 7.0 / 16.0; double lower3FromZ = 4.0 / 16.0;
        double lower3ToX = 12.0 / 16.0; double lower3ToY = 9.0 / 16.0; double lower3ToZ = 12.0 / 16.0;

        double lower4FromX = 5.0 / 16.0; double lower4FromY = 5.0 / 16.0; double lower4FromZ = 5.0 / 16.0;
        double lower4ToX = 11.0 / 16.0; double lower4ToY = 7.0 / 16.0; double lower4ToZ = 11.0 / 16.0;

        double lower5FromX = 6.0 / 16.0; double lower5FromY = 3.0 / 16.0; double lower5FromZ = 6.0 / 16.0;
        double lower5ToX = 10.0 / 16.0; double lower5ToY = 5.0 / 16.0; double lower5ToZ = 10.0 / 16.0;

        double lower6FromX = 7.0 / 16.0; double lower6FromY = 1.0 / 16.0; double lower6FromZ = 7.0 / 16.0;
        double lower6ToX = 9.0 / 16.0; double lower6ToY = 3.0 / 16.0; double lower6ToZ = 9.0 / 16.0;

        // Create VoxelShapes for the side and lower parts of the block
        VoxelShape sideShape = VoxelShapes.cuboid(sideFromX, sideFromY, sideFromZ, sideToX, sideToY, sideToZ);
        VoxelShape lower1Shape = VoxelShapes.cuboid(lower1FromX, lower1FromY, lower1FromZ, lower1ToX, lower1ToY, lower1ToZ);
        VoxelShape lower2Shape = VoxelShapes.cuboid(lower2FromX, lower2FromY, lower2FromZ, lower2ToX, lower2ToY, lower2ToZ);
        VoxelShape lower3Shape = VoxelShapes.cuboid(lower3FromX, lower3FromY, lower3FromZ, lower3ToX, lower3ToY, lower3ToZ);
        VoxelShape lower4Shape = VoxelShapes.cuboid(lower4FromX, lower4FromY, lower4FromZ, lower4ToX, lower4ToY, lower4ToZ);
        VoxelShape lower5Shape = VoxelShapes.cuboid(lower5FromX, lower5FromY, lower5FromZ, lower5ToX, lower5ToY, lower5ToZ);
        VoxelShape lower6Shape = VoxelShapes.cuboid(lower6FromX, lower6FromY, lower6FromZ, lower6ToX, lower6ToY, lower6ToZ);

        // Combine the shapes using VoxelShapes.union
        VoxelShape shape = VoxelShapes.union(sideShape, lower1Shape, lower2Shape, lower3Shape, lower4Shape, lower5Shape, lower6Shape);

        return shape;
    }



    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());

            Direction miningDirection = getMiningDirection(player, world, pos);


            if (miningDirection != null) {

                List<ItemStack> droppedStacks = getLesserDroppedSawStacks(world.getBlockState(pos), new LootContext.Builder((ServerWorld) world)
                        .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                        .parameter(LootContextParameters.TOOL, stack)
                        .random(world.random));

                for (ItemStack itemStack : droppedStacks) {
                    dropStack(world, pos, miningDirection, itemStack);
                }
            }
        }
    }
}