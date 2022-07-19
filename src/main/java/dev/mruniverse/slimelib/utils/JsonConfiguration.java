package dev.mruniverse.slimelib.utils;

import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class JsonConfiguration {

    private static final Gson json = new GsonBuilder().serializeNulls().setPrettyPrinting().registerTypeAdapter(PluginConfiguration.class, (JsonSerializer<PluginConfiguration>) (src, typeOfSrc, context) -> context.serialize(src.self)).create();

    public static void save(PluginConfiguration config, File file) throws IOException {
        try (Writer writer = new OutputStreamWriter(
                Files.newOutputStream(file.toPath()),
                Charsets.UTF_8)
        ) {
            save(config, writer);
        }
    }

    public static void save(PluginConfiguration config, Writer writer) {
        json.toJson(config.self, writer);
    }

    public static PluginConfiguration load(File file) throws IOException {
        return load(file, null);
    }

    public static PluginConfiguration load(File file, PluginConfiguration defaults) throws IOException {
        try (FileInputStream is = new FileInputStream(file)) {
            return load(is, defaults);
        }
    }

    public static PluginConfiguration load(Reader reader) {
        return load(reader, null);
    }

    @SuppressWarnings("unchecked")
    public static PluginConfiguration load(Reader reader, PluginConfiguration defaults) {
        Map<String, Object> map = json.fromJson(reader, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new PluginConfiguration(map, defaults);
    }

    public static PluginConfiguration load(InputStream is)
    {
        return load(is, null);
    }

    public static PluginConfiguration load(InputStream is, PluginConfiguration defaults) {
        return load(new InputStreamReader(is, Charsets.UTF_8), defaults);
    }

    public static PluginConfiguration load(String string)
    {
        return load(string, null);
    }

    @SuppressWarnings("unchecked")
    public static PluginConfiguration load(String string, PluginConfiguration defaults) {
        Map<String, Object> map = json.fromJson(string, LinkedHashMap.class);
        if (map == null) {
            map = new LinkedHashMap<>();
        }
        return new PluginConfiguration(map, defaults);
    }
}
