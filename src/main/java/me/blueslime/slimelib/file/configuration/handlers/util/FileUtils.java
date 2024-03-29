package me.blueslime.slimelib.file.configuration.handlers.util;

import me.blueslime.slimelib.exceptions.ConfigurationException;
import me.blueslime.slimelib.logs.SlimeLogs;

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
            if (createFile && logs != null) {
                logs.info("&7Folder created!!");
            }
        }

        if (!file.exists()) {
            try (InputStream in = resource) {
                cloneResource(file, logs, in);
            } catch (Exception exception) {
                if (logs != null) {
                    logs.error(
                            String.format("A error occurred while copying the config %s to the plugin data folder.", file.getName()),
                            new ConfigurationException(exception)
                    );
                }
            }
        }
    }

    public static void cloneResource(File file, SlimeLogs logs, InputStream in) throws IOException {
        if (in != null) {
            Files.copy(in, file.toPath());
        } else {
            if (logs != null) {
                logs.info("Resource is null");
                logs.info("Creating a empty file for " + file.getName());
            }
            boolean created = file.createNewFile();
            if (created && logs != null) {
                logs.info("File created!");
            }
        }
    }
}
