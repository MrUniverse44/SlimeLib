package me.blueslime.slimelib.logs.platforms.sponge;

import me.blueslime.slimelib.logs.SlimeLogger;
import me.blueslime.slimelib.logs.SlimeLogs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.spongepowered.api.Sponge;

public class SlimeLoggerSponge extends SlimeLogs {

    public SlimeLoggerSponge() {
        super();
    }

    public SlimeLoggerSponge(SlimeLogger logger) {
        super(logger);
    }

    private Component color(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message);
    }

    @Override
    public void send(String message) {
        Sponge.server().sendMessage(color(message));
    }
}

