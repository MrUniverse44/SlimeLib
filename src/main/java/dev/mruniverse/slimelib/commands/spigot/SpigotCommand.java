package dev.mruniverse.slimelib.commands.spigot;

import dev.mruniverse.slimelib.SlimePlugin;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotCommand {
    private final SlimePlugin<JavaPlugin> plugin;

    @SuppressWarnings("unchecked")
    public <T> SpigotCommand(SlimePlugin<T> plugin) {
        this.plugin = (SlimePlugin<JavaPlugin>) plugin;
    }

    public SlimeCommandsSpigot<JavaPlugin> get() {
        return new SlimeCommandsSpigot<>(plugin);
    }
}
