package me.blueslime.slimelib.colors.platforms.bungeecord;

import me.blueslime.slimelib.colors.ColorExtras;
import me.blueslime.slimelib.colors.ChatColorUtils;
import me.blueslime.slimelib.colors.SlimeColor;
import me.blueslime.slimelib.colors.SlimeText;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

@SuppressWarnings("unused")
public class BungeeSlimeColor extends SlimeText<BaseComponent> {

    public final Map<ColorExtras, Boolean> GRADIENT_BOOLEAN_MAP = new EnumMap<>(ColorExtras.class);

    public final Map<ColorExtras, Boolean> SOLID_BOOLEAN_MAP = new EnumMap<>(ColorExtras.class);

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
    public BaseComponent empty() {
        return new TextComponent();
    }

    @Override
    public BaseComponent build() {
        if (!hasGradient() && !hasSolid()) {
            return new TextComponent(colorize(getContent()));
        }

        if (!getContent().contains("%(slimecolor solid:") && !getContent().contains("%(slimecolor start:")) {
            return new TextComponent(colorize(getContent()));
        }


        GRADIENT_BOOLEAN_MAP.clear();
        SOLID_BOOLEAN_MAP.clear();

        if (hasGradient()) {

            String result;

            if (!hasSolid()) {
                result = colorize(getContent());

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent = result.split("%\\(slimecolor start:");

                TextComponent textComponent = new TextComponent();

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

                    List<ChatColor> gradient = ChatColorUtils.createGradientHSV(
                            length,
                            colorList
                    );

                    int size = 0;
                    for (TextComponent component : componentList) {
                        if (getBoolean(true, ColorExtras.BOLD)) {
                            component.setBold(true);
                        }

                        if (getBoolean(true, ColorExtras.ITALIC)) {
                            component.setItalic(true);
                        }

                        if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                            component.setStrikethrough(true);
                        }

                        if (getBoolean(true, ColorExtras.UNDERLINE)) {
                            component.setUnderlined(true);
                        }

                        if (getBoolean(true, ColorExtras.MAGIC)) {
                            component.setObfuscated(true);
                        }

                        component.setColor(gradient.get(size));
                        size++;
                    }

                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                            componentList.stream().iterator().forEachRemaining(textComponent::addExtra);

                            textComponent.addExtra(
                                    new TextComponent(
                                            colorize(secondSplit[1])
                                    )
                            );
                        } else {
                            textComponent.addExtra(
                                    new TextComponent(
                                            colorize(split)
                                    )
                            );
                        }
                    }
                }
                if (!findGradient) {
                    for (String split : splitContent) {
                        textComponent.addExtra(
                                new TextComponent(
                                        colorize(split)
                                )
                        );
                    }
                }
                return textComponent;
            }

            if (hasSolid()) {
                result = colorize(getContent());

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String replaceText;

                String[] splitContent;

                TextComponent textComponent = new TextComponent();

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

                    //TODO: Check

                    replaceText = ")" + content + "(end-point:" + end;

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

                    List<ChatColor> gradient = ChatColorUtils.createGradientHSV(
                            length,
                            colorList
                    );

                    int size = 0;
                    for (TextComponent component : componentList) {
                        if (getBoolean(true, ColorExtras.BOLD)) {
                            component.setBold(true);
                        }

                        if (getBoolean(true, ColorExtras.ITALIC)) {
                            component.setItalic(true);
                        }

                        if (getBoolean(true, ColorExtras.MAGIC)) {
                            component.setObfuscated(true);
                        }

                        if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                            component.setStrikethrough(true);
                        }

                        if (getBoolean(true, ColorExtras.UNDERLINE)) {
                            component.setUnderlined(true);
                        }

                        component.setColor(gradient.get(size));
                        size++;
                    }

                    splitContent = result.split("%\\(slimecolor start:" + start);

                    int splitInt = 0;
                    for (String split : splitContent) {
                        if (split.contains(replaceText)) {

                            String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                            componentList.stream().iterator().forEachRemaining(textComponent::addExtra);

                            if (secondSplit.length >= 2) {
                                if (secondSplit[1].contains("%(slimecolor start:")) {
                                    processGradient(textComponent, secondSplit[1]);
                                } else {
                                    processSolid(textComponent, secondSplit[1]);
                                }
                            }
                            return textComponent;
                        } else {
                            processSolid(textComponent, split);
                        }
                        splitInt++;
                    }
                }

                splitContent = result.split("%\\(slimecolor start:");

                if (!findGradient) {
                    for (String split : splitContent) {
                        processSolid(textComponent, split);
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

                TextComponent component = new TextComponent(content);

                if (getBoolean(false, ColorExtras.BOLD)) {
                    component.setBold(true);
                }

                if (getBoolean(false, ColorExtras.ITALIC)) {
                    component.setItalic(true);
                }

                if (getBoolean(false, ColorExtras.MAGIC)) {
                    component.setObfuscated(true);
                }

                if (getBoolean(false, ColorExtras.STRIKETHROUGH)) {
                    component.setStrikethrough(true);
                }

                if (getBoolean(false, ColorExtras.UNDERLINE)) {
                    component.setUnderlined(true);
                }
                component.setColor(getColor(color));

                for (String split : splitContent) {
                    if (split.contains(replaceText)) {

                        String[] secondSplit = split.split("\\(end-point( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                        textComponent.addExtra(component);

                        if (secondSplit[1].contains("%(slimecolor solid:")) {
                            processSolid(textComponent, secondSplit[1]);
                        } else {
                            textComponent.addExtra(
                                    new TextComponent(
                                            colorize(secondSplit[1])
                                    )
                            );
                        }
                        return textComponent;
                    } else {
                        textComponent.addExtra(
                                new TextComponent(
                                        colorize(split)
                                )
                        );
                    }
                }

            }

            return textComponent;
        }

        return null;
    }

    public void processGradient(TextComponent gradientComponent, String text) {
        text = colorize(text);

        Matcher gradientMatcher = GRADIENT_PATTERN.matcher(text);

        String replaceText;

        String[] splitContent;

        boolean findGradient = false;

        while (gradientMatcher.find()) {
            String content = gradientMatcher.group(2);
            String start   = gradientMatcher.group(1);
            String end     = gradientMatcher.group(3);

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

            List<ChatColor> gradient = ChatColorUtils.createGradientHSV(
                    length,
                    colorList
            );

            int size = 0;
            for (TextComponent component : componentList) {
                if (getBoolean(true, ColorExtras.BOLD)) {
                    component.setBold(true);
                }

                if (getBoolean(true, ColorExtras.ITALIC)) {
                    component.setItalic(true);
                }

                if (getBoolean(true, ColorExtras.MAGIC)) {
                    component.setObfuscated(true);
                }

                if (getBoolean(true, ColorExtras.STRIKETHROUGH)) {
                    component.setStrikethrough(true);
                }

                if (getBoolean(true, ColorExtras.UNDERLINE)) {
                    component.setUnderlined(true);
                }

                component.setColor(gradient.get(size));
                size++;
            }

            splitContent = text.split("%\\(slimecolor start:" + start);

            int id = 0;

            for (String split : splitContent) {
                split = split.replace("%(slimecolor start:" + start + allGroup, "");
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point:" + end + "( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                    componentList.stream().iterator().forEachRemaining(gradientComponent::addExtra);

                    if (secondSplit.length >= 2) {

                        if (secondSplit[1].contains("%(slimecolor start:")) {

                            if (secondSplit[1].contains(allGroup)) {
                                secondSplit[1] = secondSplit[1].replace(allGroup, "")
                                        .replace("%(slimecolor start:" + start + allGroup, "");

                            }

                            processGradient(gradientComponent, secondSplit[1]);
                        } else {
                            processSolid(gradientComponent, secondSplit[1]);
                        }
                    }
                    return;
                } else {
                    if (!split.contains("%(slimecolor start")) {

                        split = split.replace(
                                allGroup, ""
                        );

                        processSolid(
                                gradientComponent,
                                split
                        );
                    }
                }
                id++;
            }
        }

        if (findGradient) {
            return;
        }

        splitContent = text.split("%\\(slimecolor start:");

        for (String split : splitContent) {
            processSolid(gradientComponent, split);
        }
    }

    public ColorExtras match(String text) {

        if (text == null) {
            return ColorExtras.RESET;
        }

        text = text.toLowerCase()
                .replace(" ", "")
                .replace("add:", "");

        switch (text) {
            case "l":
            case "&l":
            case "bold":
                return ColorExtras.BOLD;
            case "&o":
            case "o":
            case "italic":
                return ColorExtras.ITALIC;
            case "&k":
            case "k":
            case "magic":
                return ColorExtras.MAGIC;
            case "&m":
            case "m":
            case "strikethrough":
                return ColorExtras.STRIKETHROUGH;
            case "&n":
            case "n":
            case "underline":
                return ColorExtras.UNDERLINE;
            default:
                return ColorExtras.RESET;
        }
    }

    private void processSolid(TextComponent component, String paramText) {
        SOLID_BOOLEAN_MAP.clear();
        Matcher matcher = SOLID_PATTERN.matcher(paramText);

        String replaceText;

        String[] splitContent = paramText.split("%\\(slimecolor solid:");

        boolean findSolid = false;

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

            TextComponent result = new TextComponent(content);

            result.setColor(getColor(color));
            if (getBoolean(false, ColorExtras.BOLD)) {
                component.setBold(true);
            }

            if (getBoolean(false, ColorExtras.ITALIC)) {
                component.setItalic(true);
            }

            if (getBoolean(false, ColorExtras.MAGIC)) {
                component.setObfuscated(true);
            }

            if (getBoolean(false, ColorExtras.STRIKETHROUGH)) {
                component.setStrikethrough(true);
            }

            if (getBoolean(false, ColorExtras.UNDERLINE)) {
                component.setUnderlined(true);
            }
            findSolid = true;

            for (String split : splitContent) {
                if (split.contains(replaceText)) {

                    String[] secondSplit = split.split("\\(end-point( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

                    component.addExtra(result);

                    if (secondSplit[1].contains("%(slimecolor solid:")) {
                        processSolid(component, secondSplit[1]);
                    } else {
                        component.addExtra(
                                new TextComponent(
                                        colorize(secondSplit[1])
                                )
                        );
                    }
                    return;
                } else {
                    component.addExtra(
                            new TextComponent(
                                    colorize(split)
                            )
                    );
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
