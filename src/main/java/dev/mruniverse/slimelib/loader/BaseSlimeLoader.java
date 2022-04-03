package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.commands.SlimeCommands;
import dev.mruniverse.slimelib.storage.FileStorage;

@SuppressWarnings("unused")
public abstract class BaseSlimeLoader<T> {
    private final SlimePlugin<T> plugin;

    private final SlimeCommands<T> commands;

    private BaseSlimeListenerManager<T> listenerManager = null;

    private SlimeStorage storage = null;

    private FileStorage files = null;

    public BaseSlimeLoader(SlimePlugin<T> plugin) {
        this.plugin = plugin;
        commands = new SlimeCommands<>(
                plugin,
                plugin.getServerType()
        );
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

    public SlimeCommands<T> getCommands() {
        return commands;
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
     * This will init the loader.
     */
    public void init() {

    }

    public void shutdown() {

    }

    public void reload() {

    }
}
