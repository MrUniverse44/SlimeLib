package dev.mruniverse.slimelib.listeners.events.ping.bungee;

import dev.mruniverse.slimelib.SlimePlugin;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class ProxyPingListener implements Listener {

    private SlimePlugin<Plugin> plugin;

    public ProxyPingListener(SlimePlugin<Plugin> plugin) {
        this.plugin = plugin;

        Plugin instance = plugin.getPlugin();

        instance.getProxy().getPluginManager().registerListener(instance, this);
    }

    @EventHandler
    public void onPing(ProxyPingEvent event) {

    }

}
