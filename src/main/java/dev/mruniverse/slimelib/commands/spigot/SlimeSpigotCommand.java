package dev.mruniverse.slimelib.commands.spigot;

import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.source.SlimeSource;
import dev.mruniverse.slimelib.source.console.SlimeConsoleSpigot;
import dev.mruniverse.slimelib.source.player.SlimePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SlimeSpigotCommand extends Command implements TabCompleter {
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

    private SlimeSource<?> cast(CommandSender sender) {
        if (sender instanceof Player) {
            return new SlimePlayer((Player)sender);
        }
        return new SlimeConsoleSpigot();
    }

    /**
     * Requests a list of possible completions for a command argument.
     *
     * @param sender  Source of the command.  For players tab-completing a
     *                command inside a command block, this will be the player, not
     *                the command block.
     * @param command Command which was executed
     * @param alias   The alias used
     * @param args    The arguments passed to the command, including final
     *                partial argument to be completed and command label
     * @return A List of possible completions for the final argument, or null
     * to default to the command executor
     */
    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return this.command.onTabComplete(
                cast(sender),
                alias,
                args
        );
    }
}
