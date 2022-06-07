package dev.mruniverse.slimelib.colors.platforms.velocity;

import dev.mruniverse.slimelib.colors.ColorUtils;
import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.SlimeText;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@SuppressWarnings("unused")
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

    @Override
    public Component empty() {
        return Component.empty();
    }

    @Override
    public Component build() {
        if (hasLegacy() && !hasGradient() && !hasSolid()) {
            return Component.text(legacy(getContent()));
        }

        if (hasGradient()) {

            String result;

            if (!hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                Component textComponent = Component.empty();

                boolean findGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    replaceText = start + ")" + content + "(end-point:" + end + ")%";

                    List<TextComponent> componentList = new ArrayList<>();
                    List<Color> colorList = new ArrayList<>();

                    String[] contentSplit = content.split("");

                    colorList.add(
                            getColor(start)
                    );

                    colorList.add(
                            getColor(end)
                    );

                    for (String character : contentSplit) {
                        componentList.add(Component.text(character));
                    }

                    findGradient = true;

                    int length = componentList.size();

                    List<Color> gradient = ColorUtils.createColorGradient(
                            length,
                            colorList,
                            true
                    );

                    List<Component> components = new ArrayList<>();

                    int size = 0;
                    for (TextComponent component : componentList) {
                        Color color = gradient.get(size);

                        components.add(
                                component.asComponent().color(
                                        TextColor.color(
                                                color.getRed(), color.getGreen(), color.getBlue()
                                        )
                                )
                        );
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "\\)%");

                            for (Component component : components) {
                                textComponent = textComponent.append(component);
                            }

                            if (hasLegacy()) {
                                textComponent = textComponent.append(Component.text(legacy(secondSplit[1])));
                            } else {
                                textComponent = textComponent.append(Component.text(secondSplit[1]));
                            }
                        } else {
                            if (hasLegacy()) {
                                textComponent = textComponent.append(Component.text(legacy(split)));
                            } else {
                                textComponent = textComponent.append(Component.text(split));
                            }
                        }
                    }
                }
                if (!findGradient) {
                    for (String split : splitContent) {
                        if (hasLegacy()) {
                            textComponent = textComponent.append(Component.text(legacy(split)));
                        } else {
                            textComponent = textComponent.append(Component.text(split));
                        }
                    }
                }
                return textComponent;
            }

            if (hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                Component textComponent = Component.empty();

                boolean hasGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start = gradientMatcher.group(1);
                    String end = gradientMatcher.group(3);

                    replaceText = start + ")" + content + "(end-point:" + end + ")%";

                    List<TextComponent> componentList = new ArrayList<>();
                    List<Color> colorList = new ArrayList<>();

                    String[] contentSplit = content.split("");

                    colorList.add(
                            getColor(start)
                    );

                    colorList.add(
                            getColor(end)
                    );
                    hasGradient = true;

                    for (String character : contentSplit) {
                        componentList.add(Component.text(character));
                    }

                    int length = componentList.size();

                    List<Color> gradient = ColorUtils.createColorGradient(
                            length,
                            colorList,
                            true
                    );

                    List<Component> components = new ArrayList<>();

                    int size = 0;
                    for (TextComponent component : componentList) {
                        Color color = gradient.get(size);

                        components.add(
                                component.asComponent().color(
                                        TextColor.color(
                                                color.getRed(), color.getGreen(), color.getBlue()
                                        )
                                )
                        );
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "\\)%");

                            for (Component component : components) {
                                textComponent = textComponent.append(component);
                            }

                            textComponent = processSolid(textComponent, secondSplit[1]);

                        } else {
                            textComponent = processSolid(textComponent, split);
                        }
                    }
                }
                if (!hasGradient) {
                    for (String split : splitContent) {
                        textComponent = processSolid(textComponent, split);
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

            Component textComponent = Component.empty();
            boolean hasSolid = false;

            while (matcher.find()) {
                String content = matcher.group(2);
                String color   = matcher.group(1);

                replaceText = color + ")" + content;

                Component component = Component.text(content);

                Color color1 = getColor(color);

                component = component.color(
                        TextColor.color(
                                color1.getRed(), color1.getGreen(), color1.getBlue()
                        )
                );

                hasSolid = true;

                for (String split : splitContent) {
                    if (split.contains(replaceText)) {

                        String[] secondSplit = split.split("\\(end-point\\)%");

                        textComponent = textComponent.append(component);

                        if (hasLegacy()) {
                            textComponent = textComponent.append(Component.text(legacy(secondSplit[1])));
                        } else {
                            textComponent = textComponent.append(Component.text(secondSplit[1]));
                        }
                    } else {
                        if (hasLegacy()) {
                            textComponent = textComponent.append(Component.text(legacy(split)));
                        } else {
                            textComponent = textComponent.append(Component.text(split));
                        }
                    }
                }
            }
            if (!hasSolid) {
                for (String split : splitContent) {
                    if (hasLegacy()) {
                        textComponent = textComponent.append(Component.text(legacy(split)));
                    } else {
                        textComponent = textComponent.append(Component.text(split));
                    }
                }
            }
            return textComponent;
        }
        return Component.text(legacy(getContent()));
    }

    private @NotNull String legacy(String content) {
        return LegacyComponentSerializer.builder().character('&').build().deserialize(content).content();
    }

    private Component processSolid(Component component, String paramText) {
        Matcher matcher = SOLID_PATTERN.matcher(paramText);

        String replaceText;

        String[] splitContent = paramText.split("%\\(slimecolor solid:");

        Component textComponent = component;

        boolean hasSolid = false;

        while (matcher.find()) {
            String content = matcher.group(2);
            String color   = matcher.group(1);

            replaceText = color + ")" + content;

            Component result = Component.empty();

            Color color1 = getColor(color);

            result = result.color(
                    TextColor.color(
                            color1.getRed(), color1.getGreen(), color1.getBlue()
                    )
            );

            hasSolid = true;

            for (String split : splitContent) {
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point\\)%");

                    textComponent = textComponent.append(result);

                    if (hasLegacy()) {
                        textComponent = textComponent.append(Component.text(legacy(secondSplit[1])));
                    } else {
                        textComponent = textComponent.append(Component.text(secondSplit[1]));
                    }

                } else {
                    if (hasLegacy()) {
                        textComponent = textComponent.append(Component.text(legacy(split)));
                    } else {
                        textComponent = textComponent.append(Component.text(split));
                    }
                }
            }
        }
        if (!hasSolid) {
            for (String split : splitContent) {
                if (hasLegacy()) {
                    textComponent = textComponent.append(Component.text(legacy(split)));
                } else {
                    textComponent = textComponent.append(Component.text(split));
                }
            }
        }
        return textComponent;
    }

    private Color getColor(String color) {
        return new Color(Integer.parseInt(color, 16));
    }
}
