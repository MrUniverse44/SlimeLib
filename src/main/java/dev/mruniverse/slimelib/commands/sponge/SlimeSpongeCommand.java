package dev.mruniverse.slimelib.commands.sponge;

import dev.mruniverse.slimelib.commands.command.SlimeCommand;
import dev.mruniverse.slimelib.commands.sender.Sender;
import dev.mruniverse.slimelib.commands.sender.console.SlimeConsoleSponge;
import dev.mruniverse.slimelib.commands.sender.player.SlimeSpongePlayer;
import org.spongepowered.api.command.CommandExecutor;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.parameter.CommandContext;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cause;

import java.util.Optional;

public class SlimeSpongeCommand implements CommandExecutor {

    private final SlimeCommand slimeCommand;
    private final SlimeConsoleSponge spongeConsole = new SlimeConsoleSponge();

    public SlimeSpongeCommand(SlimeCommand slimeCommand) {
        this.slimeCommand = slimeCommand;
    }

    @Override
    public CommandResult execute(CommandContext context) {
        slimeCommand.execute(
                cast(context),
                context.identifier(),
                new String[]{"NOT", "FINISHED"}
        );
        return CommandResult.success();
    }

    private Sender cast(CommandContext context) {
        Cause source = context.cause().cause();
        Optional<Player> optional = source.first(Player.class);
        if (optional.isPresent()) {
            return new SlimeSpongePlayer(optional.get());
        }
        return spongeConsole;
    }
}
