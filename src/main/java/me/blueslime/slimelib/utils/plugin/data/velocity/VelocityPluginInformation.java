package me.blueslime.slimelib.utils.plugin.data.velocity;

import me.blueslime.slimelib.utils.plugin.data.SlimePluginInformationSource;

import java.util.Collections;
import java.util.Set;

public class VelocityPluginInformation implements SlimePluginInformationSource {
    public <T> VelocityPluginInformation(T plugin) {

    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getVersion() {
        return "";
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
}
