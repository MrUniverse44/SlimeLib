package dev.mruniverse.slimelib.file.input.bungeecord;

import dev.mruniverse.slimelib.file.input.InputManager;
import net.md_5.bungee.api.plugin.Plugin;

import java.io.InputStream;

public class BungeeInputManager implements InputManager {
    private final Plugin plugin;

    public <T> BungeeInputManager(T plugin) {
        this.plugin = (Plugin) plugin;
    }

    @Override
    public InputStream getInputStream(String resource) {
        return plugin.getResourceAsStream(resource);
    }
}
