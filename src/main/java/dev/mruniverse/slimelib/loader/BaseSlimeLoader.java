package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.storage.FileStorage;


public abstract class BaseSlimeLoader<T> {
    private final SlimePlugin<T> plugin;

    private BaseSlimeListenerManager<T> listenerManager = null;

    private SlimeStorage storage = null;

    private FileStorage files = null;

    public BaseSlimeLoader(SlimePlugin<T> plugin) {
        this.plugin = plugin;
    }

    public void storage(SlimeStorage storage) {
        this.storage = storage;
    }

    public void listenerManager(BaseSlimeListenerManager<T> listenerManager) {
        this.listenerManager = listenerManager;
    }

    public void fileStorage(FileStorage files) {
        this.files = files;
    }

    public SlimePlugin<T> getPlugin() {
        return plugin;
    }

    public BaseSlimeListenerManager<T> getListenerManager() {
        return listenerManager;
    }

    public SlimeStorage getStorage() {
        return storage;
    }

    public FileStorage getFiles() {
        return files;
    }

    /**
     * This will be like the "onLoad()" and "onEnable()",
     *
     * This will
     */
    public void init() {

    }

    public void shutdown() {

    }

    public void reload() {

    }
}
