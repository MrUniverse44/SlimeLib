package dev.mruniverse.slimelib.colors;

import dev.mruniverse.slimelib.SlimePlatform;
import dev.mruniverse.slimelib.colors.platforms.StringComponent;
import dev.mruniverse.slimelib.colors.platforms.StringSlimeColor;
import dev.mruniverse.slimelib.colors.platforms.bungeecord.BungeeComponent;
import dev.mruniverse.slimelib.colors.platforms.bungeecord.BungeeSlimeColor;
import dev.mruniverse.slimelib.colors.platforms.velocity.DefaultComponent;
import dev.mruniverse.slimelib.colors.platforms.velocity.DefaultSlimeColor;
import dev.mruniverse.slimelib.utils.ClassUtils;
import net.md_5.bungee.api.ChatColor;

@SuppressWarnings("unused")
public class SlimeColor {

    private static final boolean HAS_RGB_SUPPORT = ClassUtils.hasMethod(ChatColor.class, "of", String.class);

    private final SlimeText<?> slimeText;

    /**
     * Add Hex, Gradients or Legacy Colors to a specified message
     * @param platform Your current platform
     * @param content Your text to check
     */
    public SlimeColor(SlimePlatform platform, String content) {
        this.slimeText = getSlimeText(platform, content);
        if (HAS_RGB_SUPPORT) {
            checks(ColorMethod.ALL);
        }
    }

    /**
     * Add Hex, Gradients or Legacy Colors to a specified message
     * @param platform Your current platform
     * @param content Your text to check
     * @param method The specified method than you want to use
     */
    public SlimeColor(SlimePlatform platform, String content, ColorMethod method) {
        this.slimeText = getSlimeText(platform, content);
        if (HAS_RGB_SUPPORT) {
            checks(method);
        }
    }

    /**
     * Add Hex, Gradients or Legacy Colors to a specified message
     * @param content Your text to check
     * @param method The specified method than you want to use
     */
    public SlimeColor(String content, ColorMethod method) {
        this.slimeText = getSlimeText(
                SlimePlatform.getAutomatically(),
                content
        );
        if (HAS_RGB_SUPPORT) {
            checks(method);
        }
    }

    /**
     * Add Hex, Gradients or Legacy Colors to a specified message
     * @param content Your text to check
     * @param inString specify <code>true</code> if you want the result in a string or <code>false</code> if you want the result in a component.
     * @param method The specified method than you want to use
     */
    public SlimeColor(String content, Boolean inString, ColorMethod method) {
        if (inString) {
            this.slimeText = new StringSlimeColor(content);
        } else {
            this.slimeText = getSlimeText(
                    SlimePlatform.getAutomatically(),
                    content
            );
        }
        if (HAS_RGB_SUPPORT) {
            checks(method);
        }
    }

    /**
     * Change your current text to another
     * @param content your text
     */
    public void setContent(String content) {
        slimeText.setContent(content);
    }


    private void checks(ColorMethod method) {
        if (method == ColorMethod.ALL) {
            execute(ColorMethod.GRADIENT);
            execute(ColorMethod.SOLID);
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
        }
    }

    /**
     * Gives the SlimeText     *  is the Builder of the current text to apply colors
     * @return the SlimeText
     */
    public SlimeText<?> getSlimeText() {
        return slimeText;
    }

    /**
     * Converts the Current SlimeColor in a BungeeCord Component (WARNING: Only use this if you are running on bungeecord or spigot)
     * @return BungeeComponent to get a BaseComponent
     */
    public BungeeComponent getBungeeComponent() {
        return new BungeeComponent(slimeText);
    }

    /**
     * Converts the Current SlimeColor in a BungeeCord Component (WARNING: Only use this if you are running on bungeecord or spigot)
     * @return BungeeComponent to get a BaseComponent
     */
    public StringComponent getStringComponent() {
        return new StringComponent(slimeText);
    }

    /**
     * Converts the Current SlimeColor in a <code>net.kyori.adventure.text.Component</code>
     * @return DefaultComponent to get a Component (WARNING: Only use this if you are running on velocity or sponge)
     */
    public DefaultComponent getDefaultComponent() {
        return new DefaultComponent(slimeText);
    }

    private SlimeText<?> getSlimeText(SlimePlatform platform, String content) {
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
        GRADIENT
    }

    public String getContent() {
        return slimeText.getContent();
    }


}
