package org.btwr.sturdy_trees.particle;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.btwr.sturdy_trees.SturdyTreesMod;

public class SturdyTreesParticles {

    public static final SimpleParticleType CINDERS = FabricParticleTypes.simple();
    public static final SimpleParticleType SLOW_WHITE_SMOKE = FabricParticleTypes.simple();

    /** Creates a new simple particle type **/
    private static void registerSimple(String name, SimpleParticleType type) {
        Registry.register(Registries.PARTICLE_TYPE, Identifier.of(SturdyTreesMod.MOD_ID, name), type);
    }

    public static void register() {
        SturdyTreesMod.LOGGER.info("Registering Mod Particles for " + SturdyTreesMod.MOD_ID);

        registerSimple("cinders", CINDERS);
        registerSimple("slow_white_smoke", SLOW_WHITE_SMOKE);
    }
}
