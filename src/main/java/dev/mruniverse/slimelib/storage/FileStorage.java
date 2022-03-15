package dev.mruniverse.slimelib.storage;


import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.control.Control;

import java.io.File;

public interface FileStorage {

    File getFile(SlimeFiles fileToGet);

    Control getControl(SlimeFiles file);

    FileStorage setEnums(SlimeFiles[] enums);

    void init();

    void reloadFile(boolean reloadAll, SlimeFiles file);

    void save(boolean reloadAll, SlimeFiles file);
}
