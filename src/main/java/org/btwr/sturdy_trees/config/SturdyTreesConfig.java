package org.btwr.sturdy_trees.config;

import org.btwr.shared_library.api.config.ConfigBuilder;
import org.btwr.shared_library.api.config.ConfigGroup;
import org.btwr.shared_library.api.config.ConfigSetting;
import org.btwr.shared_library.api.config.TomlConfigManager;
import org.btwr.sturdy_trees.SturdyTreesMod;

import java.util.function.Supplier;

public class SturdyTreesConfig {

    /** Replace with your MOD_ID for easy adaptation **/
    private static final String MOD_ID = SturdyTreesMod.MOD_ID;

    public static final ConfigGroup CONFIG;

    /** Call this method in your mod initializer so the class can initialize **/
    public static void register() {}

    public static final ConfigSetting<Boolean> saplingsFertilizable =
            ConfigBuilder.booleanSetting("saplingsFertilizable")
                    .defaultValue(false)
                    .comment("Toggles whether saplings are fertilizable in the normal manner")
                    .build();

    static {
           CONFIG = new ConfigGroup(String.format("%s/%s_common.toml", MOD_ID, MOD_ID));
           CONFIG.add(saplingsFertilizable);
           TomlConfigManager.registerGroup(CONFIG); // auto init/load/save
    }

}