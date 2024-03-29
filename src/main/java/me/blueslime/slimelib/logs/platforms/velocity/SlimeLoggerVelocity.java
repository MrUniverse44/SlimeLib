package me.blueslime.slimelib.logs.platforms.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import me.blueslime.slimelib.logs.SlimeLogger;
import me.blueslime.slimelib.logs.SlimeLogs;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class SlimeLoggerVelocity extends SlimeLogs {

    private final ProxyServer server;

    public SlimeLoggerVelocity(Object server) {
        super();
        this.server = (ProxyServer) server;
    }

    public SlimeLoggerVelocity(ProxyServer server) {
        super();
        this.server = server;
    }

    public SlimeLoggerVelocity(ProxyServer server, SlimeLogger logger) {
        super(logger);
        this.server = server;
    }

    private Component color(String message) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(message);
    }

    @Override
    public void send(String message) {
        server.getConsoleCommandSource().sendMessage(
                color(message)
        );
    }
}
