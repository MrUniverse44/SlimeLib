package me.blueslime.slimelib.colors.platforms.velocity;

import me.blueslime.slimelib.colors.SlimeText;
import net.kyori.adventure.text.Component;

@SuppressWarnings("unused")
public class DefaultComponent {
    private final SlimeText<Component> slimeText;

    @SuppressWarnings("unchecked")
    public DefaultComponent(SlimeText<?> slimeText) {
        this.slimeText = (SlimeText<Component>) slimeText;
    }

    public SlimeText<Component> getSlimeText() {
        return slimeText;
    }

    public Component build() {
        return slimeText.build();
    }
}