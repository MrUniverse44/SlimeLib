package dev.mruniverse.slimelib.utils.plugin.data.bungeecord;

import dev.mruniverse.slimelib.utils.plugin.data.SlimePluginInformationSource;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.Set;

public class BungeePluginInformation implements SlimePluginInformationSource {

    private final Set<String> softDependencies;

    private final Set<String> dependencies;

    private final String description;

    private final String[] authors;

    private final String version;

    private final String name;

    public <T> BungeePluginInformation(T plugin) {
        Plugin p = (Plugin)plugin;

        this.softDependencies = p.getDescription().getSoftDepends();
        this.dependencies = p.getDescription().getDepends();
        this.description = p.getDescription().getDescription();
        this.authors = new String[]{ p.getDescription().getAuthor() };
        this.version = p.getDescription().getVersion();
        this.name = p.getDescription().getName();
    }


    @Override
    public String[] getAuthors() {
        return authors;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public Set<String> getDependencies() {
        return dependencies;
    }

    @Override
    public Set<String> getSoftDependencies() {
        return softDependencies;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVersion() {
        return version;
    }
}
