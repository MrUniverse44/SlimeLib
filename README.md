# GuardianStorageAPI

[![](https://jitpack.io/v/MrUniverse44/GuardianStorageAPI.svg)](https://jitpack.io/#MrUniverse44/GuardianLIB)

GuardianStorageAPI is a simple YAML Storage System to simplify developers
creation of YAML's when you have some files at the same time.

Features:
* BungeeCord and Spigot Support
* 1.8 - 1.17
* InputResource Support
* More Options

Maven Usage (With jitpack):
```XML
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
```

Dependency:
```XML
        <dependency>
            <groupId>com.github.MrUniverse44</groupId>
            <artifactId>GuardianStorageAPI</artifactId>
            <version>TAG</version>
            <!-- TAG = latest version, for example:
            <version>1.0.0</version>
            -->
        </dependency>
```

## Re-location

```XML
                            <relocations>
                                <relocation>
                                    <pattern>dev.mruniverse.guardianstorageapi</pattern>
                                    <shadedPattern>(Your Package)</shadedPattern>
                                </relocation>
                            </relocations>
```

## Usage examples:


Creating a storage:
* Spigot:
```Java
import dev.mruniverse.guardianstorageapi.GuardianStorageAPI;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.inputs.SpigotInputManager;
import dev.mruniverse.guardianstorageapi.interfaces.FileStorage;
import dev.mruniverse.guardianstorageapi.logs.SpigotLogs;
import dev.mruniverse.guardianstorageapi.utils.GuardianHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class Tests extends JavaPlugin {

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
```

* BungeeCord:
```Java
package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.GuardianStorageAPI;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.inputs.BungeeInputManager;
import dev.mruniverse.guardianstorageapi.interfaces.FileStorage;
import dev.mruniverse.guardianstorageapi.logs.BungeeLogs;
import dev.mruniverse.guardianstorageapi.utils.GuardianHelper;
import net.md_5.bungee.api.plugin.Plugin;

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
```

* Enum:
```Java
package dev.mruniverse.guardianstorageapi.examples;

import dev.mruniverse.guardianstorageapi.interfaces.GuardianFiles;

public enum ExampleEnum implements GuardianFiles {
    FILE1 {
        @Override
        public String getFileName() {
            return "Tests.yml";
        }
        @Override
        public String getFolderName() {
            return "";
        }
        @Override
        public boolean isInDifferentFolder() {
            return false;
        }
    },
    FILE2 {
        @Override
        public String getFileName() {
            return "Tests.yml";
        }
        @Override
        public String getFolderName() {
            return "Tests";
        }
        @Override
        public boolean isInDifferentFolder() {
            return true;
        }
    };
}

```