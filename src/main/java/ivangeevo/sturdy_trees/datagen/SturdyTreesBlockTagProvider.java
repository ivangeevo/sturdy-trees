package ivangeevo.sturdy_trees.datagen;

import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.tag.BTWRConventionalTags;
import ivangeevo.sturdy_trees.tag.SturdyTreesTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SturdyTreesBlockTagProvider extends FabricTagProvider.BlockTagProvider {

    public SturdyTreesBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        addToVanillaTags();
        addToModTags();
        addToConventionalTags();
    }

    private void addToConventionalTags() {

        getOrCreateTagBuilder(BTWRConventionalTags.Blocks.MODDED_CONVERTING_BLOCKS)
                .addTag(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS);

        getOrCreateTagBuilder(BTWRConventionalTags.Blocks.STUMP_BLOCKS)
                .add(SturdyTreesBlocks.STUMP_OAK)
                .add(SturdyTreesBlocks.STUMP_BIRCH)
                .add(SturdyTreesBlocks.STUMP_SPRUCE)
                .add(SturdyTreesBlocks.STUMP_JUNGLE)
                .add(SturdyTreesBlocks.STUMP_ACACIA)
                .add(SturdyTreesBlocks.STUMP_DARK_OAK)
                .add(SturdyTreesBlocks.STUMP_MANGROVE)
                .add(SturdyTreesBlocks.STUMP_CHERRY)

                .add(SturdyTreesBlocks.STUMP_OAK_VAR1)
                .add(SturdyTreesBlocks.STUMP_BIRCH_VAR1)
                .add(SturdyTreesBlocks.STUMP_SPRUCE_VAR1)
                .add(SturdyTreesBlocks.STUMP_JUNGLE_VAR1)
                .add(SturdyTreesBlocks.STUMP_ACACIA_VAR1)
                .add(SturdyTreesBlocks.STUMP_DARK_OAK_VAR1)
                .add(SturdyTreesBlocks.STUMP_MANGROVE_VAR1)
                .add(SturdyTreesBlocks.STUMP_CHERRY_VAR1);

    }

    private void addToModTags() {

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS)

                // Oak Logs
                .add(SturdyTreesBlocks.LOG_OAK_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_OAK_CHEWED)
                .add(SturdyTreesBlocks.LOG_OAK_STRIPPED)

                // Birch Logs
                .add(SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_BIRCH_CHEWED)
                .add(SturdyTreesBlocks.LOG_BIRCH_STRIPPED)

                // Spruce Logs
                .add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_SPRUCE_CHEWED)
                .add(SturdyTreesBlocks.LOG_SPRUCE_STRIPPED)

                // Jungle Logs
                .add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_JUNGLE_CHEWED)
                .add(SturdyTreesBlocks.LOG_JUNGLE_STRIPPED)

                // Acacia Logs
                .add(SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_ACACIA_CHEWED)
                .add(SturdyTreesBlocks.LOG_ACACIA_STRIPPED)

                // Dark Oak Logs
                .add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_CHEWED)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_STRIPPED)

                // Mangrove Logs
                .add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_MANGROVE_CHEWED)
                .add(SturdyTreesBlocks.LOG_MANGROVE_STRIPPED)

                // Cherry Logs
                .add(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_CHERRY_CHEWED)
                .add(SturdyTreesBlocks.LOG_CHERRY_STRIPPED);
        
        getOrCreateTagBuilder(SturdyTreesTags.Blocks.LOG_SPIKE_UP_BLOCKS)
                .add(SturdyTreesBlocks.LOG_OAK_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_BIRCH_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_ACACIA_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_UP)
                .add(SturdyTreesBlocks.LOG_CHERRY_SPIKE_UP);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.LOG_SPIKE_DOWN_BLOCKS)
                .add(SturdyTreesBlocks.LOG_OAK_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_SPRUCE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_BIRCH_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_JUNGLE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_ACACIA_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_DARK_OAK_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_MANGROVE_SPIKE_DOWN)
                .add(SturdyTreesBlocks.LOG_CHERRY_SPIKE_DOWN);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.STRAIGHT_TRUNK_TREES)
                .add(Blocks.OAK_LOG)
                .add(Blocks.BIRCH_LOG)
                .add(Blocks.SPRUCE_LOG)
                .add(Blocks.JUNGLE_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.LARGE_OAK_TRUNK_TREES)
                .add(Blocks.OAK_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.DARK_OAK_TRUNK_TREES)
                .add(Blocks.DARK_OAK_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.FORKING_TRUNK_TREES)
                .add(Blocks.ACACIA_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.MEGA_JUNGLE_TRUNK_TREES)
                .add(Blocks.JUNGLE_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.GIANT_TRUNK_TREES)
                .add(Blocks.SPRUCE_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.BENDING_TRUNK_TREES)
                .add(Blocks.OAK_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.UPWARDS_BRANCHING_TRUNK_TREES)
                .add(Blocks.MANGROVE_LOG);

        getOrCreateTagBuilder(SturdyTreesTags.Blocks.CHERRY_TRUNK_TREES)
                .add(Blocks.CHERRY_LOG);


    }

    private void addToVanillaTags() {

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .forceAddTag(BlockTags.LOGS)
                .forceAddTag(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS)
                .forceAddTag(BlockTags.LEAVES);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .forceAddTag(SturdyTreesTags.Blocks.LOG_VARIATION_BLOCKS);
    }
}
