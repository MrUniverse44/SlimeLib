package me.blueslime.slimelib.file.configuration.provider;

import me.blueslime.slimelib.file.configuration.ConfigurationProvider;

public enum Provider {
    BUNGEE_CORD,
    BUKKIT,
    DEFAULT;

    public ConfigurationProvider getNewInstance() {
        switch (this) {
            case BUKKIT:
                return new BukkitConfigurationProvider();
            default:
            case DEFAULT:
                return new DefaultConfigurationProvider();
            case BUNGEE_CORD:
                return new BungeeConfigurationProvider();
        }
    }
}
