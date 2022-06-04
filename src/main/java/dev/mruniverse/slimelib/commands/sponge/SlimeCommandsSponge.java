package dev.mruniverse.slimelib.commands.sponge;

import dev.mruniverse.slimelib.SlimePlugin;
import dev.mruniverse.slimelib.commands.SlimeCommandPlatform;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import net.kyori.adventure.text.Component;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.Command;
import org.spongepowered.api.command.manager.CommandManager;

import java.util.HashMap;
import java.util.Map;

public class SlimeCommandsSponge<T extends Server> implements SlimeCommandPlatform {

    private final Map<String, Command> registeredCommandMap = new HashMap<>();

    private final Map<String, SlimeSpongeCommand> commandsMap = new HashMap<>();

    private final SlimePlugin<T> plugin;

    public SlimeCommandsSponge(SlimePlugin<T> plugin) {
        this.plugin = plugin;
    }

    @Override
    public void register(SlimeCommand command) {
        final Class<?> commandClass = command.getClass();

        String description;

        String usage;

        String shortDescription;

        if (commandClass.isAnnotationPresent(dev.mruniverse.slimelib.commands.command.Command.class)) {
            dev.mruniverse.slimelib.commands.command.Command info = commandClass.getAnnotation(dev.mruniverse.slimelib.commands.command.Command.class);
            description = info.description();
            shortDescription = info.shortDescription();
            usage = info.usage();
        } else {
            throw new NullPointerException("Command Class: " + commandClass + ", need to be annotated with @Command to work");
        }

        final String commandName = command.getCommand();

        SlimeSpongeCommand slimeCommandClass = new SlimeSpongeCommand(command);

        Command cmd = Command.builder()
                .extendedDescription(Component.text(description))
                .shortDescription(Component.text(shortDescription))
                .executor(slimeCommandClass)
                .build();

        CommandManager manager = Sponge.server().commandManager();
    }

    @Override
    public void unregister() {

    }
}
