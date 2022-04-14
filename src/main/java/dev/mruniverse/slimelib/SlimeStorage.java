package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.control.Control;

import dev.mruniverse.slimelib.input.InputManager;

import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.ControlProvider;
import dev.mruniverse.slimelib.storage.FileStorage;
import dev.mruniverse.slimelib.storage.DefaultFileStorage;

import java.io.File;

@SuppressWarnings("unused")
public final class SlimeStorage {

    private ControlProvider provider;

    private SlimePlatform type = SlimePlatform.SPIGOT;

    private InputManager manager = null;

    private SlimeLogs logs = null;

    public SlimeStorage(SlimePlatform type) {
        this.type = type;
    }

    public SlimeStorage(SlimePlatform type, SlimeLogs logs, InputManager manager) {
        this.type = type;
        this.logs = logs;
        this.manager = manager;
        this.provider = ControlProvider.create(type);
    }

    public SlimeStorage(SlimePlatform type, SlimeLogs logs) {
        this.type = type;
        this.logs = logs;
        this.provider = ControlProvider.create(type);
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

    public SlimeStorage setType(SlimePlatform type) {
        this.type = type;
        return this;
    }

    public SlimeStorage setLogs(SlimeLogs logs) {
        this.logs = logs;
        return this;
    }

    public Control createControlFile(File pluginDataFolder, SlimeFiles slimeFile, String resource, boolean includeResource) {

        if(logs == null) {
            return null;
        }

        File dataFolder = pluginDataFolder;

        if(slimeFile.isInDifferentFolder()) {
            dataFolder = new File(
                    pluginDataFolder,
                    slimeFile.getFolderName()
            );
        }

        File file = new File(
                dataFolder,
                slimeFile.getFileName()
        );

        if (!includeResource) {
            return provider.create(
                    logs,
                    file
            );
        }

        if(manager == null) {
            return null;
        }

        return provider.create(
                logs,
                file,
                manager.getInputStream(
                        resource
                )
        );
    }

    public FileStorage createStorage(File dataFolder, SlimeFiles[] enums) {
        return new DefaultFileStorage(
                logs,
                type,
                dataFolder,
                enums,
                manager
        );
    }

    public FileStorage createStorage(File dataFolder) {
        return new DefaultFileStorage(
                logs,
                type,
                dataFolder,
                manager
        );
    }

    public SlimePlatform getType() {
        return type;
    }

}
