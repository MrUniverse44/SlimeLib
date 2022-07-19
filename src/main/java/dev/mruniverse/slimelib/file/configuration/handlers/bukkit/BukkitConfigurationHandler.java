package dev.mruniverse.slimelib.file.configuration.handlers.bukkit;

import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.platforms.StringSlimeColor;
import dev.mruniverse.slimelib.exceptions.SlimeControlLoadException;
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import dev.mruniverse.slimelib.file.configuration.TextDecoration;
import dev.mruniverse.slimelib.file.configuration.handlers.util.FileUtils;
import dev.mruniverse.slimelib.logs.SlimeLogs;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BukkitConfigurationHandler extends ConfigurationHandler {

    private FileConfiguration configuration;

    public BukkitConfigurationHandler(SlimeLogs logs, File file, InputStream resource) {
        super(logs, file, resource);
    }

    public BukkitConfigurationHandler(SlimeLogs logs, File file) {
        super(logs, file);
    }

    @Override
    public void load() {
        configuration = loadConfig(getFile());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toSpecifiedConfiguration() {
        return (T) configuration;
    }

    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        if (configuration.getList(path) == null) {
            return def;
        }
        return configuration.getList(path, def);
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getStringList(TextDecoration decoration, String path) {
        List<String> list = configuration.getStringList(path);
        switch (decoration) {
            default:
            case NONE:
                return list;
            case STRIP_COLORS:
                list.replaceAll(
                        line -> line = ChatColor.stripColor(line)
                );
                return list;
            case LEGACY:
                list.replaceAll(
                        line -> line = ChatColor.translateAlternateColorCodes('&', line)
                );
                return list;
            case ALL:
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.ALL).build()
                );
                return list;
            case SOLID:
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.SOLID).build()
                );
                return list;
            case GRADIENT:
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.GRADIENT).build()
                );
                return list;
        }
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {

        ConfigurationSection section = configuration.getConfigurationSection(path);

        List<String> content = new ArrayList<>();

        if (section != null) {

            content.addAll(section.getKeys(getKeys));

        }

        return content;
    }

    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntegerList(path);
    }

    @Override
    public String getString(String path, String def) {
        return configuration.getString(path, def);
    }

    @Override
    public String getString(TextDecoration decoration, String path) {
        return getString(decoration, path, "&7Invalid path&8: &a" + path);
    }

    @Override
    public String getString(TextDecoration decoration, String path, String def) {
        String message = configuration.getString(path, def);
        switch (decoration) {
            default:
            case NONE:
                return message;
            case STRIP_COLORS:
                return ChatColor.stripColor(message);
            case LEGACY:
                return ChatColor.translateAlternateColorCodes('&', message);
            case ALL:
                return new StringSlimeColor(message, true, SlimeColor.ColorMethod.ALL).build();
            case SOLID:
                return new StringSlimeColor(message, true, SlimeColor.ColorMethod.SOLID).build();
            case GRADIENT:
                return new StringSlimeColor(message, true, SlimeColor.ColorMethod.GRADIENT).build();
        }
    }

    private FileConfiguration loadConfig(File file) {
        if (!file.exists()) {
            FileUtils.saveConfig(file, getLogs(), getResource());
        }

        FileConfiguration cnf = null;
        try {
            cnf = YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            getLogs().warn(
                    String.format(
                            "A error occurred while loading the settings file. Error: %s",
                            new SlimeControlLoadException(e)
                    )
            );
        }

        getLogs().info(String.format("&7File &e%s &7has been loaded", file.getName()));
        return cnf;
    }

    @Override
    public String getString(String path) {
        return configuration.getString(path);
    }

    @Override
    public void reload() {
        try {
            configuration = YamlConfiguration.loadConfiguration(
                    getFile()
            );
        } catch (Exception exception) {
            getLogs().error(
                    "Can't reload file: " + getFile().getName(),
                    new SlimeControlLoadException(exception)
            );
        }
    }

    @Override
    public long getLong(String path, long def) {
        return configuration.getLong(path, def);
    }

    @Override
    public long getLong(String path) {
        return configuration.getLong(path);
    }

    @Override
    public List<Long> getLongList(String path) {
        return configuration.getLongList(path);
    }

    @Override
    public List<Boolean> getBooleanList(String path) {
        return configuration.getBooleanList(path);
    }

    @Override
    public List<Byte> getByteList(String path) {
        return configuration.getByteList(path);
    }

    @Override
    public List<Character> getCharList(String path) {
        return configuration.getCharacterList(path);
    }

    @Override
    public List<Float> getFloatList(String path) {
        return configuration.getFloatList(path);
    }

    @Override
    public Object get(String path) {
        return configuration.get(path);
    }

    @Override
    public Object get(String path, Object def) {
        return configuration.get(path, def);
    }

    @Override
    public void save() {

    }

    @Override
    public int getInt(String path, int def) {
        return configuration.getInt(path, def);
    }

    @Override
    public int getInt(String path) {
        return configuration.getInt(path);
    }

    @Override
    public boolean contains(String path) {
        return configuration.contains(path);
    }

    @Override
    public boolean getStatus(String path) {
        return configuration.getBoolean(path);
    }

    @Override
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path, def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path, value);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return configuration.getKeys(deep);
    }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new BukkitConfigurationSectionHandler(
                this,
                false,
                path
        );
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new BukkitConfigurationSectionHandler(
                this,
                true,
                path
        );
    }
}
