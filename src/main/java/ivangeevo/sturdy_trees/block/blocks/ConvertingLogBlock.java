package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
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

    public ConvertingLogBlock(AbstractBlock.Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(VARIATION,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder)
    {
        super.appendProperties(builder);
        builder.add(VARIATION);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        if (!world.isClient)
        {
            int var = state.get(VARIATION);
            if (var < 2)
            {
                world.setBlockState(pos, this.getDefaultState().with(VARIATION, var + 1));
            }

            LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder((ServerWorld) world)
                    .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .add(LootContextParameters.TOOL, stack)
                    .addOptional(LootContextParameters.THIS_ENTITY, player)
                    .addOptional(LootContextParameters.BLOCK_ENTITY, blockEntity);

            if (getMiningDirection(player, world, pos) != null)
            {


                List<ItemStack> droppedStacks = state.getDroppedStacks(builder);

                for (ItemStack itemStack : droppedStacks)
                {
                    dropStack(world, pos, getMiningDirection(player, world, pos), itemStack);
                }
            }
        }
    }

    protected Direction getMiningDirection(PlayerEntity player, World world, BlockPos pos) {
        // Get the player's eye position
        Vec3d start = player.getCameraPosVec(1.0F);

        // Calculate the look vector based on the player's pitch and yaw
        Vec3d end = getVec3d(player, start);

        RaycastContext context = new RaycastContext(
                start, end, ShapeType.COLLIDER, FluidHandling.NONE, player
        );

        BlockHitResult result = world.raycast(context);

        if (result != null)
        {
            Direction hitDirection = result.getSide();
            return hitDirection.getOpposite(); // Get the opposite direction
        }
        else
        {
            return null;
        }
    }

    @NotNull
    private static Vec3d getVec3d(PlayerEntity player, Vec3d start) {
        float pitch = player.getPitch();
        float yaw = player.getYaw();

        // Adjust the look vector to point at the center of the block
        double reachDistance = 5.0D; // Adjust the reach distance as needed
        double x = start.x + Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;
        double y = start.y + Math.sin(Math.toRadians(pitch)) * reachDistance;
        double z = start.z - Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;

        return new Vec3d(x, y, z);
    }


}
