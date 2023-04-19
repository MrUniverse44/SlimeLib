package me.blueslime.slimelib.source.nms;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.entity.Player;

public class Tool {
    public void injectPlayer(Player player) {
        ChannelDuplexHandler channelDuplexHandler = new ChannelDuplexHandler() {
            @Override
            public void channelRead(ChannelHandlerContext channelHandlerContext, Object packet) {
                if(packet instanceof PacketPlayInUseEntity) {
                    try {
                        PacketPlayInUseEntity pack = (PacketPlayInUseEntity) packet;
                        readPacket(pack,player);
                    }
                    catch (Throwable ignored) { }
                }
                try {
                    super.channelRead(channelHandlerContext, packet);
                } catch (Throwable ignored) { }
            }
        };
        try {
            PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
            Channel channel = ((Channel)getValue(connection.networkManager,"channel"));
            ChannelPipeline pipeline = channel.pipeline();
            pipeline.addBefore("packet_handler", player.getName(), channelDuplexHandler);
        }catch (Throwable ignored) { }
    }
}
