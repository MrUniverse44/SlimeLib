package me.blueslime.slimelib.commands.velocity;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.slimelib.SlimePlugin;
import me.blueslime.slimelib.commands.SlimeCommandPlatform;
import me.blueslime.slimelib.commands.command.SlimeCommand;

import java.util.HashMap;
import java.util.Map;

public class SlimeCommandsVelocity<T extends ProxyServer> implements SlimeCommandPlatform {

    private final Map<String, Command> registeredCommandMap = new HashMap<>();

    private final Map<String, SlimeVelocityCommand> commandsMap = new HashMap<>();

    private final SlimePlugin<T> plugin;

    public SlimeCommandsVelocity(SlimePlugin<T> plugin) {
        this.plugin = plugin;
    }

    public void register(final SlimeCommand command) {

        final String commandName = command.getCommand();

        final ProxyServer plugin = this.plugin.getPlugin();

        if (registeredCommandMap.containsKey(commandName)) {
            this.plugin.getLogs().error("Can't register a command that was already registered in this plugin");
            return;
        }

        registeredCommandMap.remove(commandName);

        SlimeVelocityCommand slimeCommand = new SlimeVelocityCommand(plugin, command);

        plugin.getCommandManager().register(
                commandName,
                slimeCommand,
                command.getAliases().toArray(new String[0])
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
                SlimeVelocityCommand::unregister
        );
    }
}

