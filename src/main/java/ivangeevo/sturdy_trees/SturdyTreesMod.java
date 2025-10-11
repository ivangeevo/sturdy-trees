package ivangeevo.sturdy_trees;

import com.google.gson.Gson;
import ivangeevo.sturdy_trees.block.SturdyTreesBlocks;
import ivangeevo.sturdy_trees.config.SturdyTreesSettings;
import ivangeevo.sturdy_trees.item.SturdyTreesItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SturdyTreesMod implements ModInitializer {

    public static final String MOD_ID = "sturdy_trees";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public SturdyTreesSettings settings;

    private static SturdyTreesMod instance;

    public static SturdyTreesMod getInstance() {
        return instance;
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing Sturdy Trees");
        this.loadSettings();
        instance = this;

        SturdyTreesBlocks.registerModBlocks();
        SturdyTreesItems.registerModItems();
        TreeBreakHandler.registerBreakEvent();
    }

    public void loadSettings() {
        File file = new File("./config/btwr/sturdyTreesCommon.json");
        Gson gson = new Gson();
        if (file.exists()) {
            try {
                FileReader fileReader = new FileReader(file);
                settings = gson.fromJson(fileReader, SturdyTreesSettings.class);
                fileReader.close();
            } catch (IOException e) {
                LOGGER.warn("Could not load sturdy_trees settings: " + e.getLocalizedMessage());
            }
        } else {
            settings = new SturdyTreesSettings();
        }
    }

    public void saveSettings() {
        Gson gson = new Gson();
        File file = new File("./config/btwr/sturdyTreesCommon.json");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(settings));
            fileWriter.close();
        }

        catch (IOException e) {
            LOGGER.warn("Could not save sturdy_trees settings: " + e.getLocalizedMessage());
        }
    }

}
