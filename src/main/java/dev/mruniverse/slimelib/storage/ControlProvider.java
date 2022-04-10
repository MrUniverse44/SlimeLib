package dev.mruniverse.slimelib.storage;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.control.Control;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.provider.ControlBungeeProvider;
import dev.mruniverse.slimelib.storage.provider.ControlSpigotProvider;
import dev.mruniverse.slimelib.storage.provider.DefaultControlProvider;

import java.io.File;
import java.io.InputStream;

public interface ControlProvider {
    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @param resource Resource file
     * @return Controller file
     */
    Control create(SlimeLogs logs, File file, InputStream resource);

    /**
     * Create a controller file.
     * @param logs Slime Logs
     * @param file File to load
     * @return Controller file
     */
    Control create(SlimeLogs logs, File file);

    /**
     * Create a ControlProvider depending on the platform
     * @param platform Platform to create a provider
     * @return ControlProvider
     */
    static ControlProvider create(SlimePlatform platform) {
        switch (platform) {
            case SPIGOT:
                return new ControlSpigotProvider();
            case BUNGEECORD:
                return new ControlBungeeProvider();
            default:
            case VELOCITY:
            case SPONGE:
                return new DefaultControlProvider();
        }
    }
}
