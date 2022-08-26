package dev.mruniverse.slimelib.commands.velocity;

import com.google.common.collect.ImmutableList;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.source.SlimeSource;
import dev.mruniverse.slimelib.source.console.SlimeConsoleVelocity;
import dev.mruniverse.slimelib.source.player.SlimeVelocityPlayer;

import java.util.List;

public class SlimeVelocityCommand implements SimpleCommand {
    private final SlimeCommand command;
    private final ProxyServer server;

    public SlimeVelocityCommand(ProxyServer server, SlimeCommand command) {
        this.command = command;
        this.server = server;
    }
    /**
     * Executes the command for the specified invocation.
     *
     * @param invocation the invocation context
     */
    @Override
    public void execute(Invocation invocation) {
        CommandSource sender = invocation.source();

        command.execute(
                cast(sender),
                invocation.alias(),
                invocation.arguments()
        );
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        CommandSource sender = invocation.source();

        List<String> tabComplete = command.onTabComplete(
                cast(sender),
                invocation.alias(),
                invocation.arguments()
        );

        if (tabComplete != null) {
            if (tabComplete.size() >= 1) {
                return tabComplete;
            }
        }

        return ImmutableList.of();
    }

    public void unregister() {
        try {
            server.getCommandManager().unregister(command.getCommand());
            for (String alias : command.getAliases()) {
                server.getCommandManager().unregister(alias);
            }
        } catch (Exception ignored) {

        }
    }

    private SlimeSource<?> cast(CommandSource sender) {
        if (sender instanceof Player) {
            return new SlimeVelocityPlayer((Player)sender);
        }
        return new SlimeConsoleVelocity(server);
    }
}
