package dev.mruniverse.slimelib;

import dev.mruniverse.slimelib.utils.plugin.data.SlimePluginInformationSource;
import dev.mruniverse.slimelib.utils.plugin.data.bungeecord.BungeePluginInformation;
import dev.mruniverse.slimelib.utils.plugin.data.spigot.SpigotPluginInformation;
import dev.mruniverse.slimelib.utils.plugin.data.sponge.SpongePluginInformation;
import dev.mruniverse.slimelib.utils.plugin.data.velocity.VelocityPluginInformation;

import java.util.Set;

public class SlimePluginInformation {

    private final Set<String> softDependencies;

    private final Set<String> dependencies;

    private final String description;

    private final String[] authors;

    private final String version;

    private final String name;

    public <T> SlimePluginInformation(SlimePlatform platform, T plugin) {
        SlimePluginInformationSource source;

        switch (platform) {
            case BUNGEECORD:
                source = new BungeePluginInformation(plugin);
                break;
            case VELOCITY:
                source = new VelocityPluginInformation(plugin);
                break;
            default:
            case SPIGOT:
                source = new SpigotPluginInformation(plugin);
                break;
            case SPONGE:
                source = new SpongePluginInformation(plugin);
                break;
        }

        this.authors = source.getAuthors();
        this.dependencies = source.getDependencies();
        this.description = source.getDescription();
        this.name = source.getName();
        this.softDependencies = source.getSoftDependencies();
        this.version = source.getVersion();
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public Set<String> getDependencies() {
        return dependencies;
    }

    public Set<String> getSoftDependencies() {
        return softDependencies;
    }

}
