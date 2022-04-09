# SlimeLib

[![](https://jitpack.io/v/MrUniverse44/SlimeLib.svg)](https://jitpack.io/#MrUniverse44/SlimeLib)

SlimeLib is a simple Library to simplify developers
creation of multi-platform plugins.

**Features**:
* BungeeCord, Spigot and Velocity Support
* 1.8.X - 1.18.X
* Yaml Files
* Logs
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
            <artifactId>SlimeLib</artifactId>
            <version>TAG</version>
            <!-- TAG = latest version, for example:
            <version>1.0.4</version>
            -->
        </dependency>
```

## Re-location

```XML
                            <relocations>
                                <relocation>
                                    <pattern>dev.mruniverse.slimelib</pattern>
                                    <shadedPattern>(Your Package)</shadedPattern>
                                </relocation>
                            </relocations>
```

## Usage examples:


Creating a storage:
* Spigot:
```Java
public class Storage {

    private FileStorage storage;

    @Override
    public void load() {
        //TODO: storage
    }
}
```

* BungeeCord:
```Java
public class Storage {

    private FileStorage storage;

    @Override
    public void load() {
        //TODO: storage
    }
}
```

* Velocity:
```Java
public class Storage {

    private FileStorage storage;

    @Override
    public void load() {
        //TODO: storage
    }
}
```

* Enum:

```Java
package dev.mruniverse.slimelib.examples;

import dev.mruniverse.slimelib.SlimeFiles;

public enum SlimeFile implements SlimeFiles {
    SETTINGS("settings.yml"),
    MESSAGES("messages.yml");

    private final String file;

    SlimeFile(String file) {
        this.file = file;
    }

    @Override
    public String getFileName() {
        return file;
    }

    @Override
    public String getResourceFileName() {
        return file;
    }

    @Override
    public String getFolderName() {
        return "";
    }

    @Override
    public boolean isInDifferentFolder() {
        return false;
    }
}

```