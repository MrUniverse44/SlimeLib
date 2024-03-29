package me.blueslime.slimelib.exceptions;

public class SlimePlatformNotFoundException extends Exception {

    public SlimePlatformNotFoundException() {
        super("The current platform doesn't exist, the plugin only support: SPONGE, VELOCITY, BUNGEE CORD, BUKKIT. The plugin will try to load this plugin with BUNGEE CORD platform by default");
    }

    public SlimePlatformNotFoundException(String message) {
        super(message);
    }

}
