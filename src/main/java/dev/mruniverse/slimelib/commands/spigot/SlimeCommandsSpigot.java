package dev.mruniverse.slimelib.commands.spigot;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.commands.SlimeCommandPlatform;
import dev.mruniverse.slimelib.commands.command.Command;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;

import org.bukkit.Server;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginIdentifiableCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SlimeCommandsSpigot<T extends JavaPlugin> implements SlimeCommandPlatform {

    private Map<String, org.bukkit.command.Command> registeredCommandMap = new HashMap<>();

    private final Map<String, SlimeSpigotCommand> commandsMap = new HashMap<>();

    private final SlimePlugin<T> plugin;

    private CommandMap commandMap;

    public SlimeCommandsSpigot(SlimePlugin<T> plugin) {
        this.plugin = plugin;
        init();
    }

    public void register(final SlimeCommand command) {
        final Class<?> commandClass = command.getClass();

        final String description;

        final String usage;

        if (commandClass.isAnnotationPresent(Command.class)) {
            Command info = commandClass.getAnnotation(Command.class);
            description = info.description();
            usage = info.usage();
        } else {
            throw new NullPointerException("Command Class: " + commandClass + ", need to be annotated with @Command to work");
        }

        final String commandName = command.getCommand();

        final org.bukkit.command.Command oldCommand = commandMap.getCommand(commandName);

        final JavaPlugin plugin = this.plugin.getPlugin();

        if (oldCommand instanceof PluginIdentifiableCommand
                && ((PluginIdentifiableCommand) oldCommand).getPlugin() == plugin) {
            registeredCommandMap.remove(commandName);
            oldCommand.unregister(commandMap);
        }

        SlimeSpigotCommand slimeCommand = new SlimeSpigotCommand(command, description, usage);

        commandMap.register(
                commandName,
                plugin.getDescription().getName(),
                slimeCommand
        );

        commandsMap.put(
                commandName,
                slimeCommand
        );
    }

    /**
     * Unregisters all commands registered by this plugin.
     */
    @SuppressWarnings("unused")
    public void unregister() {
        commandsMap.values().forEach(
                command -> command.unregister(commandMap)
        );
    }

    @SuppressWarnings("unchecked")
    private void init() {
        try {
            final Server server = plugin.getPlugin().getServer();
            final Method getCommandMap = server.getClass().getDeclaredMethod("getCommandMap");
            getCommandMap.setAccessible(true);

            this.commandMap = (CommandMap) getCommandMap.invoke(server);

            final Field knownCommands = SimpleCommandMap.class.getDeclaredField("knownCommands");
            knownCommands.setAccessible(true);

            this.registeredCommandMap = (Map<String, org.bukkit.command.Command>) knownCommands.get(commandMap);

        } catch (ReflectiveOperationException exception) {
            plugin.getLogs().error("Can't load command map, are you using a supported version?", exception);
        }
    }
}
