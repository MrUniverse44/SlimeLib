package me.blueslime.slimelib.commands.bungee;

import me.blueslime.slimelib.commands.command.SlimeCommand;
import me.blueslime.slimelib.source.SlimeSource;
import me.blueslime.slimelib.source.console.SlimeConsoleBungee;

import me.blueslime.slimelib.source.player.SlimeProxiedPlayer;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class SlimeBungeeCommand extends Command implements TabExecutor {
    private final SlimeCommand command;

    public SlimeBungeeCommand(SlimeCommand command) {
        super(command.getCommand(), null, command.getAliases().toArray(new String[0]));
        this.command = command;
    }

    /**
     * Execute this command with the specified sender and arguments.
     *
     * @param sender the executor of this command
     * @param args   arguments used to invoke this command
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        command.execute(cast(sender), command.getCommand(), args);
    }

    public void unregister() {
        ProxyServer.getInstance().getPluginManager().unregisterCommand(this);
    }

    private SlimeSource cast(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return new SlimeProxiedPlayer((ProxiedPlayer)sender);
        }
        return new SlimeConsoleBungee();
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return command.onTabComplete(
                cast(sender),
                command.getCommand(),
                args
        );
    }
}

