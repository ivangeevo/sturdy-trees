package org.btwr.sturdy_trees.block.blocks;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;


public class LogStrippedBlock extends ConvertingLogBlock {

    public static final IntProperty VARIATION = IntProperty.of("variation", 0, 3);

    public LogStrippedBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(VARIATION);
    }


    // Removed the neighboring replacement logic as it was causing issues and I don't really understand what I coded here *facepalm*
    // Ideally all the log classes would need to be reworked like I've tried to do so in the dev/logs-class-overhaul branch
    /**
    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);

        if (!world.isClient && state.get(VARIATION) == 0) {
            Block thisBlock = state.getBlock();
            Identifier id = Registries.BLOCK.getId(thisBlock);
            String[] parts = id.getPath().split("_");
            boolean isLogBlock = parts.length >= 3 && parts[0].equals("log");

            if (isLogBlock) {
                String woodType = parts[1]; // e.g., spruce

                Identifier chewedId = Identifier.of(id.getNamespace(), "log_" + woodType + "_chewed");
                Block chewedBlock = Registries.BLOCK.get(chewedId);

                Identifier strippedId = Identifier.of(id.getNamespace(), "log_" + woodType + "_stripped");
                Block strippedBlock = Registries.BLOCK.get(strippedId);

                Identifier spikeId = Identifier.of(id.getNamespace(), "log_" + woodType + "_spike");
                Block spikeBlock = Registries.BLOCK.get(spikeId);

                if (spikeBlock != Blocks.AIR) {
                    for (Direction dir : Direction.values()) {
                        BlockPos otherPos = pos.offset(dir);
                        BlockState otherState = world.getBlockState(otherPos);

                        if (otherState.getBlock() == chewedBlock) {
                            int variation = otherState.getOrEmpty(VARIATION).orElse(-1);
                            if (variation >= 0 && variation <= 2) {
                                // Build states preserving properties
                                BlockState newSelfState = spikeBlock.getStateWithProperties(state)
                                        .with(Properties.FACING, dir)
                                        .with(VARIATION, 0); // this block always becomes variation 0

                                BlockState newOtherState = spikeBlock.getStateWithProperties(otherState)
                                        .with(Properties.FACING, dir.getOpposite())
                                        .with(VARIATION, variation); // preserve variation of chewed block

                                // Replace the chewed block
                                world.setBlockState(otherPos, newOtherState, Block.NOTIFY_ALL);

                                return newSelfState;
                            }
                        }

                        if (otherState.getBlock() == strippedBlock) {
                            int variation = otherState.getOrEmpty(VARIATION).orElse(-1);
                            if (variation >= 0 && variation <= 3) {
                                // Build states preserving properties
                                BlockState newSelfState = spikeBlock.getStateWithProperties(state)
                                        .with(Properties.FACING, dir)
                                        .with(VARIATION, 0); // this block always becomes variation 0

                                BlockState newOtherState = strippedBlock.getStateWithProperties(otherState);

                                // Replace the stripped block
                                world.setBlockState(otherPos, newOtherState, Block.NOTIFY_ALL);

                                return newSelfState;
                            }
                        }
                    }
                }
            }
        }

        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }

        world.emitGameEvent(GameEvent.BLOCK_DESTROY, pos, GameEvent.Emitter.of(player, state));
        return state;
    }
    **/


    @Override
    public boolean convertBlock(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        return world.setBlockState(pos, this.getReplacementState(world, pos, state));
    }

    @Override
    public void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        switch (state.get(VARIATION)) {
            case 0, 2:
                this.playSpecialBreakSound(world, pos, player);
        }
    }

    @Override
    public int getOutlineOffset() {
        return 1;
    }

    @Override
    public IntProperty getBreakLevel() {
        return VARIATION;
    }

    private BlockState getReplacementState(World world, BlockPos pos, BlockState currentState) {
        Block strippedVar = null;
        Block chewedVar = null;
        Block spikeVar = null;

        // Define blocks to change
        Identifier id = Registries.BLOCK.getId(currentState.getBlock());

        if (id.getPath().endsWith("_stripped")) {
            String base = id.getPath().replace("_stripped", "");
            strippedVar = currentState.getBlock(); // already stripped
            chewedVar = Registries.BLOCK.get(Identifier.of(id.getNamespace(), base + "_chewed"));
            spikeVar = Registries.BLOCK.get(Identifier.of(id.getNamespace(), base + "_spike"));
        }

        // Determine directions based on axis
        Direction.Axis axis = currentState.get(AXIS);
        Direction dirPos, dirNeg;
        BlockState statePos, stateNeg;

        switch (axis) {
            case X -> {
                dirPos = Direction.EAST;
                dirNeg = Direction.WEST;
                statePos = world.getBlockState(pos.west());
                stateNeg = world.getBlockState(pos.east());
            }
            case Z -> {
                dirPos = Direction.NORTH;
                dirNeg = Direction.SOUTH;
                statePos = world.getBlockState(pos.south());
                stateNeg = world.getBlockState(pos.north());
            }
            default -> {
                dirPos = Direction.UP;
                dirNeg = Direction.DOWN;
                statePos = world.getBlockState(pos.down());
                stateNeg = world.getBlockState(pos.up());
            }
        }

        // Check whether solid, full, non-replaceable block exists
        boolean hasPos = isSolidBlockAtBase(world, pos.offset(dirPos), statePos);
        boolean hasNeg = isSolidBlockAtBase(world, pos.offset(dirNeg), stateNeg);

        // Apply logic
        if (hasNeg && hasPos) {
            return chewedVar != null ? getChewedState(chewedVar, currentState) : currentState;
        }
        else if (hasNeg) {
            return getSpikeState(spikeVar, currentState, dirNeg);
        }
        else if (hasPos) {
            return getSpikeState(spikeVar, currentState, dirPos);
        }
        else if (currentState.get(VARIATION) == 3) {
            return Blocks.AIR.getDefaultState();
        }
        else {
            int level = currentState.get(VARIATION);
            return strippedVar != null
                    ? strippedVar.getStateWithProperties(currentState.with(VARIATION, (level + 1) % 4))
                    : currentState;
        }
    }

}