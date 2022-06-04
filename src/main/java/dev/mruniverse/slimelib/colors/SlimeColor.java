package dev.mruniverse.slimelib.colors;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.colors.platforms.bungeecord.BungeeComponent;
import dev.mruniverse.slimelib.colors.platforms.bungeecord.BungeeSlimeColor;
import dev.mruniverse.slimelib.colors.platforms.velocity.DefaultComponent;
import dev.mruniverse.slimelib.colors.platforms.velocity.DefaultSlimeColor;
import dev.mruniverse.slimelib.utils.ClassUtils;
import net.md_5.bungee.api.ChatColor;

public class SlimeColor {

    private static final boolean HAS_RGB_SUPPORT = ClassUtils.hasMethod(ChatColor.class, "of", String.class);

    private final SlimeText<?> slimeText;

    public SlimeColor(SlimePlatform platform, String content) {
        this.slimeText = getSlimeText(platform, content);
        if (HAS_RGB_SUPPORT) {
            checks(ColorMethod.ALL);
            return;
        }
        checks(ColorMethod.LEGACY);
    }

    public SlimeColor(SlimePlatform platform, String content, ColorMethod method) {
        this.slimeText = getSlimeText(platform, content);
        if (HAS_RGB_SUPPORT) {
            checks(method);
            return;
        }
        checks(ColorMethod.LEGACY);
    }

    public void setContent(String content) {
        slimeText.setContent(content);
    }


    private void checks(ColorMethod method) {
        if (method == ColorMethod.ALL) {
            execute(ColorMethod.GRADIENT);
            execute(ColorMethod.SOLID);
            execute(ColorMethod.LEGACY);
            return;
        }
        execute(method);
    }

    private void execute(ColorMethod method) {
        switch (method) {
            case GRADIENT:
                slimeText.applyGradient();
                break;
            case SOLID:
                slimeText.applySolid();
                break;
            case ALL:
            case LEGACY:
                slimeText.applyLegacy();
                break;
        }
    }

    public SlimeText<?> getSlimeText() {
        return slimeText;
    }

    public BungeeComponent getBungeeComponent() {
        return new BungeeComponent(slimeText);
    }

    public DefaultComponent getDefaultComponent() {
        return new DefaultComponent(slimeText);
    }

    public SlimeText<?> getSlimeText(SlimePlatform platform, String content) {
        switch (platform) {
            default:
            case BUNGEECORD:
            case SPIGOT:
                return new BungeeSlimeColor(content, HAS_RGB_SUPPORT);
            case VELOCITY:
            case SPONGE:
                return new DefaultSlimeColor(content, HAS_RGB_SUPPORT);
        }
    }

    public enum ColorMethod {
        ALL,
        SOLID,
        GRADIENT,
        LEGACY
    }

    public String getContent() {
        return slimeText.getContent();
    }


}
