package dev.mruniverse.slimelib.file.configuration.handlers.util;

import dev.mruniverse.slimelib.exceptions.SlimeControlFileException;
import dev.mruniverse.slimelib.logs.SlimeLogs;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileUtils {
    public static void saveConfig(File file, SlimeLogs logs, InputStream resource) {
        checkFileExistence(file, logs, resource);
    }

    public static void checkFileExistence(File file, SlimeLogs logs, InputStream resource) {
        if (!file.getParentFile().exists()) {
            boolean createFile = file.getParentFile().mkdirs();
            if(createFile) logs.info("&7Folder created!!");
        }

        if (!file.exists()) {
            try (InputStream in = resource) {
                cloneResource(file, logs, in);
            } catch (Exception exception) {
                logs.error(
                        String.format("A error occurred while copying the config %s to the plugin data folder.", file.getName()),
                        new SlimeControlFileException(exception)
                );
            }
        }
    }

    public static void cloneResource(File file, SlimeLogs logs, InputStream in) throws IOException {
        if(in != null) {
            Files.copy(in, file.toPath());
        } else {
            logs.info("Resource is null");
            logs.info("Creating a empty file for " + file.getName());
            boolean created = file.createNewFile();
            if (created) {
                logs.info("File created!");
            }
        }
    }
}
