package dev.mruniverse.slimelib.input.spigot;

import dev.mruniverse.slimelib.input.InputManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.InputStream;

public class SpigotInputManager implements InputManager {
    private final JavaPlugin plugin;

    public SpigotInputManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getResource(resource);
    }
}
