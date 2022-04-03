package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.input.InputManager;
import dev.mruniverse.slimelib.utils.SlimeHelper;

public class DefaultSlimeLoader<T> extends BaseSlimeLoader<T> {

    public DefaultSlimeLoader(SlimePlugin<T> plugin, InputManager inputManager) {
        super(plugin);

        super.storage(new SlimeStorage(
                plugin.getServerType(),
                plugin.getLogs(),
                inputManager
        ));
    }

    public <O extends Enum<O> & SlimeFiles> void setFiles(Class<O> clazz) {
        fileStorage(getStorage().createStorage(getPlugin().getDataFolder())
                .setEnums(SlimeHelper.process(clazz)));
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
