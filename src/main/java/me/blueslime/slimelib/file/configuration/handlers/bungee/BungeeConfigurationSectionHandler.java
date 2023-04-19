package me.blueslime.slimelib.file.configuration.handlers.bungee;

import me.blueslime.slimelib.colors.SlimeColor;
import me.blueslime.slimelib.colors.platforms.StringSlimeColor;
import me.blueslime.slimelib.file.configuration.ConfigurationHandler;
import me.blueslime.slimelib.file.configuration.TextDecoration;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.config.Configuration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BungeeConfigurationSectionHandler extends ConfigurationHandler {
    private final BungeeConfigurationSectionHandler sectionHandler;
    private final BungeeConfigurationHandler configurationHandler;
    private final Configuration configuration;

    public BungeeConfigurationSectionHandler(BungeeConfigurationHandler configurationHandler, String path) {
        super(
                configurationHandler.getLogs(),
                configurationHandler.getFile(),
                configurationHandler.getResource(),
                !configurationHandler.hasLogs()
        );
        this.configurationHandler = configurationHandler;
        this.sectionHandler = null;

        Configuration main = configurationHandler.toSpecifiedConfiguration();

        this.configuration = main.getSection(path);
    }

    public BungeeConfigurationSectionHandler(BungeeConfigurationSectionHandler configurationHandler, String path) {
        super(
                configurationHandler.getLogs(),
                configurationHandler.getFile(),
                configurationHandler.getResource(),
                !configurationHandler.hasLogs()
        );
        this.configurationHandler = null;
        this.sectionHandler = configurationHandler;

        Configuration main = configurationHandler.toSpecifiedConfiguration();

        this.configuration = main.getSection(path);
    }

    @Override
    public void save() {
        if (configurationHandler != null) {
            this.configurationHandler.save();
            return;
        }
        this.sectionHandler.save();
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
        if (configurationHandler != null) {
            this.configurationHandler.reload();
            return;
        }
        this.sectionHandler.reload();
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
        List<String> rx = new ArrayList<>();
        Configuration section = configuration.getSection(path);
        if(section == null) return rx;
        rx.addAll(section.getKeys());
        return rx;
    }

    @Override
    public ConfigurationHandler getSection(String path) {
        return new BungeeConfigurationSectionHandler(
                this,
                path
        );
    }

    @Override
    public ConfigurationHandler createSection(String path) {
        return new BungeeConfigurationSectionHandler(
                this,
                path
        );
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
