package me.blueslime.slimelib.file.configuration.provider;

import me.blueslime.slimelib.file.configuration.ConfigurationProvider;

public enum Provider {
    BUNGEE_CORD,
    BUKKIT,
    DEFAULT;

    public ConfigurationProvider getNewInstance() {
        return switch (this) {
            case BUKKIT -> new BukkitConfigurationProvider();
            case BUNGEE_CORD -> new BungeeConfigurationProvider();
            default -> new DefaultConfigurationProvider();
        };
    }
}
