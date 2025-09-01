package me.blueslime.slimelib.colors.platforms.velocity;

import me.blueslime.slimelib.colors.ColorExtras;
import me.blueslime.slimelib.colors.ColorUtils;
import me.blueslime.slimelib.colors.SlimeColor;
import me.blueslime.slimelib.colors.SlimeText;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@SuppressWarnings("unused")
public class DefaultSlimeColor extends SlimeText<Component> {

    public final Map<ColorExtras, Boolean> GRADIENT_BOOLEAN_MAP = new EnumMap<>(ColorExtras.class);

    public final Map<ColorExtras, Boolean> SOLID_BOOLEAN_MAP = new EnumMap<>(ColorExtras.class);

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

    private boolean getBoolean(boolean isGradient, ColorExtras extra) {
        if (isGradient) {
            if (GRADIENT_BOOLEAN_MAP.containsKey(extra)) {
                return GRADIENT_BOOLEAN_MAP.get(extra);
            }
            return GRADIENT_BOOLEAN_MAP.computeIfAbsent(extra, v -> false);
        }
        if (SOLID_BOOLEAN_MAP.containsKey(extra)) {
            return SOLID_BOOLEAN_MAP.get(extra);
        }
        return SOLID_BOOLEAN_MAP.computeIfAbsent(extra, v -> false);
    }

    @Override
    public Component empty() {
        return Component.empty();
    }

    @Override
    public Component build() {
        if (!hasGradient() && !hasSolid()) {
            return createComponent(getContent());
        }

        if (!getContent().contains("%(slimecolor solid:") && !getContent().contains("%(slimecolor start:")) {
            return createComponent(getContent());
        }

        GRADIENT_BOOLEAN_MAP.clear();
        SOLID_BOOLEAN_MAP.clear();

        if (hasGradient()) {

            String result;

            if (!hasSolid()) {
                result = legacy(getContent());

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                Component textComponent = Component.empty();

                boolean findGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    int count = gradientMatcher.groupCount();

                    if (count >= 4) {
                        ColorExtras extra = match(gradientMatcher.group(4));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 5) {
                        ColorExtras extra = match(gradientMatcher.group(5));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 6) {
                        ColorExtras extra = match(gradientMatcher.group(6));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 7) {
                        ColorExtras extra = match(gradientMatcher.group(7));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 8) {
                        ColorExtras extra = match(gradientMatcher.group(8));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    replaceText = start + ")" + content + "(end-point:" + end;

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

                    List<Color> gradient = ColorUtils.createColorGradientHSV(
                            length,
                            colorList
                    );

                    List<Component> components = new ArrayList<>();

                    int size = 0;
                    for (TextComponent component : componentList) {
                        Color color = gradient.get(size);

                        Component component1 = component.asComponent();

                        if (getBoolean(true, ColorExtras.BOLD)) {
                            component1 = component1.decorate(TextDecoration.BOLD);
                        }

                        if (getBoolean(true, ColorExtras.ITALIC)) {
                            component1 = component1.decorate(TextDecoration.ITALIC);
                        }

                        if (getBoolean(true, ColorExtras.MAGIC)) {
                            component1 = component1.decorate(TextDecoration.OBFUSCATED);
                        }

                        if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                            component1 = component1.decorate(TextDecoration.STRIKETHROUGH);
                        }

                        if (getBoolean(true, ColorExtras.UNDERLINE)) {
                            component1 = component1.decorate(TextDecoration.UNDERLINED);
                        }

                        component1 = component1.color(
                                TextColor.color(
                                        color.getRed(), color.getGreen(), color.getBlue()
                                )
                        );

                        components.add(
                                component1
                        );
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                            for (Component component : components) {
                                textComponent = textComponent.append(component);
                            }

                            if (secondSplit.length >= 2) {
                                if (secondSplit[1].contains("%(slimecolor start:")) {
                                    textComponent = processGradient(textComponent, secondSplit[1]);
                                } else {
                                    textComponent = textComponent.append(createComponent(secondSplit[1]));
                                }
                            }
                            return textComponent;
                        } else {
                            textComponent = textComponent.append(createComponent(split));
                        }
                    }
                }
                if (!findGradient) {
                    for (String split : splitContent) {
                        textComponent = textComponent.append(createComponent(split));
                    }
                }
                return textComponent;
            }

