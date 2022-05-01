package dev.mruniverse.slimelib.input.velocity;

import com.velocitypowered.api.plugin.Plugin;
import dev.mruniverse.slimelib.input.InputManager;

import java.io.InputStream;

public class VelocityInputManager implements InputManager {
    private final Plugin plugin;

    public <T> VelocityInputManager(T plugin) {
        this.plugin = (Plugin) plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getClass().getResourceAsStream(resource);
    }
}
