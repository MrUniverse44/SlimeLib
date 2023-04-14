package me.blueslime.slimelib.commands.bukkit;

import me.blueslime.slimelib.SlimePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitCommand {
    private final SlimePlugin<JavaPlugin> plugin;

    @SuppressWarnings("unchecked")
    public <T> BukkitCommand(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<JavaPlugin>) plugin;
    }

    public BukkitSlimeCommands<JavaPlugin> get() {
        return new BukkitSlimeCommands<>(plugin);
    }
}
