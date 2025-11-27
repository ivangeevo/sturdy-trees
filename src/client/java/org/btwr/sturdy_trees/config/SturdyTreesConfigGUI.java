package org.btwr.sturdy_trees.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SturdyTreesConfigGUI {

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.sturdy_trees.config"));

        //builder.setSavingRunnable(() -> SturdyTreesMod.getInstance().saveSettings());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.sturdy_trees.category.general"));

        // Client Settings
        general.addEntry(entryBuilder.startTextDescription(Text.translatable("config.sturdy_trees.text.clientSettingsText")).build());
        general.addEntry(entryBuilder
                .startTextDescription(Text.translatable("config.sturdy_trees.text.emptyClientConfigText"))
                .build()
        );

        // Server Settings
        general.addEntry(entryBuilder.startTextDescription(Text.translatable("config.sturdy_trees.text.serverSettingsText")).build());
        general.addEntry(entryBuilder
                .startTextDescription(Text.translatable("config.sturdy_trees.text.serverSettingsNoAccessText"))
                .build()
        );

        return builder.build();
    }

}