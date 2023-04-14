package me.blueslime.slimelib.file.configuration.handlers.other;

import me.blueslime.slimelib.colors.SlimeColor;
import me.blueslime.slimelib.colors.platforms.velocity.DefaultSlimeColor;
import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.file.configuration.TextDecoration;
import me.blueslime.slimelib.file.configuration.handlers.util.FileUtils;
import me.blueslime.slimelib.logs.SlimeLogs;
import me.blueslime.slimelib.utils.configuration.PluginConfiguration;
import me.blueslime.slimelib.utils.configuration.YamlConfiguration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PluginConfigurationHandler extends ConfigurationHandler {
    private PluginConfiguration configuration;


    public PluginConfigurationHandler(SlimeLogs logs, File file, InputStream resource) {
        super(logs, file, resource);
        load();
    }

    public PluginConfigurationHandler(SlimeLogs logs, File file) {
        super(logs, file);
        load();
    }

    @Override
    public void save() {
        try {
            YamlConfiguration.save(this.configuration, getFile());
        } catch (Exception exception) {
            getLogs().error("Can't save file: " + getFile().getName());
            getLogs().error(exception);
        }
    }

    @Override
    public void reload() {
        try {
            configuration = YamlConfiguration.load(getFile());
        } catch (Exception exception) {
            getLogs().error("Can't reload file: " + getFile().getName());
            getLogs().error(exception);
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
        return configuration.getCharList(path);
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

    public void load() {
        configuration = loadConfig(getFile());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T toSpecifiedConfiguration() {
        return (T) configuration;
    }

    public void saveConfig(File file) {
        FileUtils.saveConfig(file, getLogs(), getResource());
    }

    private PluginConfiguration loadConfig(File file) {
        if (!file.exists()) {
            saveConfig(file);
        }

        PluginConfiguration cnf = null;

        try {
            cnf = YamlConfiguration.load(file);
        } catch (Exception exception) {
            getLogs().error("Can't load: " + file.getName() + ".!");
            getLogs().error(exception);
        }

        getLogs().info(String.format("&7File &e%s &7has been loaded", file.getName()));
        return cnf;
    }

    @Override
    public List<?> getList(String path) {
        return configuration.getList(path);
    }

    @Override
    public List<?> getList(String path, List<?> def) {
        return configuration.getList(path, def);
    }

    @Override
    public List<String> getStringList(String path) {
        return configuration.getStringList(path);
    }

    @Override
    public List<String> getStringList(TextDecoration decoration, String path) {
        return null;
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();

        PluginConfiguration section = configuration.getSection(path);

        if (section == null) {
            return rx;
        }

        rx.addAll(section.getKeys());

        return rx;
    }

    @Override
    public List<Integer> getIntList(String path) {
        return configuration.getIntList(path);
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
        return (Set<String>) configuration.getKeys();
    }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new PluginConfigurationSectionHandler(
                this,
                configuration.getSection(
                        path
                )
        );
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new PluginConfigurationSectionHandler(
                this,
                configuration.getSection(
                        path
                )
        );
    }

    @Override
    public String getString(String path, String def) {
        return configuration.getString(path, def);
    }

    @Override
    public String getString(TextDecoration decoration, String path) {
        return getString(decoration, path, "&cInvalid Path: &7" + path);
    }

    @Override
    public String getString(TextDecoration decoration, String path, String def) {
        switch(decoration) {
            case NONE:
            default:
                return getString(path, def);
            case STRIP_COLORS:
                return LegacyComponentSerializer.legacyAmpersand().deserialize(getString(path, def)).content();
            case GRADIENT:
                return new DefaultSlimeColor(
                        getString(path, def),
                        true,
                        SlimeColor.ColorMethod.GRADIENT
                ).toString();
            case SOLID:
                return new DefaultSlimeColor(
                        getString(path, def),
                        true,
                        SlimeColor.ColorMethod.SOLID
                ).toString();
            case ALL:
                return new DefaultSlimeColor(
                        getString(path, def),
                        true,
                        SlimeColor.ColorMethod.ALL
                ).toString();
            case LEGACY:
                return new DefaultSlimeColor(
                        getString(path, def),
                        false
                ).toString();
        }
    }

    @Override
    public String getString(String path) {
        return getString(path, "&cInvalid Path: &e" + path);
    }
}
