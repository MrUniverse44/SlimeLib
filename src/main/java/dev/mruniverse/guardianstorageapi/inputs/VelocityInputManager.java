package dev.mruniverse.guardianstorageapi.inputs;

import com.velocitypowered.api.plugin.Plugin;
import dev.mruniverse.guardianstorageapi.interfaces.InputManager;

import java.io.InputStream;

public class VelocityInputManager implements InputManager {
    private final Plugin plugin;

    public VelocityInputManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getClass().getResourceAsStream(resource);
    }

    @Override
    public boolean isBungee() {
        return false;
    }
}
