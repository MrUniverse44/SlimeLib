package dev.mruniverse.slimelib.colors.platforms.bungeecord;

import dev.mruniverse.slimelib.colors.ColorUtils;
import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.SlimeText;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@SuppressWarnings("unused")
public class BungeeSlimeColor extends SlimeText<BaseComponent> {

    public BungeeSlimeColor(String content, boolean hasHexSupport, SlimeColor.ColorMethod method) {
        super(content, hasHexSupport, method);
    }

    public BungeeSlimeColor(String content, boolean hasHexSupport) {
        super(content, hasHexSupport);
    }

    public BungeeSlimeColor(String content) {
        super(content);
    }

    public BungeeSlimeColor() {
        super();
    }

    @Override
    public BaseComponent empty() {
        return new TextComponent();
    }

    @Override
    public BaseComponent build() {
        if (hasLegacy() && !hasGradient() && !hasSolid()) {
            return new TextComponent(ChatColor.translateAlternateColorCodes('&', getContent()));
        }

        if (hasGradient()) {

            String result;

            if (!hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                TextComponent textComponent = new TextComponent();

                boolean findGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    replaceText = start + ")" + content + "(end-point:" + end + ")%";

                    findGradient = true;

                    List<TextComponent> componentList = new ArrayList<>();
                    List<ChatColor> colorList = new ArrayList<>();

                    String[] contentSplit = content.split("");

                    colorList.add(
                            getColor(start)
                    );

                    colorList.add(
                            getColor(end)
                    );

                    for (String character : contentSplit) {
                        componentList.add(new TextComponent(character));
                    }

                    int length = componentList.size();

                    List<ChatColor> gradient = ColorUtils.createGradient(
                            length,
                            colorList,
                            true
                    );

                    int size = 0;
                    for (TextComponent component : componentList) {
                        component.setColor(gradient.get(size));
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "\\)%");

                            componentList.stream().iterator().forEachRemaining(textComponent::addExtra);

                            if (!hasLegacy()) {
                                textComponent.addExtra(secondSplit[1]);
                            } else {
                                textComponent.addExtra(colorize(secondSplit[1]));
                            }

                        } else {
                            if (!hasLegacy()) {
                                textComponent.addExtra(split);
                            } else {
                                textComponent.addExtra(colorize(split));
                            }
                        }
                    }
                }
                if (!findGradient) {
                    for (String split : splitContent) {
                        textComponent.addExtra(split);
                    }
                }
                return textComponent;
            }

            if (hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                TextComponent textComponent = new TextComponent();

                boolean findGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    replaceText = start + ")" + content + "(end-point:" + end + ")%";

                    List<TextComponent> componentList = new ArrayList<>();
                    List<ChatColor> colorList = new ArrayList<>();

                    String[] contentSplit = content.split("");
                    findGradient = true;

                    colorList.add(
                            getColor(start)
                    );

                    colorList.add(
                            getColor(end)
                    );

                    for (String character : contentSplit) {
                        componentList.add(new TextComponent(character));
                    }

                    int length = componentList.size();

                    List<ChatColor> gradient = ColorUtils.createGradient(
                            length,
                            colorList,
                            true
                    );

                    int size = 0;
                    for (TextComponent component : componentList) {
                        component.setColor(gradient.get(size));
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "\\)%");

                            componentList.stream().iterator().forEachRemaining(textComponent::addExtra);

                            processSolid(textComponent, secondSplit[1]);

                        } else {
                            processSolid(textComponent, split);
                        }
                    }
                }
                if (!findGradient) {
                    for (String split : splitContent) {
                        processSolid(textComponent, split);
                    }
                }
                return textComponent;
            }
            if (!hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                TextComponent textComponent = new TextComponent();

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    replaceText = start + ")" + content + "(end-point:" + end + ")%";

                    List<TextComponent> componentList = new ArrayList<>();
                    List<ChatColor> colorList = new ArrayList<>();

                    String[] contentSplit = content.split("");

                    colorList.add(
                            getColor(start)
                    );

                    colorList.add(
                            getColor(end)
                    );

                    for (String character : contentSplit) {
                        componentList.add(new TextComponent(character));
                    }

                    int length = componentList.size();

                    List<ChatColor> gradient = ColorUtils.createGradient(
                            length,
                            colorList,
                            true
                    );

                    int size = 0;
                    for (TextComponent component : componentList) {
                        component.setColor(gradient.get(size));
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "\\)%");

                            componentList.stream().iterator().forEachRemaining(textComponent::addExtra);

                            if (!hasLegacy()) {
                                textComponent.addExtra(secondSplit[1]);
                            } else {
                                textComponent.addExtra(colorize(secondSplit[1]));
                            }

                        } else {
                            if (!hasLegacy()) {
                                textComponent.addExtra(split);
                            } else {
                                textComponent.addExtra(colorize(split));
                            }
                        }
                    }
                }
                return textComponent;
            }
        }

        if (hasSolid()) {
            String result = getContent();

            Matcher matcher = SOLID_PATTERN.matcher(result);

            String replaceText;

            String[] splitContent = result.split("%\\(slimecolor solid:");

            TextComponent textComponent = new TextComponent();

            while (matcher.find()) {
                String content = matcher.group(2);
                String color   = matcher.group(1);

                replaceText = color + ")" + content;

                TextComponent component = new TextComponent(content);

                component.setColor(getColor(color));

                for (String split : splitContent) {
                    if (split.contains(replaceText)) {

                        String[] secondSplit = split.split("\\(end-point\\)%");

                        textComponent.addExtra(component);

                        if (!hasLegacy()) {
                            textComponent.addExtra(secondSplit[1]);
                        } else {
                            textComponent.addExtra(colorize(secondSplit[1]));
                        }
                    } else {
                        if (!hasLegacy()) {
                            textComponent.addExtra(split);
                        } else {
                            textComponent.addExtra(colorize(split));
                        }
                    }
                }

            }

            return textComponent;
        }

        return null;
    }

    private void processSolid(TextComponent component, String paramText) {
        Matcher matcher = SOLID_PATTERN.matcher(paramText);

        String replaceText;

        String[] splitContent = paramText.split("%\\(slimecolor solid:");

        boolean findSolid = false;

        while (matcher.find()) {
            String content = matcher.group(2);
            String color   = matcher.group(1);

            replaceText = color + ")" + content;

            TextComponent result = new TextComponent(content);

            result.setColor(getColor(color));
            findSolid = true;

            for (String split : splitContent) {
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point\\)%");

                    component.addExtra(result);

                    if (!hasLegacy()) {
                        component.addExtra(secondSplit[1]);
                    } else {
                        component.addExtra(colorize(secondSplit[1]));
                    }

                } else {
                    if (!hasLegacy()) {
                        component.addExtra(split);
                    } else {
                        component.addExtra(colorize(split));
                    }
                }
            }
        }
        if (!findSolid) {
            for (String split : splitContent) {
                component.addExtra(split);
            }
        }
    }

    private String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    private ChatColor getColor(String color) {
        return ChatColor.of(new Color(Integer.parseInt(color, 16)));
    }
}
