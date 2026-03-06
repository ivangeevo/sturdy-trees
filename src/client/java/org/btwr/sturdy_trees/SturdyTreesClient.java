package org.btwr.sturdy_trees;

import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import org.btwr.sturdy_trees.block.render.SturdyTreesBlockRenderLayers;
import org.btwr.sturdy_trees.particle.CindersParticle;
import org.btwr.sturdy_trees.particle.SlowWhiteSmoke;
import org.btwr.sturdy_trees.particle.SturdyTreesParticles;

public class SturdyTreesClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SturdyTreesBlockRenderLayers.register();

        ParticleFactoryRegistry.getInstance().register(SturdyTreesParticles.SLOW_WHITE_SMOKE, SlowWhiteSmoke.Factory::new);
        ParticleFactoryRegistry.getInstance().register(SturdyTreesParticles.CINDERS, CindersParticle.Factory::new);

    }


}