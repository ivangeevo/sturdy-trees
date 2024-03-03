package ivangeevo.sturdy_trees.block.blocks;

import ivangeevo.sturdy_trees.ConvertingLogBlock;
import ivangeevo.sturdy_trees.block.util.LogType;
import ivangeevo.sturdy_trees.state.property.ModProperties;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

public class LogChewed extends ConvertingLogBlock {

    public static final IntProperty BREAK_LEVEL = ModProperties.BREAK_LEVEL;

    public LogChewed(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(BREAK_LEVEL, 1));

    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(BREAK_LEVEL);
    }


}
