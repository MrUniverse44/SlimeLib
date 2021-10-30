package dev.mruniverse.guardianstorageapi.inputs;

import dev.mruniverse.guardianstorageapi.interfaces.InputManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.InputStream;

public class BungeeInputManager implements InputManager {
    private final Plugin plugin;

    public BungeeInputManager(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getResourceAsStream(resource);
    }

    @Override
    public boolean isBungee() {
        return true;
    }
}
