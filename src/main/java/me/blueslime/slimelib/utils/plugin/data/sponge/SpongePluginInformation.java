package me.blueslime.slimelib.utils.plugin.data.sponge;

import me.blueslime.slimelib.utils.plugin.data.SlimePluginInformationSource;

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
