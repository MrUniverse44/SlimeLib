package dev.mruniverse.slimelib.input;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.input.bungeecord.BungeeInputManager;
import dev.mruniverse.slimelib.input.spigot.SpigotInputManager;
import dev.mruniverse.slimelib.input.sponge.SpongeInputManager;
import dev.mruniverse.slimelib.input.velocity.VelocityInputManager;

import java.io.InputStream;

public interface InputManager {

    /**
     * Get a InputStream from a resource file.
     * @param resource File Path with File Name
     * @return InputStream
     */
    InputStream getInputStream(String resource);

    static <T> InputManager createInputManager(SlimePlatform platform, T plugin) {
        switch (platform) {
            case SPONGE:
                return new SpongeInputManager(plugin);
            case VELOCITY:
                return new VelocityInputManager(plugin);
            case BUNGEECORD:
                return new BungeeInputManager(plugin);
            default:
            case SPIGOT:
                return new SpigotInputManager(plugin);
        }
    }
}
