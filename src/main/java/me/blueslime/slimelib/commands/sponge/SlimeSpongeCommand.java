package me.blueslime.slimelib.commands.sponge;

import me.blueslime.slimelib.commands.command.SlimeCommand;
import me.blueslime.slimelib.source.SlimeSource;
import me.blueslime.slimelib.source.console.SlimeConsoleSponge;
import me.blueslime.slimelib.source.player.SlimeSpongePlayer;
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

    private SlimeSource<?> cast(CommandContext context) {
        Cause source = context.cause().cause();
        Optional<Player> optional = source.first(Player.class);
        if (optional.isPresent()) {
            return new SlimeSpongePlayer(optional.get());
        }
        return spongeConsole;
    }
}
