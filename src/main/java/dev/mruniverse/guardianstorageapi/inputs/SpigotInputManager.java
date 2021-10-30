package dev.mruniverse.guardianstorageapi.inputs;

import dev.mruniverse.guardianstorageapi.interfaces.InputManager;
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

    @Override
    public boolean isBungee() {
        return false;
    }
}
