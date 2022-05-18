package dev.mruniverse.slimelib.utils.plugininfo.sponge;

import dev.mruniverse.slimelib.utils.plugininfo.SlimePluginInformationSource;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.metadata.PluginMetadata;

import java.util.Collections;
import java.util.Set;

public class SpongePluginInformation implements SlimePluginInformationSource {

    public <T> SpongePluginInformation(T plugin) {

    }


    @Override
    public String[] getAuthors() {
        return new String[0];
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Set<String> getDependencies() {
        return Collections.emptySet();
    }

    @Override
    public Set<String> getSoftDependencies() {
        return Collections.emptySet();
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getVersion() {
        return "";
    }
}
