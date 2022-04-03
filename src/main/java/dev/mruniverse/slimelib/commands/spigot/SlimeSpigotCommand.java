package dev.mruniverse.slimelib.commands.spigot;

import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.commands.sender.console.SlimeConsoleSpigot;
import dev.mruniverse.slimelib.commands.sender.player.SlimePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SlimeSpigotCommand extends Command {
    private final SlimeCommand command;

    public SlimeSpigotCommand(SlimeCommand command, String description, String usage) {
        super(command.getCommand(), description, usage, command.getAliases());
        this.command = command;
    }

    /**
     * Executes the command, returning its success
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     * @return true if the command was successful, otherwise false
     */
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        command.execute(cast(sender), commandLabel, args);
        return true;
    }

    private Sender cast(CommandSender sender) {
        if (sender instanceof Player) {
            return new SlimePlayer((Player)sender);
        }
        return new SlimeConsoleSpigot();
    }
}
