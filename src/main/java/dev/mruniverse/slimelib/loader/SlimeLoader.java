package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.storage.FileStorage;

public abstract class SlimeLoader {
    private final SlimePlugin plugin;

    private SlimeStorage storage = null;

    private FileStorage files = null;

    public SlimeLoader(SlimePlugin plugin) {
        this.plugin = plugin;
    }

    public void storage(SlimeStorage storage) {
        this.storage = storage;
    }

    public void fileStorage(FileStorage files) {
        this.files = files;
    }

    public SlimePlugin getPlugin() {
        return plugin;
    }

    public SlimeStorage getStorage() {
        return storage;
    }
    public FileStorage getFiles() {
        return files;
    }

    public void init() {

    }

    public void shutdown() {

    }

    public void reload() {

    }
}
