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
    protected void configure(RegistryWrapper.WrapperLookup arg)
    {

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.STUMP_BLOCKS)
                .add(SturdyTreesBlocks.STUMP_OAK)
                .add(SturdyTreesBlocks.STUMP_BIRCH)
                .add(SturdyTreesBlocks.STUMP_SPRUCE)
                .add(SturdyTreesBlocks.STUMP_JUNGLE)
                .add(SturdyTreesBlocks.STUMP_ACACIA)
                .add(SturdyTreesBlocks.STUMP_DARK_OAK)
                .add(SturdyTreesBlocks.STUMP_MANGROVE);



        getOrCreateTagBuilder(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS)
                
                // Oak Logs
                .add(SturdyTreesBlocks.LOG_OAK_SPIKE)
                .add(SturdyTreesBlocks.LOG_OAK_CHEWED)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED)

                // Birch Logs
                .add(SturdyTreesBlocks.LOG_BIRCH_SPIKE)
                .add(SturdyTreesBlocks.LOG_BIRCH_CHEWED)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)

                // Spruce Logs
                .add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE)
                .add(SturdyTreesBlocks.LOG_SPRUCE_CHEWED)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)

                // Jungle Logs
                .add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE)
                .add(SturdyTreesBlocks.LOG_JUNGLE_CHEWED)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)

                // Acacia Logs
                .add(SturdyTreesBlocks.LOG_ACACIA_SPIKE)
                .add(SturdyTreesBlocks.LOG_ACACIA_CHEWED)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)

                // Dark Oak Logs
                .add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_CHEWED)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)

                // Mangrove Logs
                .add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE)
                .add(SturdyTreesBlocks.LOG_MANGROVE_CHEWED)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .forceAddTag(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS);




    }
}
