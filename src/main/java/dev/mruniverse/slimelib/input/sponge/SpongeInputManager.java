package dev.mruniverse.slimelib.input.sponge;

import dev.mruniverse.slimelib.input.InputManager;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.io.InputStream;

public class SpongeInputManager implements InputManager {
    private final Plugin plugin;

    public <T> SpongeInputManager(T plugin) {
        this.plugin = (Plugin) plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getClass().getResourceAsStream(resource);
    }
}