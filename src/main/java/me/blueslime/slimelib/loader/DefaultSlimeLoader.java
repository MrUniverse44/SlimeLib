package me.blueslime.slimelib.loader;

import me.blueslime.slimelib.SlimePlugin;

public class DefaultSlimeLoader<T> extends BaseSlimeLoader<T> {

    public DefaultSlimeLoader(SlimePlugin<T> plugin) {
        super(plugin);
    }

    @Override
    public void init() {
        if (getFiles() != null) {
            getFiles().init();
        }
    }

    @Override
    public void shutdown() {
        getCommands().unregister();
    }

    @Override
    public void reload() {
        getFiles().reloadFiles();
    }


}
