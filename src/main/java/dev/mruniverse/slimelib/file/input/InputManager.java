package dev.mruniverse.slimelib.file.input;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.file.input.bungeecord.BungeeInputManager;
import dev.mruniverse.slimelib.file.input.spigot.SpigotInputManager;
import dev.mruniverse.slimelib.file.input.sponge.SpongeInputManager;
import dev.mruniverse.slimelib.file.input.velocity.VelocityInputManager;

import java.io.InputStream;

public interface InputManager {

    /**
     * Get a InputStream from a resource file.
     * @param resource File Path with File Name
     * @return InputStream
     */
    InputStream getInputStream(String resource);

    /**
     * Gives a InputManager depending on your platform, See a shorter method {@link InputManager#create(SlimePlatform, Object)}
     * @param platform Your current platform
     * @param plugin   The plugin instance
     * @param <T>      The platform-instance-type
     * @return InputManager
     */
    static <T> InputManager createInputManager(SlimePlatform platform, T plugin) {
        return create(platform, plugin);
    }

    /**
     * A shorter static method of {@link InputManager#createInputManager(SlimePlatform, Object)}
     * @param platform Your current platform
     * @param plugin   The plugin instance
     * @param <T>      The platform-instance-type
     * @return InputManager
     */
    static <T> InputManager create(SlimePlatform platform, T plugin) {
        switch (platform) {
            case SPONGE:
                return new SpongeInputManager();
            case VELOCITY:
                return new VelocityInputManager();
            case BUNGEECORD:
                return new BungeeInputManager(plugin);
            default:
            case SPIGOT:
                return new SpigotInputManager(plugin);
        }
    }

    static InputManager getAutomatically() {
        return new DefaultInputManager();
    }
}
