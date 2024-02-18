package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.util.SideModUtils;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LogStripped extends ConvertingBlock implements LogBlockStacks, SideModUtils {
    public static final EnumProperty<LogType> LOG_TYPE = EnumProperty.of("log_type", LogType.class);


    public LogStripped(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(LOG_TYPE, LogType.OAK));

    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 1.0 / 16.0; double fromY = 0.0; double fromZ = 1.0 / 16.0;
        double toX = 15.0 / 16.0; double toY = 16.0 / 16.0; double toZ = 15.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {

        super.afterBreak(world, player, pos, state, blockEntity, stack);

        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());



        // Logic to determine the block to replace with
        Block replacementBlock = determineReplacementBlock(state, blockBelowState, blockAboveState);
        world.setBlockState(pos, replacementBlock.getDefaultState());

        dropLootTable(world, pos, state, player);
    }





    private Block determineReplacementBlock(BlockState state, BlockState blockBelowState, BlockState blockAboveState) {
        Block strippedVar0 = null;
        Block strippedVar1 = null;
        Block strippedVar2 = null;
        Block strippedVar3 = null;

        Block midVar1 = null;
        Block midVar2 = null;
        Block midVar3 = null;

        Block botVar1 = null;
        Block botVar2 = null;
        Block botVar3 = null;

        Block topVar1 = null;
        Block topVar2 = null;
        Block topVar3 = null;



        // Assign the appropriate stripped variations based on the log type
        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_OAK_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_OAK_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_OAK_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_OAK_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_OAK_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_OAK_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_OAK_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_OAK_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_OAK_TOP_VAR3;

        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR3;


        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR3;
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0)) {
            strippedVar0 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0;
            strippedVar1 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR1;
            strippedVar2 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR2;
            strippedVar3 = SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR3;

            midVar1 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR1;
            midVar2 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR2;
            midVar3 = SturdyTreesBlocks.LOG_MANGROVE_MID_VAR3;

            botVar1 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR1;
            botVar2 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR2;
            botVar3 = SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR3;

            topVar1 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR1;
            topVar2 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR2;
            topVar3 = SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR3;
        }

        // Check for blocks above and below
        boolean hasBlockAbove = !blockAboveState.isAir();
        boolean hasBlockBelow = !blockBelowState.isAir();




        // Default and neighboring replacement logic
        if (hasBlockAbove && hasBlockBelow) {
            // Both block above and below, choose midVar1
            return midVar1;
        } else if (hasBlockAbove) {
            // Only block above, choose topVar2
            return topVar1;
        } else if (hasBlockBelow) {
            // Only block below, choose botVar2
            return botVar1;
        } else {
            // Default, choose strippedVar0
            return strippedVar1;
        }

    }




    private void dropLootTable(World world, BlockPos pos, BlockState state, PlayerEntity player) {
       // Check the tool used to break the block
        ItemStack toolStack = player.getMainHandStack();



        List<ItemStack> droppedStacks = getDroppedPlankStacks(state, world, pos, toolStack, world.random);

        // Rest of the code to drop the items
        Direction playerFacing = player.getHorizontalFacing();

        for (ItemStack stack : droppedStacks) {
            // Calculate the drop position in the opposite direction of the player's facing
            double offsetX = -playerFacing.getOffsetX() * 0.5;
            double offsetY = 0.2;
            double offsetZ = -playerFacing.getOffsetZ() * 0.5;
            Vec3d dropPos = new Vec3d(pos.getX() + 0.5 + offsetX, pos.getY() + offsetY, pos.getZ() + 0.5 + offsetZ);

            // Drop the stack at the calculated position
            ItemEntity itemEntity = new ItemEntity(world, dropPos.x, dropPos.y, dropPos.z, stack);
            itemEntity.setToDefaultPickupDelay();
            world.spawnEntity(itemEntity);
        }
    }


    private List<ItemStack> getDroppedPlankStacks(BlockState state, World world, BlockPos pos, ItemStack toolStack, Random random) {
        LootContext.Builder builder = new LootContext.Builder((ServerWorld) world)
                .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                .parameter(LootContextParameters.TOOL, toolStack)
                .random(world.random);

        if (state.isOf(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0)) {
            return getDroppedOakPlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0)) {
            return getDroppedBirchPlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0)) {
            return getDroppedSprucePlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0)) {
            return getDroppedJunglePlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0)) {
            return getDroppedAcaciaPlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0)) {
            return getDroppedDarkOakPlankStacks(state, builder);
        } else if (state.isOf(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0)) {
            return getDroppedMangrovePlankStacks(state, builder);
        } else {
            return Collections.emptyList(); // Handle other log types if needed
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(LOG_TYPE);
    }

}