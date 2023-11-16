package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingBlock;
import ivangeevo.sturdy_trees.SturdyTrees;
import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.SturdyTreesItems;
import ivangeevo.sturdy_trees.block.LogBlockStacks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogStripped extends ConvertingBlock implements LogBlockStacks {



    public LogStripped(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        // Define the dimensions from the model JSON
        double fromX = 1.0 / 16.0;
        double fromY = 0.0;
        double fromZ = 1.0 / 16.0;
        double toX = 15.0 / 16.0;
        double toY = 16.0 / 16.0;
        double toZ = 15.0 / 16.0;

        // Create a VoxelShape based on the dimensions
        VoxelShape shape = VoxelShapes.cuboid(fromX, fromY, fromZ, toX, toY, toZ);

        return shape;
    }


    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        player.addExhaustion(0.2F);

        BlockState blockBelowState = world.getBlockState(pos.down());
        BlockState blockAboveState = world.getBlockState(pos.up());

        Block oakLog = Blocks.OAK_LOG;
        Block birchLog = Blocks.BIRCH_LOG;
        Block acaciaLog = Blocks.ACACIA_LOG;
        Block spruceLog = Blocks.SPRUCE_LOG;
        Block jungleLog = Blocks.JUNGLE_LOG;


        Block oakStump = SturdyTreesBlocks.STUMP_OAK;
        Block birchStump = SturdyTreesBlocks.STUMP_BIRCH;
        Block spruceStump = SturdyTreesBlocks.STUMP_SPRUCE;
        Block jungleStump = SturdyTreesBlocks.STUMP_JUNGLE;
        Block acaciaStump = SturdyTreesBlocks.STUMP_ACACIA;

        Block oakMidVar1 = SturdyTreesBlocks.LOG_OAK_MID_VAR1;
        Block oakMidVar2 = SturdyTreesBlocks.LOG_OAK_MID_VAR2;
        Block oakMidVar3 = SturdyTreesBlocks.LOG_OAK_MID_VAR3;

        Block birchMidVar1 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR1;
        Block birchMidVar2 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR2;
        Block birchMidVar3 = SturdyTreesBlocks.LOG_BIRCH_MID_VAR3;

        Block acaciaMidVar1 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR1;
        Block acaciaMidVar2 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR2;
        Block acaciaMidVar3 = SturdyTreesBlocks.LOG_ACACIA_MID_VAR3;

        Block spruceMidVar1 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR1;
        Block spruceMidVar2 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR2;
        Block spruceMidVar3 = SturdyTreesBlocks.LOG_SPRUCE_MID_VAR3;

        Block jungleMidVar1 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR1;
        Block jungleMidVar2 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR2;
        Block jungleMidVar3 = SturdyTreesBlocks.LOG_JUNGLE_MID_VAR3;


        Block oakTopVar1 = SturdyTreesBlocks.LOG_OAK_TOP_VAR1;
        Block oakTopVar2 = SturdyTreesBlocks.LOG_OAK_TOP_VAR2;
        Block oakTopVar3 = SturdyTreesBlocks.LOG_OAK_TOP_VAR3;

        Block birchTopVar1 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR1;
        Block birchTopVar2 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR2;
        Block birchTopVar3 = SturdyTreesBlocks.LOG_BIRCH_TOP_VAR3;

        Block spruceTopVar1 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR1;
        Block spruceTopVar2 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR2;
        Block spruceTopVar3 = SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR3;

        Block jungleTopVar1 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR1;
        Block jungleTopVar2 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR2;
        Block jungleTopVar3 = SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR3;

        Block acaciaTopVar1 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR1;
        Block acaciaTopVar2 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR2;
        Block acaciaTopVar3 = SturdyTreesBlocks.LOG_ACACIA_TOP_VAR3;

        Block oakBotVar1 = SturdyTreesBlocks.LOG_OAK_BOT_VAR1;
        Block oakBotVar2 = SturdyTreesBlocks.LOG_OAK_BOT_VAR2;
        Block oakBotVar3 = SturdyTreesBlocks.LOG_OAK_BOT_VAR3;

        Block spruceBotVar1 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR1;
        Block spruceBotVar2 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR2;
        Block spruceBotVar3 = SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR3;

        Block acaciaBotVar1 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR1;
        Block acaciaBotVar3 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR2;
        Block acaciaBotVar2 = SturdyTreesBlocks.LOG_ACACIA_BOT_VAR3;

        Block birchBotVar1 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR1;
        Block birchBotVar2 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR2;
        Block birchBotVar3 = SturdyTreesBlocks.LOG_BIRCH_BOT_VAR3;

        Block jungleBotVar1 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR1;
        Block jungleBotVar2 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR2;
        Block jungleBotVar3 = SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR3;

        Block oakStrippedVar0 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR0;
        Block oakStrippedVar1 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR1;
        Block oakStrippedVar2 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR2;
        Block oakStrippedVar3 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR3;


        Block birchStrippedVar0 = SturdyTreesBlocks.LOG_STRIPPED_BIRCH_VAR0;
        Block strippedVar1 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR1;
        Block strippedVar2 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR2;
        Block strippedVar3 = SturdyTreesBlocks.LOG_STRIPPED_OAK_VAR3;

        Block[] woodBlocks = {
                oakLog, birchLog, acaciaLog, spruceLog, jungleLog,
                oakStump, birchStump, spruceStump,
                jungleStump, acaciaStump,

                oakStrippedVar0, strippedVar1, strippedVar2, strippedVar3,

                oakMidVar1, oakMidVar2, oakMidVar3,
                birchMidVar1, birchMidVar2, birchMidVar3,
                acaciaMidVar1, acaciaMidVar2, acaciaMidVar3,
                spruceMidVar1, spruceMidVar2, spruceMidVar3,
                jungleMidVar1, jungleMidVar2, jungleMidVar3,

                oakTopVar1, oakTopVar2, oakTopVar3,
                birchTopVar1, birchTopVar2, birchTopVar3,
                acaciaTopVar1, acaciaTopVar2, acaciaTopVar3,
                spruceTopVar1, spruceTopVar2, spruceTopVar3,
                jungleTopVar1, jungleTopVar2, jungleTopVar3,

                oakBotVar1, oakBotVar2, oakBotVar3,
                birchBotVar1, birchBotVar2, birchBotVar3,
                acaciaLog, acaciaBotVar2, acaciaBotVar3,
                spruceLog, spruceBotVar2, spruceBotVar3,
                jungleLog, jungleBotVar2, jungleBotVar3,
        };


        boolean isWoodBlockAbove = Arrays.asList(woodBlocks).contains(blockAboveState.getBlock());
        boolean isWoodBlockBelow = Arrays.asList(woodBlocks).contains(blockBelowState.getBlock());


        if (isWoodBlockAbove && isWoodBlockBelow) {
            world.setBlockState(pos, oakMidVar1.getDefaultState());
        } else if (isWoodBlockAbove) {
            world.setBlockState(pos, oakTopVar1.getDefaultState());
        } else if (isWoodBlockBelow) {
            world.setBlockState(pos, oakBotVar1.getDefaultState());
        } else {
            world.setBlockState(pos, oakStrippedVar1.getDefaultState());
        }
        dropLootTable(world, pos, player);
    }


    private void dropLootTable(World world, BlockPos pos, PlayerEntity player) {
        // Check the tool used to break the block
        ItemStack toolStack = player.getMainHandStack();

        List<ItemStack> droppedStacks;

            droppedStacks = getLesserDroppedShaftStacks(world.getBlockState(pos), new LootContext.Builder((ServerWorld) world)
                    .parameter(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                    .parameter(LootContextParameters.TOOL, toolStack)
                    .random(world.random));


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


    private List<ItemStack> getLesserDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = new ArrayList<>();
        droppedStacks.add(new ItemStack(SturdyTreesItems.SHAFT, 1));
        return droppedStacks;
    }


}