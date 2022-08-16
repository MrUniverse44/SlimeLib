package dev.mruniverse.slimelib.events.internal.platform;

import com.google.inject.Inject;
import dev.mruniverse.slimelib.events.EventType;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import dev.mruniverse.slimelib.events.internal.listeners.SpongeListener;
import org.spongepowered.api.Server;
import org.spongepowered.api.Sponge;
import org.spongepowered.plugin.PluginContainer;

public class SpongeEventExecutor extends EventExecutor<Server> {

    private final SpongeListener listener = new SpongeListener(this);

    private boolean first = false;

    @Inject
    private PluginContainer container;

    public <T> SpongeEventExecutor(T plugin) {
        super((Server)plugin);
    }

    @Override
    public void register(Object plugin, EventType type) {
        if (!first) {
            first = true;
            Sponge.eventManager().registerListeners(
                    container,
                    listener
            );
        }
        getEventRegistered().put(type, true);
    }
}
