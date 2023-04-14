package me.blueslime.slimelib.loader;

import me.blueslime.slimelib.SlimeFiles;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.commands.SlimeCommands;
import me.blueslime.slimelib.file.storage.DefaultFileStorage;
import me.blueslime.slimelib.file.storage.FileStorage;

@SuppressWarnings("unused")
public abstract class BaseSlimeLoader<T> {
    private final SlimePlugin<T> plugin;

    private final SlimeCommands<T> commands;

    private FileStorage files = null;

    public BaseSlimeLoader(SlimePlugin<T> plugin) {
        this.plugin = plugin;
        this.commands = new SlimeCommands<>(
                plugin,
                plugin.getServerType()
        );
    }

    public <O extends Enum<O> & SlimeFiles> void setFiles(Class<O> clazz) {
        fileStorage(
                new DefaultFileStorage(
                        getPlugin()
                ).setEnums(
                        process(
                                clazz
                        )
                )
        );
    }

    private static <T extends Enum<T> & SlimeFiles> SlimeFiles[] process(Class<T> paramClass) {
        return paramClass.getEnumConstants();
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
