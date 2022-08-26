package dev.mruniverse.slimelib.events.internal.converter.bukkit.player;

import dev.mruniverse.slimelib.events.events.PlayerJoinEvent;
import dev.mruniverse.slimelib.source.SlimeSource;
import dev.mruniverse.slimelib.source.player.SlimePlayer;
import org.bukkit.entity.Player;

public class PlayerJoin extends PlayerJoinEvent {

    private final org.bukkit.event.player.PlayerJoinEvent event;

    private final SlimePlayer player;

    public PlayerJoin(org.bukkit.event.player.PlayerJoinEvent event) {
        this.event = event;
        this.player = new SlimePlayer(event.getPlayer());
    }

    @Override
    public String getJoinMessage(){
        return event.getJoinMessage();
    }

    @Override
    public void setJoinMessage(String message){
        event.setJoinMessage(message);
    }

    public SlimeSource<Player> getSource() {
        return player;
    }

}
