package dev.mruniverse.slimelib.storage;

import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.control.Control;
import dev.mruniverse.slimelib.input.InputManager;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;

public class DefaultFileStorage implements FileStorage {

    private final HashMap<SlimeFiles, Control> files = new HashMap<>();

    private final InputManager inputManager;

    private final ControlProvider provider;

    private SlimeFiles[] currentFiles;

    private final SlimePlatform type;

    private final File dataFolder;

    private final SlimeLogs logs;

    public DefaultFileStorage(SlimeLogs logs, SlimePlatform type, File dataFolder, SlimeFiles[] enums, InputManager inputManager) {
        this.dataFolder   = dataFolder;
        this.logs         = logs;
        this.inputManager = inputManager;
        this.currentFiles = enums;
        this.type         = type;
        this.provider = ControlProvider.create(type);
        load();
    }

    public DefaultFileStorage(SlimeLogs logs, SlimePlatform type, File dataFolder, InputManager inputManager) {
        this.dataFolder   = dataFolder;
        this.logs         = logs;
        this.type         = type;
        this.inputManager = inputManager;
        this.provider = ControlProvider.create(type);
    }

    public FileStorage setEnums(SlimeFiles[] enums) {
        this.currentFiles = enums;
        return this;
    }

    public void init() {
        if(currentFiles != null) {
            load();
        } else {
            logs.info("Enums aren't defined so can't init files if files doesn't exists");
        }
    }

    private void load() {
        for(SlimeFiles guardianFiles : currentFiles) {
            File mainFolder = dataFolder;

            if(guardianFiles.isInDifferentFolder()) {
                mainFolder = new File(dataFolder,guardianFiles.getFolderName());
            }

            if (guardianFiles.loadOnPlatform(type)) {

                File file = new File(
                        mainFolder,
                        guardianFiles.getFileName()
                );

                InputStream resource = inputManager.getInputStream(
                        guardianFiles.getResourceFileName(type)
                );

                files.put(
                        guardianFiles,
                        provider.create(
                                logs,
                                file,
                                resource
                        )
                );
            }
        }
    }

    @Override
    public File getFile(SlimeFiles fileToGet) {
        return files.get(fileToGet).getFile();
    }

    @Override
    public Control getControl(SlimeFiles file) {
        try {
            if (!files.containsKey(file)) {
                throw new SlimeFileNotLoadedException(file);
            }
            return files.get(file);
        } catch (SlimeFileNotLoadedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void reloadFile(SlimeFiles paramFile) {
        files.get(paramFile).reload();
    }

    @Override
    public void reloadFiles() {
        for (SlimeFiles file : currentFiles) {
            files.get(file).reload();
        }
    }

    @Override
    public void save(SlimeFiles paramFile) {
        files.get(paramFile).reload();
    }

    @Override
    public void saveFiles() {
        for (SlimeFiles file : currentFiles) {
            files.get(file).reload();
        }
    }
}
