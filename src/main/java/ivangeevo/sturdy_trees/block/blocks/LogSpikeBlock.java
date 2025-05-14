package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class LogSpikeBlock extends LogStrippedBlock {

    public static final DirectionProperty FACING = Properties.FACING;

    public LogSpikeBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.UP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACING);
    }

    @Override
    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int breakLevel = state.get(VARIATION);
        if (breakLevel >= 2) { return; }
        world.setBlockState(pos, getStateWithProperties(state.with(VARIATION, breakLevel + 1)));
        this.playSoundsOnBreak(world, pos, state, player);
    }

    @Override
    protected int getOutlineOffset() {
        return 2;
    }

    @Override
    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (state.get(VARIATION) == 1) {
            this.playSpecialBreakSound(world, pos, player);
        }
    }

}