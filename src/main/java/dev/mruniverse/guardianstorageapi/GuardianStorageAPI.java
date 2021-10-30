package dev.mruniverse.guardianstorageapi;

import dev.mruniverse.guardianstorageapi.builder.ControlBungeeBuilder;
import dev.mruniverse.guardianstorageapi.builder.ControlSpigotBuilder;
import dev.mruniverse.guardianstorageapi.builder.FileStorageBuilder;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.interfaces.*;

import java.io.File;

@SuppressWarnings("unused")
public final class GuardianStorageAPI {

    private ControlType type = ControlType.SPIGOT;

    private GLogger logs = null;

    private InputManager manager = null;

    public GuardianStorageAPI(ControlType type) {
        this.type = type;
    }

    public GuardianStorageAPI(ControlType type, GLogger logs,InputManager manager) {
        this.type = type;
        this.logs = logs;
        this.manager = manager;
    }

    public GuardianStorageAPI(ControlType type, GLogger logs) {
        this.type = type;
        this.logs = logs;
    }

    public GuardianStorageAPI(GLogger logs) {
        this.logs = logs;
    }

    public GuardianStorageAPI(InputManager manager) {
        this.manager = manager;
    }


    public GuardianStorageAPI setInputManager(InputManager manager) {
        this.manager = manager;
        return this;
    }

    public GuardianStorageAPI setType(ControlType type) {
        this.type = type;
        return this;
    }

    public GuardianStorageAPI setLogs(GLogger logs) {
        this.logs = logs;
        return this;
    }

    public ControlType getType() {
        return type;
    }

    public Control createControlFile(File pluginDataFolder,GuardianFiles fileInfo,String resource,boolean includeResource) {
        if(logs == null) return null;
        File dataFolder = pluginDataFolder;
        if(fileInfo.isInDifferentFolder()) {
            dataFolder = new File(pluginDataFolder,fileInfo.getFolderName());
        }
        File finalFile = new File(dataFolder,fileInfo.getFileName());
        if(!includeResource) {
            if (type == ControlType.BUNGEECORD) return new ControlBungeeBuilder(logs, finalFile);
            return new ControlSpigotBuilder(logs, finalFile);
        }
        if(manager == null) return null;
        if (type == ControlType.BUNGEECORD) return new ControlBungeeBuilder(logs, finalFile,manager.getInputStream(resource));
        return new ControlSpigotBuilder(logs, finalFile,manager.getInputStream(resource));
    }

    public FileStorage createStorage(File dataFolder,GuardianFiles[] enums) {
        return new FileStorageBuilder(logs,dataFolder,enums,manager);
    }

    public FileStorage createStorage(File dataFolder) {
        return new FileStorageBuilder(logs,dataFolder,manager);
    }

}
