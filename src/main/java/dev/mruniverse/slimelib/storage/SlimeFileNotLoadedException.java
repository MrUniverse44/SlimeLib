package dev.mruniverse.slimelib.storage;

import dev.mruniverse.slimelib.SlimeFiles;

public class SlimeFileNotLoadedException extends Exception {

    public SlimeFileNotLoadedException(SlimeFiles file) {
        super("The requested file: " + file.getFileName() + ", was created but is not loaded on this platform, to load it modify the loadOnPlatform(SlimePlatform) result, or init the file initialization.");
    }
}
