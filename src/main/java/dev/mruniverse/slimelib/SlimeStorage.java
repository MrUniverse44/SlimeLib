package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.control.Control;

import dev.mruniverse.slimelib.control.bungee.ControlBungeeBuilder;
import dev.mruniverse.slimelib.control.spigot.ControlSpigotBuilder;
import dev.mruniverse.slimelib.control.velocity.ControlVelocityBuilder;
import dev.mruniverse.slimelib.input.InputManager;

import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.FileStorage;
import dev.mruniverse.slimelib.storage.FileStorageBuilder;

import java.io.File;

@SuppressWarnings("unused")
public final class SlimeStorage {

    private PluginMode type = PluginMode.SPIGOT;

    private SlimeLogs logs = null;

    private InputManager manager = null;

    public SlimeStorage(PluginMode type) {
        this.type = type;
    }

    public SlimeStorage(PluginMode type, SlimeLogs logs, InputManager manager) {
        this.type = type;
        this.logs = logs;
        this.manager = manager;
    }

    public SlimeStorage(PluginMode type, SlimeLogs logs) {
        this.type = type;
        this.logs = logs;
    }

    public SlimeStorage(SlimeLogs logs) {
        this.logs = logs;
    }

    public SlimeStorage(InputManager manager) {
        this.manager = manager;
    }


    public SlimeStorage setInputManager(InputManager manager) {
        this.manager = manager;
        return this;
    }

    public SlimeStorage setType(PluginMode type) {
        this.type = type;
        return this;
    }

    public SlimeStorage setLogs(SlimeLogs logs) {
        this.logs = logs;
        return this;
    }

    public PluginMode getType() {
        return type;
    }

    public Control createControlFile(File pluginDataFolder, SlimeFiles fileInfo, String resource, boolean includeResource) {
        if(logs == null) return null;
        File dataFolder = pluginDataFolder;

        if(fileInfo.isInDifferentFolder()) {
            dataFolder = new File(pluginDataFolder,fileInfo.getFolderName());
        }

        File finalFile = new File(dataFolder,fileInfo.getFileName());

        if(!includeResource) {
            if (type == PluginMode.BUNGEECORD) {
                return new ControlBungeeBuilder(logs, finalFile);
            }
            return new ControlSpigotBuilder(logs, finalFile);
        }

        if(manager == null) {
            return null;
        }

        if (type == PluginMode.BUNGEECORD) {
            return new ControlBungeeBuilder(logs, finalFile,manager.getInputStream(resource));
        }

        if (type == PluginMode.VELOCITY) {
            return new ControlVelocityBuilder(logs, finalFile,manager.getInputStream(resource));
        }

        return new ControlSpigotBuilder(logs, finalFile,manager.getInputStream(resource));
    }

    public FileStorage createStorage(File dataFolder, SlimeFiles[] enums) {
        return new FileStorageBuilder(logs,type,dataFolder,enums,manager);
    }

    public FileStorage createStorage(File dataFolder) {
        return new FileStorageBuilder(logs,type,dataFolder,manager);
    }

}
