package dev.mruniverse.slimelib.events.internal.platform;

import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.listeners.BukkitListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class SpigotEventExecutor extends EventExecutor<JavaPlugin> {

    private final BukkitListener listener = new BukkitListener(this);

    private boolean first = false;

    public <T> SpigotEventExecutor(T plugin) {
        super((JavaPlugin) plugin);
    }

    @Override
    public void register(Object plugin, EventType type) {
        if (!first) {
            first = true;
            Bukkit.getServer().getPluginManager().registerEvents(
                    listener,
                    getPlugin()
            );
        }
        getEventRegistered().put(type, true);
    }
}
