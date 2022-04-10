package dev.mruniverse.slimelib.storage.provider;

import dev.mruniverse.slimelib.control.Control;
import dev.mruniverse.slimelib.control.bungee.ControlBungeeBuilder;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import dev.mruniverse.slimelib.storage.ControlProvider;

import java.io.File;
import java.io.InputStream;

public class ControlBungeeProvider implements ControlProvider {
    /**
     * Create a controller file.
     *
     * @param logs     Slime Logs
     * @param file     File to load
     * @param resource Resource file
     * @return Controller file
     */
    @Override
    public Control create(SlimeLogs logs, File file, InputStream resource) {
        return new ControlBungeeBuilder(
                logs,
                file,
                resource
        );
    }

    /**
     * Create a controller file.
     *
     * @param logs Slime Logs
     * @param file File to load
     * @return Controller file
     */
    @Override
    public Control create(SlimeLogs logs, File file) {
        return new ControlBungeeBuilder(
                logs,
                file
        );
    }
}
