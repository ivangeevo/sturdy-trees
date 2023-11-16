package ivangeevo.sturdy_trees;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.RaycastContext.FluidHandling;
import net.minecraft.world.RaycastContext.ShapeType;
import net.minecraft.world.World;

public abstract class ConvertingBlock extends Block {

    public ConvertingBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected Direction getMiningDirection(PlayerEntity player, World world, BlockPos pos) {
        // Get the player's eye position
        Vec3d start = player.getCameraPosVec(1.0F);

        // Calculate the look vector based on the player's pitch and yaw
        float pitch = player.getPitch();
        float yaw = player.getYaw();

        // Adjust the look vector to point at the center of the block
        double reachDistance = 5.0D; // Adjust the reach distance as needed
        double x = start.x + Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;
        double y = start.y + Math.sin(Math.toRadians(pitch)) * reachDistance;
        double z = start.z - Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;

        Vec3d end = new Vec3d(x, y, z);

        RaycastContext context = new RaycastContext(
                start, end, ShapeType.COLLIDER, FluidHandling.NONE, player
        );

        BlockHitResult result = world.raycast(context);

        if (result != null) {
            Direction hitDirection = result.getSide();
            return hitDirection.getOpposite(); // Get the opposite direction
        } else {
            return null;
        }
    }






}
