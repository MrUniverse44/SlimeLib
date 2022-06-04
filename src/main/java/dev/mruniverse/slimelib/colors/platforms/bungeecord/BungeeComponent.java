package dev.mruniverse.slimelib.colors.platforms.bungeecord;

import dev.mruniverse.slimelib.colors.SlimeText;
import net.md_5.bungee.api.chat.BaseComponent;

@SuppressWarnings("unused")
public class BungeeComponent {
    private final SlimeText<BaseComponent> slimeText;

    @SuppressWarnings("unchecked")
    public BungeeComponent(SlimeText<?> slimeText) {
        this.slimeText = (SlimeText<BaseComponent>) slimeText;
    }

    public SlimeText<BaseComponent> getSlimeText() {
        return slimeText;
    }

    public BaseComponent build() {
        return slimeText.build();
    }
}
