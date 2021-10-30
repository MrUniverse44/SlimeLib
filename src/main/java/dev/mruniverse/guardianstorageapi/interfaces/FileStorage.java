package dev.mruniverse.guardianstorageapi.interfaces;


import java.io.File;

public interface FileStorage {

    File getFile(GuardianFiles fileToGet);

    Control getControl(GuardianFiles file);

    FileStorage setEnums(GuardianFiles[] enums);

    void init();

    void reloadFile(boolean reloadAll,GuardianFiles file);

    void save(boolean reloadAll,GuardianFiles file);
}
