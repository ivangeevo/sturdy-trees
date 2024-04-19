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
                .add(SturdyTreesBlocks.LOG_OAK_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_OAK_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_OAK_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_OAK_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_OAK_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_OAK_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_OAK_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_OAK_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_OAK_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED_VAR3)

                // Birch Logs
                .add(SturdyTreesBlocks.LOG_BIRCH_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_BIRCH_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_BIRCH_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_BIRCH_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_BIRCH_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_BIRCH_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_BIRCH_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_BIRCH_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_BIRCH_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED_VAR3)

                // Spruce Logs
                .add(SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_SPRUCE_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_SPRUCE_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_SPRUCE_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_SPRUCE_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_SPRUCE_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED_VAR3)

                // Jungle Logs
                .add(SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_JUNGLE_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_JUNGLE_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_JUNGLE_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_JUNGLE_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_JUNGLE_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED_VAR3)

                // Acacia Logs
                .add(SturdyTreesBlocks.LOG_ACACIA_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_ACACIA_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_ACACIA_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_ACACIA_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_ACACIA_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_ACACIA_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_ACACIA_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_ACACIA_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_ACACIA_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED_VAR3)

                // Dark Oak Logs
                .add(SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED_VAR3)

                // Mangrove Logs
                .add(SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR1)
                .add(SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR2)
                .add(SturdyTreesBlocks.LOG_MANGROVE_BOT_VAR3)
                .add(SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR1)
                .add(SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR2)
                .add(SturdyTreesBlocks.LOG_MANGROVE_TOP_VAR3)
                .add(SturdyTreesBlocks.LOG_MANGROVE_MID_VAR1)
                .add(SturdyTreesBlocks.LOG_MANGROVE_MID_VAR2)
                .add(SturdyTreesBlocks.LOG_MANGROVE_MID_VAR3)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR0)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR1)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR2)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED_VAR3);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .forceAddTag(SturdyTreesTags.Blocks.STRIPPED_LOG_BLOCKS);




    }
}
