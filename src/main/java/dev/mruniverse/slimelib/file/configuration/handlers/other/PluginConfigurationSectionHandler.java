package dev.mruniverse.slimelib.file.configuration.handlers.other;

import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.platforms.velocity.DefaultSlimeColor;
import dev.mruniverse.slimelib.file.configuration.ConfigurationHandler;
import dev.mruniverse.slimelib.file.configuration.TextDecoration;
import dev.mruniverse.slimelib.utils.PluginConfiguration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PluginConfigurationSectionHandler extends ConfigurationHandler {
    private final PluginConfiguration configuration;

    private PluginConfigurationSectionHandler section = null;

    private PluginConfigurationHandler main = null;

    public PluginConfigurationSectionHandler(PluginConfigurationHandler main, PluginConfiguration section) {
        super(
                main.getLogs(),
                main.getFile(),
                main.getResource()
        );
        this.configuration = section;
        this.main = main;
    }

    public PluginConfigurationSectionHandler(PluginConfigurationSectionHandler main, PluginConfiguration section) {
        super(
                main.getLogs(),
                main.getFile(),
                main.getResource()
        );
        this.configuration = section;
        this.section = main;
    }

    @Override
    public void save() {
        if (main != null) {
            main.save();
            return;
        }
        section.save();
    }

    @Override
    public void reload() {
        if (main != null) {
            main.reload();
            return;
        }
        section.reload();
    }

    @Override
    public void load() {
        // Nothing To-Do, is a section of an already loaded configuration handler.
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
        return configuration.getList(path,def);
    }

    @Override
    public List<String> getContent(String path, boolean getKeys) {
        List<String> rx = new ArrayList<>();

        PluginConfiguration section = configuration.getSection(path);

        if(section == null) {
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
    public Object get(String path) { return configuration.get(path); }

    @Override
    public Object get(String path, Object def) { return configuration.get(path,def); }

    @Override
    public long getLong(String path,long def) { return configuration.getLong(path,def); }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new PluginConfigurationSectionHandler(
                this,
                configuration.getSection(path)
        );
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new PluginConfigurationSectionHandler(
                this,
                configuration.getSection(path)
        );
    }

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
    public boolean getStatus(String path, boolean def) {
        return configuration.getBoolean(path,def);
    }

    @Override
    public void set(String path, Object value) {
        configuration.set(path,value);
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
            case STRIP_COLORS:
            case NONE:
                return message;
            case LEGACY:
                return legacyColor(message);
            case ALL:
                return new DefaultSlimeColor(message, true, SlimeColor.ColorMethod.ALL).toString();
            case SOLID:
                return new DefaultSlimeColor(message, true, SlimeColor.ColorMethod.SOLID).toString();
            case GRADIENT:
                return new DefaultSlimeColor(message, true, SlimeColor.ColorMethod.GRADIENT).toString();
        }
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
            case STRIP_COLORS:
                return list;
            case LEGACY:
                list.replaceAll(
                        line -> line = legacyColor(line)
                );
                return list;
            case ALL:
                list.replaceAll(
                        line -> line = new DefaultSlimeColor(line, true, SlimeColor.ColorMethod.ALL).toString()
                );
                return list;
            case SOLID:
                list.replaceAll(
                        line -> line = new DefaultSlimeColor(line, true, SlimeColor.ColorMethod.SOLID).toString()
                );
                return list;
            case GRADIENT:
                list.replaceAll(
                        line -> line = new DefaultSlimeColor(line, true, SlimeColor.ColorMethod.GRADIENT).toString()
                );
                return list;
        }
    }

    @Override
    public Set<String> getKeys(boolean deep) {
        return (Set<String>) configuration.getKeys();
    }

    private String legacyColor(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message).content();
    }

}

