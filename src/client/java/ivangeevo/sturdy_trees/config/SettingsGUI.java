package ivangeevo.sturdy_trees.config;

import ivangeevo.sturdy_trees.SturdyTreesMod;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SettingsGUI {

    static SturdyTreesSettings settingsCommon = SturdyTreesMod.getInstance().settings;

    public static Screen createConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.sturdy_trees.config"));

        builder.setSavingRunnable(() -> SturdyTreesMod.getInstance().saveSettings());

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory general = builder.getOrCreateCategory(Text.translatable("config.sturdy_trees.category.general"));

        /** General Category**/

        general.addEntry(entryBuilder
                .startBooleanToggle(Text.translatable("config.sturdy_trees.isSaplingsFertilizable"), settingsCommon.isSaplingsFertilizable)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> settingsCommon.isSaplingsFertilizable = newValue)
                .setTooltip(Text.translatable("config.sturdy_trees.tooltip.isSaplingsFertilizable"))
                .build());

        return builder.build();
    }

}
