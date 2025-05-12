package ivangeevo.sturdy_trees.block.blocks;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public abstract class ConvertingLogBlock extends PillarBlock {

    public static final IntProperty VARIATION = IntProperty.of("variation", 0, 3);
    public static final BooleanProperty CHARRED = BooleanProperty.of("charred");

    public ConvertingLogBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
                .with(VARIATION, 0)
                .with(CHARRED, false)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(VARIATION, CHARRED);
    }

    @Override
    public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack tool) {
        this.tryConvert(world, pos, state, player);
        super.afterBreak(world, player, pos, state, blockEntity, tool);
    }

    protected void tryConvert(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.playSoundsOnBreak(world, pos, state, player);
    }

    protected void playSoundsOnBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {}

    protected void playSpecialBreakSound(World world, BlockPos pos, PlayerEntity player) {
        world.playSound(null, pos, this.getSpecialBreakSound(), SoundCategory.BLOCKS, 0.1F,
                1.25F + (player.getWorld().random.nextFloat() * 0.25F));
    }

    protected SoundEvent getSpecialBreakSound() {
        return SoundEvents.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR;
    }

}
