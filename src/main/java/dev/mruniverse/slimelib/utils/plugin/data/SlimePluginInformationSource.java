package dev.mruniverse.slimelib.utils.plugin.data;

import java.util.Set;

public interface SlimePluginInformationSource {

    String getName();

    String getVersion();

    String[] getAuthors();

    String getDescription();

    Set<String> getDependencies();

    Set<String> getSoftDependencies();
}
