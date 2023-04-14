package me.blueslime.slimelib.commands;

import me.blueslime.slimelib.SlimePlatform;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.commands.command.SlimeCommand;
import me.blueslime.slimelib.commands.manager.SlimeCommandManager;
import me.blueslime.slimelib.commands.manager.DefaultSlimeCommandManager;

@SuppressWarnings("unused")
public class SlimeCommands<T> {

    private final SlimeCommandManager manager;

    private final SlimeCommandPlatform platform;

    public SlimeCommands(SlimePlugin<T> plugin, SlimePlatform mode) {
        this.manager = new DefaultSlimeCommandManager();
        this.platform = SlimeCommandPlatform.fromMode(plugin, mode);
    }

    public SlimeCommands(SlimePlugin<T> plugin) {
        this.manager = new DefaultSlimeCommandManager();
        this.platform = SlimeCommandPlatform.fromMode(
                plugin,
                SlimePlatform.getAutomatically()
        );
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
