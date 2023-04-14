package me.blueslime.slimelib.utils.configuration;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class YamlConfiguration {

    private static final ThreadLocal<Yaml> yaml = ThreadLocal.withInitial(() -> {
        Representer representer = new Representer() {
            {
                representers.put(PluginConfiguration.class, data -> represent(((PluginConfiguration) data).self));
            }
        };

        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);

        return new Yaml(new Constructor(), representer, options);
    });

    public static void save(PluginConfiguration config, File file) throws IOException {
        try (Writer writer = new OutputStreamWriter(
                Files.newOutputStream(file.toPath()),
                Charsets.UTF_8)
        ) {
            save(config, writer);
        }
    }

    public static void save(PluginConfiguration config, Writer writer) {
        yaml.get().dump(config.self, writer);
    }

    public static PluginConfiguration load(File file) throws IOException {
        return load(file, null);
    }

    public static PluginConfiguration load(File file, PluginConfiguration defaults) throws IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            return load(is, defaults);
        }
    }

    public static PluginConfiguration load(Reader reader)
    {
        return load(reader, null);
    }

    @SuppressWarnings("unchecked")
    public static PluginConfiguration load(Reader reader, PluginConfiguration defaults) {
        Map<String, Object> map = yaml.get().loadAs(reader, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new PluginConfiguration(map, defaults);
    }

    public static PluginConfiguration load(InputStream is)
    {
        return load(is, null);
    }

    @SuppressWarnings("unchecked")
    public static PluginConfiguration load(InputStream is, PluginConfiguration defaults) {
        Map<String, Object> map = yaml.get().loadAs(is, LinkedHashMap.class);

        if (map == null) {
            map = new LinkedHashMap<>();
        }

        return new PluginConfiguration(map, defaults);
    }

    public static PluginConfiguration load(String string)
    {
        return load(string, null);
    }

    @SuppressWarnings("unchecked")
    public static PluginConfiguration load(String string, PluginConfiguration defaults) {

        Map<String, Object> map = yaml.get().loadAs(string, LinkedHashMap.class);

        if (map == null) {
            map = new LinkedHashMap<>();
        }

        return new PluginConfiguration(map, defaults);
    }
}
