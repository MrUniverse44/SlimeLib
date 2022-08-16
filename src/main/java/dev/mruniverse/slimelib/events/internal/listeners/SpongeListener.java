package dev.mruniverse.slimelib.events.internal.listeners;

import dev.mruniverse.slimelib.events.internal.EventCaller;
import dev.mruniverse.slimelib.events.internal.EventExecutor;
import org.spongepowered.api.Server;

public class SpongeListener extends EventCaller<Server> {
    public SpongeListener(EventExecutor<Server> manager) {
        super(manager);
    }
}
