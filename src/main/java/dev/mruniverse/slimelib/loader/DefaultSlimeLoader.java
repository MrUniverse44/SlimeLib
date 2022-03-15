package dev.mruniverse.slimelib.loader;

import dev.mruniverse.slimelib.SlimeFiles;
import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.SlimeStorage;
import dev.mruniverse.slimelib.input.InputManager;
import dev.mruniverse.slimelib.utils.SlimeHelper;

public class DefaultSlimeLoader extends SlimeLoader {

    public DefaultSlimeLoader(SlimePlugin plugin, InputManager inputManager) {
        super(plugin);

        super.storage(new SlimeStorage(
                plugin.getServerType(),
                plugin.getSlimeLogs(),
                inputManager
        ));
    }

    public <T extends Enum<T> & SlimeFiles> void setFiles(Class<T> clazz) {
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
        //TODO
    }

    @Override
    public void reload() {
        getFiles().reloadFiles();
    }


}
