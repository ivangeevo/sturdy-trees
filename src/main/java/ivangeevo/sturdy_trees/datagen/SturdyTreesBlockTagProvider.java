package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SturdyTreesBlockTagProvider extends FabricTagProvider.BlockTagProvider {


    public SturdyTreesBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }



    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(SturdyTreesTags.Blocks.STUMP_BLOCKS)
                .add(SturdyTreesBlocks.STUMP_OAK)
                .add(SturdyTreesBlocks.STUMP_BIRCH)
                .add(SturdyTreesBlocks.STUMP_SPRUCE)
                .add(SturdyTreesBlocks.STUMP_JUNGLE)
                .add(SturdyTreesBlocks.STUMP_ACACIA)
                .add(SturdyTreesBlocks.STUMP_DARK_OAK)
                .add(SturdyTreesBlocks.STUMP_MANGROVE);


        getOrCreateTagBuilder(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS)

                .add(SturdyTreesBlocks.LOG_STRIPPED_OAK)
                .add(SturdyTreesBlocks.LOG_STRIPPED_BIRCH)
                .add(SturdyTreesBlocks.LOG_STRIPPED_SPRUCE)
                .add(SturdyTreesBlocks.LOG_STRIPPED_JUNGLE)
                .add(SturdyTreesBlocks.LOG_STRIPPED_ACACIA)
                .add(SturdyTreesBlocks.LOG_STRIPPED_DARK_OAK)
                .add(SturdyTreesBlocks.LOG_STRIPPED_MANGROVE)
                .add(SturdyTreesBlocks.LOG_STRIPPED_CHERRY)
                .add(SturdyTreesBlocks.LOG_STRIPPED_WARPED);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.SPIKE_LOG_BLOCKS)

                .add(SturdyTreesBlocks.LOG_SPIKE_OAK)
                .add(SturdyTreesBlocks.LOG_SPIKE_BIRCH)
                .add(SturdyTreesBlocks.LOG_SPIKE_SPRUCE)
                .add(SturdyTreesBlocks.LOG_SPIKE_JUNGLE)
                .add(SturdyTreesBlocks.LOG_SPIKE_ACACIA)
                .add(SturdyTreesBlocks.LOG_SPIKE_DARK_OAK)
                .add(SturdyTreesBlocks.LOG_SPIKE_MANGROVE)
                .add(SturdyTreesBlocks.LOG_SPIKE_CHERRY)
                .add(SturdyTreesBlocks.LOG_SPIKE_WARPED);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.CHEWED_LOG_BLOCKS)

                .add(SturdyTreesBlocks.LOG_CHEWED_OAK)
                .add(SturdyTreesBlocks.LOG_CHEWED_BIRCH)
                .add(SturdyTreesBlocks.LOG_CHEWED_SPRUCE)
                .add(SturdyTreesBlocks.LOG_CHEWED_JUNGLE)
                .add(SturdyTreesBlocks.LOG_CHEWED_ACACIA)
                .add(SturdyTreesBlocks.LOG_CHEWED_DARK_OAK)
                .add(SturdyTreesBlocks.LOG_CHEWED_MANGROVE)
                .add(SturdyTreesBlocks.LOG_CHEWED_CHERRY)
                .add(SturdyTreesBlocks.LOG_CHEWED_WARPED);
                
                

        getOrCreateTagBuilder(BlockTags.LOGS)
                .forceAddTag(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS)
                .forceAddTag(SturdyTreesTags.Blocks.SPIKE_LOG_BLOCKS)
                .forceAddTag(SturdyTreesTags.Blocks.CHEWED_LOG_BLOCKS);


    }
}
