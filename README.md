# SlimeLib

[![](https://jitpack.io/v/MrUniverse44/SlimeLib.svg)](https://jitpack.io/#MrUniverse44/SlimeLib)

SlimeLib is a simple Library to simplify developers
creation of multi-platform plugins.

**Features**:
* Gradient and Solid Syntax's and more with **SlimeColors**
* BungeeCord, Spigot and Velocity Support
* Command System
* Some Utils for a fast plugin developing
* 1.8.X - 1.19.X
* Yaml Files
* Updater for SpigotMC & GitHub
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
            <version>1.0.8</version>
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

* ConfigurationProvider - Usage Example:

```Java
import dev.mruniverse.slimelib.file.configuration.ConfigurationProvider;
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;

public class a {
    public ConfigurationHandler load(SlimeLogs logs, File file) {
        ConfigurationProvider provider = ConfigurationProvider.newInstance();
        return provider.create(
                logs,
                file
        );
    }

    public ConfigurationHandler load(SlimeLogs logs, File file, InputStream resource) {
        ConfigurationProvider provider = ConfigurationProvider.newInstance();
        return provider.create(
                logs,
                file,
                resource
        );
    }
}
```

* toSpecifiedConfiguration Method - Examples:
BungeeCord:

```Java
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import net.md_5.bungee.config.Configuration;

public class a {
    public void toSpecifiedBungee(SlimeLogs logs, File file) {
        ConfigurationProvider provider = ConfigurationProvider.newInstance();
        
        ConfigurationHandler handler = provider.create(
                logs,
                file
        );
        
        Configuration configuration = handler.toSpecifiedConfiguration();
    }
}
```
Spigot:

```Java
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

public class a {
    public void toSpecifiedBukkit(SlimeLogs logs, File file) {
        ConfigurationProvider provider = ConfigurationProvider.newInstance();

        ConfigurationHandler handler = provider.create(
                logs,
                file
        );

        //NOTE: If you are calling this method, normally it will be FileConfiguration, but
        //NOTE: If you are calling this method in a getSection it will be a ConfigurationSection
        FileConfiguration configuration = handler.toSpecifiedConfiguration();

        ConfigurationSection section = handler.toSpecifiedConfiguration();
    }
}
```

Other:

```Java
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import dev.mruniverse.slimelib.utils.PluginConfiguration;

public class a {
    public void toSpecifiedBukkit(SlimeLogs logs, File file) {
        ConfigurationProvider provider = ConfigurationProvider.newInstance();

        ConfigurationHandler handler = provider.create(
                logs,
                file
        );

        PluginConfiguration configuration = handler.toSpecifiedConfiguration();
    }
}
```

* Control & Configuration Handler File System - Enum Example:

```Java
package dev.mruniverse.slimelib.examples;

import dev.mruniverse.slimelib.SlimeFiles;

public enum SlimeFile implements SlimeFiles {
    FILE1("Tests.yml"),
    FILE2("Tests2.yml","Tests","Tests.yml"),
    FILE3("Tests3.yml","Tests3.yml",true);

    private final boolean differentFolder;

    private final String file;

    private final String folder;

    private final String resource;

    SlimeFile(String file) {
        this.file = file;
        this.resource = file;
        this.differentFolder = false;
        this.folder = "";
    }

    SlimeFile(String file,String folder,String resource) {
        this.file = file;
        this.resource = resource;
        this.differentFolder = true;
        this.folder = folder;
    }

    SlimeFile(String file,String folderOrResource,boolean isResource) {
        this.file = file;
        if(isResource) {
            this.resource = folderOrResource;
            this.folder = "";
            this.differentFolder = false;
        } else {
            this.resource = file;
            this.folder = folderOrResource;
            this.differentFolder = true;
        }
    }

    @Override
    public String getFileName() {
        return this.file;
    }

    @Override
    public String getFolderName() {
        return this.folder;
    }

    @Override
    public String getResourceFileName(SlimePlatform platform) {
        return this.resource;
    }

    @Override
    public boolean isInDifferentFolder() {
        return this.differentFolder;
    }
}
```