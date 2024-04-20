package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
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

public abstract class ConvertingLogBlock extends Block
{

    public static final IntProperty VARIATION = IntProperty.of("variation", 0, 3);

    public ConvertingLogBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        super.afterBreak(world, player, pos, state, blockEntity, tool);
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
