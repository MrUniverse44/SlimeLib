package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.GuardianStorageAPI;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.inputs.SpigotInputManager;
import dev.mruniverse.guardianstorageapi.interfaces.FileStorage;
import dev.mruniverse.guardianstorageapi.logs.SpigotLogs;
import dev.mruniverse.guardianstorageapi.utils.GuardianHelper;
import org.bukkit.plugin.java.JavaPlugin;

@SuppressWarnings("unused")
public class TestsSpigot extends JavaPlugin {

    private FileStorage storage;

    @Override
    public void onEnable() {
        storage = new GuardianStorageAPI(ControlType.SPIGOT)
                .setLogs(new SpigotLogs("TestPlugin", "TestPlugin.package."))
                .setInputManager(new SpigotInputManager(this))
                .createStorage(getDataFolder())
                .setEnums(GuardianHelper.process(ExampleEnum.class));
    }

    @SuppressWarnings("unused")
    public void usages() {
        storage.getControl(ExampleEnum.FILE1).reload();
        storage.getControl(ExampleEnum.FILE2).reload();
        new SpigotLogs("Test","dev.mruniverse.guardianstorageapi.examples.").debug(storage.getControl(ExampleEnum.FILE2).getColoredString("TestsSpigot","&cDefault"));
    }

}
