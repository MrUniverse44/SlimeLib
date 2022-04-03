package dev.mruniverse.slimelib.commands;

import dev.mruniverse.slimelib.PluginMode;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.manager.SlimeCommandManager;
import dev.mruniverse.slimelib.commands.manager.DefaultSlimeCommandManager;

@SuppressWarnings("unused")
public class SlimeCommands<T> {

    private final SlimeCommandManager manager;

    private final SlimeCommandPlatform platform;

    public SlimeCommands(SlimePlugin<T> plugin, PluginMode mode) {
        this.manager = new DefaultSlimeCommandManager();
        this.platform = SlimeCommandPlatform.fromMode(plugin, mode);
    }

    public SlimeCommandManager getManager() {
        return manager;
    }

    public void register(SlimeCommand command) {
        platform.register(command);
    }

    public void unregister() {
        platform.unregister();
    }
}
