package dev.mruniverse.slimelib.storage;


import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.control.Control;

import java.io.File;

public interface FileStorage {

    File getFile(SlimeFiles fileToGet);

    Control getControl(SlimeFiles file);

    FileStorage setEnums(SlimeFiles[] enums);

    void init();

    void reloadFile(SlimeFiles file);

    void reloadFiles();

    void saveFiles();

    void save(SlimeFiles paramFile);

}
