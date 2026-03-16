package org.btwr.sturdy_trees.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.btwr.shared_library.util.utils.IdUtils;
import org.btwr.sturdy_trees.block.blocks.SmallSaplingBlock;
import org.btwr.sturdy_trees.item.SturdyTreesItems;

public class SturdyTreesModelProvider extends FabricModelProvider {

    public SturdyTreesModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        this.registerSmallSaplings(blockStateModelGenerator);
    }

    private void registerSmallSaplings(BlockStateModelGenerator modelGen) {
        String[] smallSaplings = new String[] {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "cherry"};
        for (String type : smallSaplings) {
            Identifier blockId = IdUtils.ofST(type + "_sapling_small");
            Block block = Registries.BLOCK.get(blockId);

            // register the item models
            Models.GENERATED.upload(
                    IdUtils.ofST("item/" + type + "_sapling_small"),
                    TextureMap.layer0(IdUtils.ofST("block/" + type + "_sapling_small_stage0")),
                    modelGen.modelCollector
            );

            Identifier modelId0 = ModelIds.getBlockSubModelId(block, "_stage0");
            Identifier modelId1 = ModelIds.getBlockSubModelId(block, "_stage1");
            Identifier modelId2 = ModelIds.getBlockSubModelId(block, "_stage2");

            // register the block models
            Models.CROSS.upload(modelId0, TextureMap.cross(modelId0), modelGen.modelCollector);
            Models.CROSS.upload(modelId1, TextureMap.cross(modelId1), modelGen.modelCollector);
            Models.CROSS.upload(modelId2, TextureMap.cross(modelId2), modelGen.modelCollector);

            // register the blockstates via multipart
            modelGen.blockStateCollector
                    .accept(
                            MultipartBlockStateSupplier.create(block)
                                    .with(
                                            When.create().set(SmallSaplingBlock.GROW_STAGE, 0),
                                            BlockStateVariant.create().put(VariantSettings.MODEL, modelId0)
                                    )
                                    .with(
                                            When.create().set(SmallSaplingBlock.GROW_STAGE, 1),
                                            BlockStateVariant.create().put(VariantSettings.MODEL, modelId1)
                                    )
                                    .with(
                                            When.create().set(SmallSaplingBlock.GROW_STAGE, 2),
                                            BlockStateVariant.create().put(VariantSettings.MODEL, modelId2)
                                    )
                    );
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(SturdyTreesItems.STUMP_REMOVER, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.DUST_SAW, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_OAK, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_BIRCH, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_SPRUCE, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_JUNGLE, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_ACACIA, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_DARK_OAK, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_MANGROVE, Models.GENERATED);
        itemModelGenerator.register(SturdyTreesItems.BARK_CHERRY, Models.GENERATED);
    }
}
