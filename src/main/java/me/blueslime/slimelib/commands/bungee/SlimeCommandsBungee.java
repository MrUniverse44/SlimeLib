package me.blueslime.slimelib.commands.bungee;


import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.commands.SlimeCommandPlatform;
import me.blueslime.slimelib.commands.command.SlimeCommand;

import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

import java.util.HashMap;
import java.util.Map;

public class SlimeCommandsBungee<T extends Plugin> implements SlimeCommandPlatform {

    private final Map<String, Command> registeredCommandMap = new HashMap<>();

    private final Map<String, SlimeBungeeCommand> commandsMap = new HashMap<>();

    private final SlimePlugin<T> plugin;

    public SlimeCommandsBungee(SlimePlugin<T> plugin) {
        this.plugin = plugin;
    }


    public void register(final SlimeCommand command) {

        final String commandName = command.getCommand();

        final Plugin plugin = this.plugin.getPlugin();

        if (registeredCommandMap.containsKey(commandName)) {
            this.plugin.getLogs().error("Can't register a command that was already registered in this plugin");
            return;
        }

        registeredCommandMap.remove(commandName);

        SlimeBungeeCommand slimeCommand = new SlimeBungeeCommand(command);

        plugin.getProxy().getPluginManager().registerCommand(
                plugin,
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
                SlimeBungeeCommand::unregister
        );
    }
}
