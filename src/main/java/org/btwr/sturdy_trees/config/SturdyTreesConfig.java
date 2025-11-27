package org.btwr.sturdy_trees.config;

import com.google.common.reflect.Reflection;
import com.supermartijn642.configlib.api.ConfigBuilders;
import com.supermartijn642.configlib.api.IConfigBuilder;
import org.btwr.sturdy_trees.SturdyTreesMod;

import java.util.function.Supplier;

public class SturdyTreesConfig {

    public static void register() {
        Reflection.initialize(Settings.class);
    }

    public static class Settings {
        public static final Supplier<Boolean> saplingsFertilizable;

        static {
            // construct a new config builder
            IConfigBuilder builder = ConfigBuilders.newTomlConfig(SturdyTreesMod.MOD_ID, "btwr_core", true);

            // Boolean checks
            saplingsFertilizable = builder
                    .comment("Disables knockback if not using a suitable weapon")
                    .define("knockbackRestrictions", true);

            // build the config
            builder.build();
        }
    }

}