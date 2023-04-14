package me.blueslime.slimelib.commands.bungee;

import me.blueslime.slimelib.SlimePlugin;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeCommand {
    private final SlimePlugin<Plugin> plugin;

    @SuppressWarnings("unchecked")
    public <T> BungeeCommand(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<Plugin>) plugin;
    }

    public SlimeCommandsBungee<Plugin> get() {
        return new SlimeCommandsBungee<>(plugin);
    }
}
