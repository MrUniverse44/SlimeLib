package dev.mruniverse.guardianstorageapi.builder;

import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.interfaces.*;

import java.io.File;
import java.util.HashMap;

public class FileStorageBuilder implements FileStorage {

    private final boolean isBungee;

    private final HashMap<GuardianFiles, Control> files = new HashMap<>();

    private final GLogger logs;

    private final File dataFolder;

    private final InputManager inputManager;

    private final ControlType type;

    private GuardianFiles[] currentFiles;

    public FileStorageBuilder(GLogger logs,ControlType type,File dataFolder,GuardianFiles[] enums, InputManager inputManager) {
        this.dataFolder = dataFolder;
        this.logs = logs;
        this.inputManager = inputManager;
        this.isBungee = inputManager.isBungee();
        this.currentFiles = enums;
        this.type = type;
        load();
    }

    public FileStorageBuilder(GLogger logs,ControlType type,File dataFolder, InputManager inputManager) {
        this.dataFolder = dataFolder;
        this.logs = logs;
        this.type = type;
        this.inputManager = inputManager;
        this.isBungee = inputManager.isBungee();
    }

    public FileStorage setEnums(GuardianFiles[] enums) {
        this.currentFiles = enums;
        return this;
    }

    public void init() {
        if(currentFiles != null) {
            load();
        } else {
            logs.info("Enums aren't defined");
        }
    }

    private void load() {
        for(GuardianFiles guardianFiles : currentFiles) {
            File mainFolder = dataFolder;
            if(guardianFiles.isInDifferentFolder()) {
                mainFolder = new File(dataFolder,guardianFiles.getFolderName());
            }
            if(isBungee) {
                files.put(guardianFiles, new ControlBungeeBuilder(logs,
                        new File(mainFolder,guardianFiles.getFileName()),
                        inputManager.getInputStream(guardianFiles.getResourceFileName())
                ));
            } else {
                if(type == ControlType.SPIGOT) {
                    files.put(guardianFiles, new ControlSpigotBuilder(logs,
                            new File(mainFolder, guardianFiles.getFileName()),
                            inputManager.getInputStream(guardianFiles.getResourceFileName())
                    ));
                } else if(type == ControlType.VELOCITY){
                   files.put(guardianFiles,new ControlVelocityBuilder(logs,
                           new File(mainFolder, guardianFiles.getFileName()),
                           inputManager.getInputStream(guardianFiles.getResourceFileName())
                   ));
                }
            }
        }
    }

    @Override
    public File getFile(GuardianFiles fileToGet) {
        return files.get(fileToGet).getFile();
    }

    @Override
    public Control getControl(GuardianFiles file) {
        return files.get(file);
    }

    @Override
    public void reloadFile(boolean reloadAll,GuardianFiles paramFile) {
        if(reloadAll) {
            for (GuardianFiles file : currentFiles) {
                files.get(file).reload();
            }
            return;
        }
        files.get(paramFile).reload();
    }

    @Override
    public void save(boolean reloadAll,GuardianFiles paramFile) {
        if(reloadAll) {
            for (GuardianFiles file : currentFiles) {
                files.get(file).reload();
            }
            return;
        }
        files.get(paramFile).reload();
    }
}
