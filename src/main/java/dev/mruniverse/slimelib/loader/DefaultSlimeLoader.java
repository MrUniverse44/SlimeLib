package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.input.InputManager;

public class DefaultSlimeLoader<T> extends BaseSlimeLoader<T> {

    public DefaultSlimeLoader(SlimePlugin<T> plugin, InputManager inputManager) {
        super(plugin);

        super.storage(new SlimeStorage(
                plugin.getServerType(),
                plugin.getLogs(),
                inputManager
        ));
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
