package me.blueslime.slimelib.file.configuration.handlers.bungee;

import me.blueslime.slimelib.colors.SlimeColor;
import me.blueslime.slimelib.colors.platforms.StringSlimeColor;
import me.blueslime.slimelib.exceptions.ConfigurationException;
import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.file.configuration.TextDecoration;
import me.blueslime.slimelib.file.configuration.handlers.util.FileUtils;
import me.blueslime.slimelib.logs.SlimeLogs;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BungeeConfigurationHandler extends ConfigurationHandler {

    private Configuration configuration;

    public BungeeConfigurationHandler(SlimeLogs logs, File file, InputStream resource, boolean withoutLogs) {
        super(logs, file, resource, withoutLogs);
    }

    public BungeeConfigurationHandler(SlimeLogs logs, File file, boolean withoutLogs) {
        super(logs, file, withoutLogs);
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
    public void save() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, getFile());
        }catch (Exception exception) {
            if (hasLogs()) {
                getLogs().error(
                        "Can't save file: " + getFile().getName(),
                        new ConfigurationException(exception)
                );
            }
        }
    }

    @Override
    public Object get(String path) { return configuration.get(path); }

    @Override
    public Object get(String path,Object def) { return configuration.get(path,def); }

    @Override
    public long getLong(String path,long def) { return configuration.getLong(path,def); }

    @Override
    public long getLong(String path) { return configuration.getLong(path); }

    @Override
    public List<Long> getLongList(String path) { return configuration.getLongList(path); }

    @Override
    public List<Boolean> getBooleanList(String path) { return configuration.getBooleanList(path); }

    @Override
    public List<Byte> getByteList(String path) { return configuration.getByteList(path); }

    @Override
    public List<Character> getCharList(String path) { return configuration.getCharList(path); }
    @Override
    public List<Float> getFloatList(String path) { return configuration.getFloatList(path); }

    @Override
    public void reload() {
        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(getFile());
        } catch (Exception exception) {
            if (hasLogs()) {
                getLogs().error(
                        "Can't reload file: " + getFile().getName(),
                        new ConfigurationException(exception)
                );
            }
        }
    }

    private Configuration loadConfig(File file) {
        if (!file.exists()) {
            FileUtils.saveConfig(file, getLogs(), getResource());
        }

        Configuration cnf = null;
        try {
            cnf = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (Exception e) {
            if (hasLogs()) {
                getLogs().warn(
                        String.format(
                                "A error occurred while loading the settings file. Error: %s",
                                new ConfigurationException(e)
                        )
                );
            }
        }

        if (hasLogs()) {
            getLogs().info(String.format("&7File &e%s &7has been loaded", file.getName()));
        }
        return cnf;
    }

    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return configuration.getList(path,def);
    }

    @Override
    public String getString(TextDecoration decoration, String path) {
        return getString(decoration, path, "&7Invalid path&8: &a" + path);
    }

    @Override
    public String getString(TextDecoration decoration, String path, String def) {
        String message = configuration.getString(path, def);
        return switch (decoration) {
            case STRIP_COLORS -> ChatColor.stripColor(message);
            case LEGACY -> ChatColor.translateAlternateColorCodes('&', message);
            case ALL -> new StringSlimeColor(message, true, SlimeColor.ColorMethod.ALL).build();
            case SOLID -> new StringSlimeColor(message, true, SlimeColor.ColorMethod.SOLID).build();
            case GRADIENT -> new StringSlimeColor(message, true, SlimeColor.ColorMethod.GRADIENT).build();
            default -> message;
        };
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getStringList(TextDecoration decoration, String path) {
        List<String> list = configuration.getStringList(path);
        return switch (decoration) {
            case STRIP_COLORS -> {
                list.replaceAll(
                        line -> line = ChatColor.stripColor(line)
                );
                yield list;
            }
            case LEGACY -> {
                list.replaceAll(
                        line -> line = ChatColor.translateAlternateColorCodes('&', line)
                );
                yield list;
            }
            case ALL -> {
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.ALL).build()
                );
                yield list;
            }
            case SOLID -> {
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.SOLID).build()
                );
                yield list;
            }
            case GRADIENT -> {
                list.replaceAll(
                        line -> line = new StringSlimeColor(line, true, SlimeColor.ColorMethod.GRADIENT).build()
                );
                yield list;
            }
            default -> list;
        };
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();
        Configuration section = configuration.getSection(path);
        if(section == null) return rx;
        rx.addAll(section.getKeys());
        return rx;
    }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new BungeeConfigurationSectionHandler(this, path);
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new BungeeConfigurationSectionHandler(this, path);
    }

    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntList(path);
    }

    @Override
    public int getInt(String path, int def) {
        return configuration.getInt(path,def);
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
        configuration.set(path,value);
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return new HashSet<>(configuration.getKeys());
    }
}
