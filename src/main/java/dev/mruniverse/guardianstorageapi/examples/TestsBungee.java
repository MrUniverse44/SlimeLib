package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.GuardianStorageAPI;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.inputs.BungeeInputManager;
import dev.mruniverse.guardianstorageapi.interfaces.FileStorage;
import dev.mruniverse.guardianstorageapi.logs.BungeeLogs;
import dev.mruniverse.guardianstorageapi.utils.GuardianHelper;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("unused")
public class TestsBungee extends Plugin {

    private FileStorage storage;

    @Override
    public void onEnable() {
        storage = new GuardianStorageAPI(ControlType.BUNGEECORD)
                .setLogs(new BungeeLogs(this,"TestPlugin", "TestPlugin.package."))
                .setInputManager(new BungeeInputManager(this))
                .createStorage(getDataFolder())
                .setEnums(GuardianHelper.process(ExampleEnum.class));
    }

    @SuppressWarnings("unused")
    public void usages() {
        storage.getControl(ExampleEnum.FILE1).reload();
        storage.getControl(ExampleEnum.FILE2).reload();
        new BungeeLogs(this,"TestPlugin","dev.mruniverse.guardianstorageapi.examples.")
                .debug(storage.getControl(ExampleEnum.FILE2).getColoredString("TestsSpigot","&cDefault"));
    }

}
