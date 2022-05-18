package dev.mruniverse.slimelib.utils.plugininfo.spigot;

import dev.mruniverse.slimelib.utils.plugininfo.SlimePluginInformationSource;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;

public class SpigotPluginInformation implements SlimePluginInformationSource {

    private final Set<String> softDependencies;

    private final Set<String> dependencies;

    private final String description;

    private final String[] authors;

    private final String version;

    private final String name;

    public <T> SpigotPluginInformation(T plugin) {
        JavaPlugin p = (JavaPlugin)plugin;

        this.softDependencies = new HashSet<>(p.getDescription().getSoftDepend());
        this.dependencies = new HashSet<>(p.getDescription().getDepend());
        this.description = p.getDescription().getDescription();
        this.authors = new String[]{ String.valueOf(p.getDescription().getAuthors()) };
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