            if (hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent;

                Component textComponent = Component.empty();

                boolean hasGradient = false;

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start = gradientMatcher.group(1);
                    String end = gradientMatcher.group(3);

                    String allGroup = gradientMatcher.group();

                    allGroup = allGroup.replace("%(slimecolor start:" + start, "");

                    int count = gradientMatcher.groupCount();

                    if (count >= 4) {
                        ColorExtras extra = match(gradientMatcher.group(4));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 5) {
                        ColorExtras extra = match(gradientMatcher.group(5));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 6) {
                        ColorExtras extra = match(gradientMatcher.group(6));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 7) {
                        ColorExtras extra = match(gradientMatcher.group(7));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    if (count >= 8) {
                        ColorExtras extra = match(gradientMatcher.group(8));
                        GRADIENT_BOOLEAN_MAP.put(extra, true);
                    }

                    replaceText = ")" + content + "(end-point:" + end;

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

                    List<Color> gradient = ColorUtils.createColorGradientHSV(
                            length,
                            colorList
                    );

                    List<Component> components = new ArrayList<>();

                    int size = 0;
                    for (TextComponent component : componentList) {
                        Color color = gradient.get(size);

                        Component component1 = component.asComponent();

                        if (getBoolean(true, ColorExtras.BOLD)) {
                            component1 = component1.decorate(TextDecoration.BOLD);
                        }

                        if (getBoolean(true, ColorExtras.ITALIC)) {
                            component1 = component1.decorate(TextDecoration.ITALIC);
                        }

                        if (getBoolean(true, ColorExtras.MAGIC)) {
                            component1 = component1.decorate(TextDecoration.OBFUSCATED);
                        }

                        if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                            component1 = component1.decorate(TextDecoration.STRIKETHROUGH);
                        }

                        if (getBoolean(true, ColorExtras.UNDERLINE)) {
                            component1 = component1.decorate(TextDecoration.UNDERLINED);
                        }

                        component1 = component1.color(
                                TextColor.color(
                                        color.getRed(), color.getGreen(), color.getBlue()
                                )
                        );

                        components.add(
                                component1
                        );
                        size++;
                    }

                    splitContent = result.split("%\\(slimecolor start:" + start);

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                            for (Component component : components) {
                                textComponent = textComponent.append(component);
                            }

                            if (secondSplit.length >= 2) {
                                if (secondSplit[1].contains("%(slimecolor start:")) {
                                    if (secondSplit[1].contains(allGroup)) {
                                        secondSplit[1] = secondSplit[1].replace(allGroup, "")
                                                .replace("%(slimecolor start:" + start + allGroup, "");
                                    }
                                    textComponent = processGradient(textComponent, secondSplit[1]);
                                } else {
                                    textComponent = processSolid(textComponent, secondSplit[1]);
                                }
                            }
                            return textComponent;
                        } else {
                            if (!split.contains("%(slimecolor start")) {
                                textComponent = processSolid(textComponent, split);
                            }
                        }
                    }
                }

                if (hasGradient) {
                    return textComponent;
                }

                splitContent = result.split("%\\(slimecolor start:");

                for (String split : splitContent) {
                    textComponent = processSolid(textComponent, split);
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

                int count = matcher.groupCount();

                if (count >= 3) {
                    ColorExtras extra = match(matcher.group(3));
                    SOLID_BOOLEAN_MAP.put(extra, true);
                }

                if (count >= 4) {
                    ColorExtras extra = match(matcher.group(4));
                    SOLID_BOOLEAN_MAP.put(extra, true);
                }

                if (count >= 5) {
                    ColorExtras extra = match(matcher.group(5));
                    SOLID_BOOLEAN_MAP.put(extra, true);
                }

                if (count >= 6) {
                    ColorExtras extra = match(matcher.group(6));
                    SOLID_BOOLEAN_MAP.put(extra, true);
                }

                if (count >= 7) {
                    ColorExtras extra = match(matcher.group(7));
                    SOLID_BOOLEAN_MAP.put(extra, true);
                }

                replaceText = color + ")" + content;

                Component component = Component.text(content);

                Color color1 = getColor(color);

                if (getBoolean(false, ColorExtras.BOLD)) {
                    component = component.decorate(TextDecoration.BOLD);
                }

                if (getBoolean(false, ColorExtras.ITALIC)) {
                    component = component.decorate(TextDecoration.ITALIC);
                }

                if (getBoolean(false, ColorExtras.MAGIC)) {
                    component = component.decorate(TextDecoration.OBFUSCATED);
                }

                if (getBoolean(false, ColorExtras.STRIKETHROUGH)) {
                    component = component.decorate(TextDecoration.STRIKETHROUGH);
                }

                if (getBoolean(false, ColorExtras.UNDERLINE)) {
                    component = component.decorate(TextDecoration.UNDERLINED);
                }

                component = component.color(
                        TextColor.color(
                                color1.getRed(), color1.getGreen(), color1.getBlue()
                        )
                );

                hasSolid = true;

                for (String split : splitContent) {
                    if (split.contains(replaceText)) {

                        String[] secondSplit = split.split("\\(end-point( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                        textComponent = textComponent.append(component);

                        if (secondSplit.length >= 2) {
                            if (secondSplit[1].contains("%(slimecolor solid:")) {
                                textComponent = processSolid(textComponent, secondSplit[1]);
                            } else {
                                textComponent = textComponent.append(component(secondSplit[1]));
                            }
                        }
                        return textComponent;
                    } else {
                        textComponent = textComponent.append(component(split));
                    }
                }
            }
            if (!hasSolid) {
                for (String split : splitContent) {
                    textComponent = textComponent.append(component(split));
                }
            }
            return textComponent;
        }
        return Component.text(legacy(getContent()));
    }

    private Component component(String message) {
        return Component.text(legacy(message));
    }

    public ColorExtras match(String text) {

        if (text == null) {
            return ColorExtras.RESET;
        }

        text = text.toLowerCase()
                .replace(" ", "")
                .replace("add:", "");

        return switch (text) {
            case "&l", "bold" -> ColorExtras.BOLD;
            case "&o", "italic" -> ColorExtras.ITALIC;
            case "&k", "magic" -> ColorExtras.MAGIC;
            case "&m", "strikethrough" -> ColorExtras.STRIKETHROUGH;
            case "&n", "underline" -> ColorExtras.UNDERLINE;
            default -> ColorExtras.RESET;
        };
    }

    private String legacy(String content) {
        if (content == null || content.isEmpty()) {
            return "";
        }
        return LegacyComponentSerializer.builder().character('&').build().deserialize(content).content();
    }

    private Component createComponent(String content) {
        return LegacyComponentSerializer.legacyAmpersand().deserialize(content);
    }

    @Override
    public String toString() {
        Component build = build();

        return LegacyComponentSerializer.legacySection().serialize(build);
    }

    private Component processGradient(Component gradientComponent, String paramText) {

        Matcher gradientMatcher = GRADIENT_PATTERN.matcher(paramText);

        String replaceText;

        String[] splitContent;

        boolean hasGradient = false;

        while (gradientMatcher.find()) {
            String content = gradientMatcher.group(2);
            String start = gradientMatcher.group(1);
            String end = gradientMatcher.group(3);

            String allGroup = gradientMatcher.group();

            allGroup = allGroup.replace("%(slimecolor start:" + start, "");

            int count = gradientMatcher.groupCount();

            if (count >= 4) {
                ColorExtras extra = match(gradientMatcher.group(4));
                GRADIENT_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 5) {
                ColorExtras extra = match(gradientMatcher.group(5));
                GRADIENT_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 6) {
                ColorExtras extra = match(gradientMatcher.group(6));
                GRADIENT_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 7) {
                ColorExtras extra = match(gradientMatcher.group(7));
                GRADIENT_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 8) {
                ColorExtras extra = match(gradientMatcher.group(8));
                GRADIENT_BOOLEAN_MAP.put(extra, true);
            }

            replaceText = ")" + content + "(end-point:" + end;

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

            List<Color> gradient = ColorUtils.createColorGradientHSV(
                    length,
                    colorList
            );

            List<Component> components = new ArrayList<>();

            int size = 0;
            for (TextComponent component : componentList) {
                Color color = gradient.get(size);

                Component component1 = component.asComponent();

                if (getBoolean(true, ColorExtras.BOLD)) {
                    component1 = component1.decorate(TextDecoration.BOLD);
                }

                if (getBoolean(true, ColorExtras.ITALIC)) {
                    component1 = component1.decorate(TextDecoration.ITALIC);
                }

                if (getBoolean(true, ColorExtras.MAGIC)) {
                    component1 = component1.decorate(TextDecoration.OBFUSCATED);
                }

                if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                    component1 = component1.decorate(TextDecoration.STRIKETHROUGH);
                }

                if (getBoolean(true, ColorExtras.UNDERLINE)) {
                    component1 = component1.decorate(TextDecoration.UNDERLINED);
                }

                component1 = component1.color(
                        TextColor.color(
                                color.getRed(), color.getGreen(), color.getBlue()
                        )
                );

                components.add(
                        component1
                );
                size++;
            }

            splitContent = paramText.split("%\\(slimecolor start:" + start);

            for (String split : splitContent) {
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                    for (Component component : components) {
                        gradientComponent = gradientComponent.append(component);
                    }

                    if (secondSplit.length >= 2) {
                        if (secondSplit[1].contains("%(slimecolor start:")) {
                            if (secondSplit[1].contains(allGroup)) {
                                secondSplit[1] = secondSplit[1].replace(allGroup, "")
                                        .replace("%(slimecolor start:" + start + allGroup, "");
                            }
                            gradientComponent = processGradient(gradientComponent, secondSplit[1]);

                        } else {
                            gradientComponent = processSolid(gradientComponent, secondSplit[1]);
                        }
                    }
                    return gradientComponent;
                } else {
                    gradientComponent = processSolid(gradientComponent, split);
                }
            }
        }
        if (hasGradient) {
            return gradientComponent;
        }

        splitContent = paramText.split("%\\(slimecolor start:");

        for (String split : splitContent) {
            gradientComponent = processSolid(gradientComponent, split);
        }
        return gradientComponent;
    }

    private Component processSolid(Component component, String paramText) {
        SOLID_BOOLEAN_MAP.clear();

        Matcher matcher = SOLID_PATTERN.matcher(paramText);

        String replaceText;

        String[] splitContent = paramText.split("%\\(slimecolor solid:");

        Component textComponent = component;

        boolean hasSolid = false;

        while (matcher.find()) {
            String content = matcher.group(2);
            String color   = matcher.group(1);

            int count = matcher.groupCount();

            if (count >= 3) {
                ColorExtras extra = match(matcher.group(3));
                SOLID_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 4) {
                ColorExtras extra = match(matcher.group(4));
                SOLID_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 5) {
                ColorExtras extra = match(matcher.group(5));
                SOLID_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 6) {
                ColorExtras extra = match(matcher.group(6));
                SOLID_BOOLEAN_MAP.put(extra, true);
            }

            if (count >= 7) {
                ColorExtras extra = match(matcher.group(7));
                SOLID_BOOLEAN_MAP.put(extra, true);
            }

            replaceText = color + ")" + content;

            Component result = Component.empty();

            Color color1 = getColor(color);

            if (getBoolean(false, ColorExtras.BOLD)) {
                result = result.decorate(TextDecoration.BOLD);
            }

            if (getBoolean(false, ColorExtras.ITALIC)) {
                result = result.decorate(TextDecoration.ITALIC);
            }

            if (getBoolean(false, ColorExtras.MAGIC)) {
                result = result.decorate(TextDecoration.OBFUSCATED);
            }

            if (getBoolean(false, ColorExtras.STRIKETHROUGH)) {
                result = result.decorate(TextDecoration.STRIKETHROUGH);
            }

            if (getBoolean(false, ColorExtras.UNDERLINE)) {
                result = result.decorate(TextDecoration.UNDERLINED);
            }


            result = result.color(
                    TextColor.color(
                            color1.getRed(), color1.getGreen(), color1.getBlue()
                    )
            );

            hasSolid = true;

            for (String split : splitContent) {
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                    textComponent = textComponent.append(result);

                    if (secondSplit.length >= 2) {
                        if (secondSplit[1].contains("%(slimecolor solid:")) {
                            textComponent = processSolid(textComponent, secondSplit[1]);
                        } else {
                            textComponent = textComponent.append(createComponent(secondSplit[1]));
                        }
                    }
                    return textComponent;
                } else {
                    textComponent = textComponent.append(createComponent(split));
                }
            }
        }
        if (!hasSolid) {
            for (String split : splitContent) {
                textComponent = textComponent.append(createComponent(split));
            }
        }
        return textComponent;
    }

    private Color getColor(String color) {
        return new Color(Integer.parseInt(color, 16));
    }
}