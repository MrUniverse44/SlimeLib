package dev.mruniverse.guardianstorageapi.examples;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Dependency;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.lang.annotation.Annotation;
import java.nio.file.Path;

import dev.mruniverse.guardianstorageapi.GuardianStorageAPI;
import dev.mruniverse.guardianstorageapi.enums.ControlType;
import dev.mruniverse.guardianstorageapi.inputs.VelocityInputManager;
import dev.mruniverse.guardianstorageapi.interfaces.FileStorage;
import dev.mruniverse.guardianstorageapi.logs.SpigotLogs;
import dev.mruniverse.guardianstorageapi.logs.VelocityLogs;
import dev.mruniverse.guardianstorageapi.utils.GuardianHelper;
import org.slf4j.Logger;

@SuppressWarnings({"unused", "ClassExplicitlyAnnotation"})
@Plugin(
        id = "pixelmotd",
        name = "PixelMOTDBuilder",
        version = "9.0.0",
        description = "Simple Motd Plugin",
        url = "darkness.studios",
        authors = {"MrUniverse44"}
)
public class TestsVelocity implements Plugin {

    @Inject
    private final ProxyServer server;
    @Inject
    private final Logger logger;

    private final FileStorage storage;

    // path of the plugin
    @Inject
    private @DataDirectory
    Path dataDirectory;

    // connect to the server and logger
    @Inject
    public TestsVelocity(ProxyServer server, Logger logger,Path directory){
        this.server = server;
        this.logger = logger;

        storage = new GuardianStorageAPI(ControlType.VELOCITY)
                .setLogs(new SpigotLogs("TestPlugin", "TestPlugin.package."))
                .setInputManager(new VelocityInputManager(this))
                .createStorage(directory.toFile())
                .setEnums(GuardianHelper.process(ExampleEnum.class));

    }

    public ProxyServer getServer() {
        return server;
    }

    @Subscribe
    public void onInitialize(ProxyInitializeEvent event) {
        storage.getControl(ExampleEnum.FILE1).reload();
        storage.getControl(ExampleEnum.FILE2).reload();
        storage.getControl(ExampleEnum.FILE3).reload();
        new VelocityLogs(server,"Test","dev.mruniverse.guardianstorageapi.examples.").debug(storage.getControl(ExampleEnum.FILE2).getColoredString("TestsVelocity","&cDefault"));
    }

    /**
     * The ID of the plugin. This ID should be unique as to not conflict with other plugins. The
     * plugin ID may contain alphanumeric characters, dashes, and underscores, and be a maximum
     * of 64 characters long.
     *
     * @return the ID for this plugin
     */
    @Override
    public String id() {
        return "example";
    }

    /**
     * The human-readable name of the plugin as to be used in descriptions and similar things.
     *
     * @return The plugin name, or an empty string if unknown
     */
    @Override
    public String name() {
        return "TestsGuardianStorageAPI";
    }

    /**
     * The version of the plugin.
     *
     * @return the version of the plugin, or an empty string if unknown
     */
    @Override
    public String version() {
        return "1.0.3";
    }

    /**
     * The description of the plugin, explaining what it can be used for.
     *
     * @return The plugin description, or an empty string if unknown
     */
    @Override
    public String description() {
        return "This is only a test";
    }

    /**
     * The URL or website of the plugin.
     *
     * @return The plugin url, or an empty string if unknown
     */
    @Override
    public String url() {
        return "";
    }

    /**
     * The author of the plugin.
     *
     * @return the plugin's author, or empty if unknown
     */
    @Override
    public String[] authors() {
        String[] strings = new String[1];
        strings[0] = "MrUniverse44";
        return strings;
    }

    /**
     * The dependencies required to load before this plugin.
     *
     * @return the plugin dependencies
     */
    @Override
    public Dependency[] dependencies() {
        return new Dependency[0];
    }

    /**
     * Returns the annotation type of this annotation.
     *
     * @return the annotation type of this annotation
     */
    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
