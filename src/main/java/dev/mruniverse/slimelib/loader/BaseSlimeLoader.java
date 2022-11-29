package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.commands.SlimeCommands;
import dev.mruniverse.slimelib.events.EventManager;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.file.storage.DefaultFileStorage;
import dev.mruniverse.slimelib.file.storage.FileStorage;
import dev.mruniverse.slimelib.utils.SlimeHelper;

@SuppressWarnings("unused")
public abstract class BaseSlimeLoader<T> {
    private final SlimePlugin<T> plugin;

    private final SlimeCommands<T> commands;

    private final EventManager<?> events;

    private FileStorage files = null;

    public BaseSlimeLoader(SlimePlugin<T> plugin) {
        this.plugin = plugin;
        this.commands = new SlimeCommands<>(
                plugin,
                plugin.getServerType()
        );
        this.events = new EventManager<>(
                EventExecutor.fromType(
                        plugin.getServerType(),
                        plugin.getPlugin()
                )
        );
    }

    public <O extends Enum<O> & SlimeFiles> void setFiles(Class<O> clazz) {
        fileStorage(
                new DefaultFileStorage(
                        getPlugin()
                ).setEnums(
                        SlimeHelper.process(
                                clazz
                        )
                )
        );
    }

    public EventManager<?> getEvents() {
        return events;
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

    public FileStorage getFiles() {
        return files;
    }

    /**
     * This will be like the "onLoad()" and "onEnable()",
     *
     * This will init the loader.
     */
    public abstract void init();

    public abstract void shutdown();

    public abstract void reload();
}
