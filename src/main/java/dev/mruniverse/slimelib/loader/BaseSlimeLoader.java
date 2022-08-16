package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.commands.SlimeCommands;
import dev.mruniverse.slimelib.events.EventManager;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.file.storage.FileStorage;
import dev.mruniverse.slimelib.utils.SlimeHelper;

@SuppressWarnings("unused")
public abstract class BaseSlimeLoader<T> {
    private final SlimePlugin<T> plugin;

    private final SlimeCommands<T> commands;

    private final EventManager<?> events;

    private SlimeStorage storage = null;

    private FileStorage files = null;

    public BaseSlimeLoader(SlimePlugin<T> plugin) {
        this.plugin = plugin;
        commands = new SlimeCommands<>(
                plugin,
                plugin.getServerType()
        );
        events = new EventManager<>(
                EventExecutor.fromType(
                        plugin.getServerType(),
                        plugin.getPlugin()
                )
        );
    }

    public <O extends Enum<O> & SlimeFiles> void setFiles(Class<O> clazz) {
        fileStorage(getStorage().createStorage(getPlugin().getDataFolder())
                .setEnums(SlimeHelper.process(clazz)));
    }

    public void storage(SlimeStorage storage) {
        this.storage = storage;
    }

    public void fileStorage(FileStorage files) {
        this.files = files;
    }

    public SlimeCommands<T> getCommands() {
        return commands;
    }

    public EventManager<?> getEvents() {
        return events;
    }

    public SlimePlugin<T> getPlugin() {
        return plugin;
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
