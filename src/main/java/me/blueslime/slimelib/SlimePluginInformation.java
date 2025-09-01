package me.blueslime.slimelib;

import lombok.Getter;
import me.blueslime.slimelib.utils.plugin.data.SlimePluginInformationSource;
import me.blueslime.slimelib.utils.plugin.data.bungeecord.BungeePluginInformation;
import me.blueslime.slimelib.utils.plugin.data.spigot.BukkitPluginInformation;
import me.blueslime.slimelib.utils.plugin.data.sponge.SpongePluginInformation;
import me.blueslime.slimelib.utils.plugin.data.velocity.VelocityPluginInformation;

import java.util.Set;

@Getter
public class SlimePluginInformation {

    private final Set<String> softDependencies;

    private final Set<String> dependencies;

    private final String description;

    private final String[] authors;

    private final String version;

    private final String name;

    public <T> SlimePluginInformation(SlimePlatform platform, T plugin) {
        SlimePluginInformationSource source = switch (platform) {
            case VELOCITY -> new VelocityPluginInformation(plugin);
            case SPONGE -> new SpongePluginInformation(plugin);
            case SPIGOT, PAPER, BUKKIT -> new BukkitPluginInformation(plugin);
            default -> new BungeePluginInformation(plugin);
        };

        this.authors = source.getAuthors();
        this.dependencies = source.getDependencies();
        this.description = source.getDescription();
        this.name = source.getName();
        this.softDependencies = source.getSoftDependencies();
        this.version = source.getVersion();
    }

}
