package me.blueslime.slimelib.file.storage;


import me.blueslime.slimelib.SlimeFiles;
import me.blueslime.slimelib.file.configuration.ConfigurationHandler;

import java.io.File;

public interface FileStorage {

    File getFile(SlimeFiles fileToGet);

    ConfigurationHandler getConfigurationHandler(SlimeFiles file);

    FileStorage setEnums(SlimeFiles[] enums);

    /**
     * Replace a SlimeFile with another one
     * @param replaced Replaced File
     * @param newFile New File
     */
    void replace(SlimeFiles replaced, SlimeFiles newFile);

    void init();

    void reloadFile(SlimeFiles file);

    void reloadFiles();

    void saveFiles();

    void save(SlimeFiles paramFile);

}
