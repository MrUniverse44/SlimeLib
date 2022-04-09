package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.control.Control;

import dev.mruniverse.slimelib.control.bungee.ControlBungeeBuilder;
import dev.mruniverse.slimelib.control.spigot.ControlSpigotBuilder;
import dev.mruniverse.slimelib.control.sponge.ControlSpongeBuilder;
import dev.mruniverse.slimelib.control.velocity.ControlVelocityBuilder;
import dev.mruniverse.slimelib.input.InputManager;

import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.FileStorage;
import dev.mruniverse.slimelib.storage.DefaultFileStorage;

import java.io.File;

@SuppressWarnings("unused")
public final class SlimeStorage {

    private SlimePlatform type = SlimePlatform.SPIGOT;

    private SlimeLogs logs = null;

    private InputManager manager = null;

    public SlimeStorage(SlimePlatform type) {
        this.type = type;
    }

    public SlimeStorage(SlimePlatform type, SlimeLogs logs, InputManager manager) {
        this.type = type;
        this.logs = logs;
        this.manager = manager;
    }

    public SlimeStorage(SlimePlatform type, SlimeLogs logs) {
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

    public SlimeStorage setType(SlimePlatform type) {
        this.type = type;
        return this;
    }

    public SlimeStorage setLogs(SlimeLogs logs) {
        this.logs = logs;
        return this;
    }

    public SlimePlatform getType() {
        return type;
    }

    public Control createControlFile(File pluginDataFolder, SlimeFiles fileInfo, String resource, boolean includeResource) {

        if(logs == null) {
            return null;
        }

        File dataFolder = pluginDataFolder;

        if(fileInfo.isInDifferentFolder()) {
            dataFolder = new File(
                    pluginDataFolder,
                    fileInfo.getFolderName()
            );
        }

        File finalFile = new File(
                dataFolder,
                fileInfo.getFileName()
        );

        if (!includeResource) {
            if (type == SlimePlatform.BUNGEECORD) {
                return new ControlBungeeBuilder(
                        logs,
                        finalFile
                );
            }
            if (type == SlimePlatform.VELOCITY) {
                return new ControlVelocityBuilder(
                        logs,
                        finalFile
                );
            }
            return new ControlSpigotBuilder(
                    logs,
                    finalFile
            );
        }

        if(manager == null) {
            return null;
        }

        if (type == SlimePlatform.BUNGEECORD) {
            return new ControlBungeeBuilder(
                    logs,
                    finalFile,
                    manager.getInputStream(resource)
            );
        }

        if (type == SlimePlatform.VELOCITY) {
            return new ControlVelocityBuilder(
                    logs,
                    finalFile,
                    manager.getInputStream(resource)
            );
        }

        if (type == SlimePlatform.SPONGE) {
            return new ControlSpongeBuilder(
                    logs,
                    finalFile,
                    manager.getInputStream(resource)
            );
        }

        return new ControlSpigotBuilder(
                logs,
                finalFile,
                manager.getInputStream(resource)
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

}
