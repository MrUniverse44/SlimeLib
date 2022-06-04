package dev.mruniverse.slimelib.colors.platforms.velocity;

import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.SlimeText;
import net.kyori.adventure.text.Component;

public class DefaultSlimeColor extends SlimeText<Component> {

    public DefaultSlimeColor(String content, boolean hasHexSupport, SlimeColor.ColorMethod method) {
        super(content, hasHexSupport, method);
    }

    public DefaultSlimeColor(String content, boolean hasHexSupport) {
        super(content, hasHexSupport);
    }

    public DefaultSlimeColor(String content) {
        super(content);
    }

    public DefaultSlimeColor() {
        super();
    }
}
