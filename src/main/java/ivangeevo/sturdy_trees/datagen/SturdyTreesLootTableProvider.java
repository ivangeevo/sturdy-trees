package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.state.property.ModProperties;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class SturdyTreesLootTableProvider extends FabricBlockLootTableProvider {


    protected SturdyTreesLootTableProvider (FabricDataOutput dataOutput) {super(dataOutput);}

    @Override
    public void generate() {
        addDrop(SturdyTreesBlocks.LOG_SPIKE_OAK.getDefaultState().with(ModProperties.BREAK_LEVEL_STRIPPED,0).getBlock());
        addDrop(SturdyTreesBlocks.LOG_SPIKE_OAK.getDefaultState().with(ModProperties.BREAK_LEVEL_STRIPPED,0).getBlock());


    }
}
