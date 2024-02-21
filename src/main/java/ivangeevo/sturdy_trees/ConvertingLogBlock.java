package ivangeevo.sturdy_trees;

import ivangeevo.sturdy_trees.block.util.LogType;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
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

public class ConvertingLogBlock extends PillarBlock {
    public static final EnumProperty<LogType> LOG_TYPE = EnumProperty.of("log_type", LogType.class);

    public ConvertingLogBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    public LogType getLogType(BlockState state) {
        return state.get(LOG_TYPE);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LOG_TYPE, AXIS);
    }


    // Overriding and not calling super so that the default dropStack logic doesn't transfer
    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.incrementStat(Stats.MINED.getOrCreateStat(this));
        player.addExhaustion(0.005f);
    }

    protected Direction getMiningDirection(PlayerEntity player, World world, BlockPos pos) {
        // Get the player's eye position
        Vec3d start = player.getCameraPosVec(1.0F);

        // Calculate the look vector based on the player's pitch and yaw
        Vec3d end = this.getVec3d(player, start);

        RaycastContext context = new RaycastContext(
                start, end, ShapeType.COLLIDER, FluidHandling.NONE, player);

        BlockHitResult result = world.raycast(context);

        if (result != null) {
            Direction hitDirection = result.getSide();
            return hitDirection.getOpposite(); // Get the opposite direction
        } else {
            return null;
        }
    }

    @NotNull
    private Vec3d getVec3d(PlayerEntity player, Vec3d start) {
        float pitch = player.getPitch();
        float yaw = player.getYaw();

        // Adjust the look vector to point at the center of the block
        double reachDistance = 5.0D;
        double x = start.x + Math.sin(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;
        double y = start.y + Math.sin(Math.toRadians(pitch)) * reachDistance;
        double z = start.z - Math.cos(Math.toRadians(yaw)) * Math.cos(Math.toRadians(pitch)) * reachDistance;

        return new Vec3d(x, y, z);
    }
}
