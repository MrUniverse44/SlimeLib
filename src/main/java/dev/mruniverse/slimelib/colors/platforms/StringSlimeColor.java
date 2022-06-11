package dev.mruniverse.slimelib.colors.platforms;

import dev.mruniverse.slimelib.colors.ChatColorUtils;
import dev.mruniverse.slimelib.colors.SlimeColor;
import dev.mruniverse.slimelib.colors.SlimeText;
import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

@SuppressWarnings("unused")
public class StringSlimeColor extends SlimeText<String> {

    public StringSlimeColor(String content, boolean hasHexSupport, SlimeColor.ColorMethod method) {
        super(content, hasHexSupport, method);
    }

    public StringSlimeColor(String content, boolean hasHexSupport) {
        super(content, hasHexSupport);
    }

    public StringSlimeColor(String content) {
        super(content);
    }

    public StringSlimeColor() {
        super();
    }

    @Override
    public String build() {
        if (!hasGradient() && !hasSolid()) {
            return ChatColor.translateAlternateColorCodes('&', getContent());
        }

        if (hasGradient()) {

            String result;

            if (!hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String finalResult = getContent();

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    finalResult = finalResult.replace(
                            gradientMatcher.group(),
                            color(
                                    content,
                                    new Color(Integer.parseInt(start, 16)),
                                    new Color(Integer.parseInt(end, 16))
                            )
                    );
                }
                return ChatColor.translateAlternateColorCodes('&', finalResult);
            }

            if (hasSolid()) {
                result = getContent();

                Matcher gradientMatcher = GRADIENT_PATTERN.matcher(result);

                String finalResult = getContent();

                while (gradientMatcher.find()) {
                    String content = gradientMatcher.group(2);
                    String start   = gradientMatcher.group(1);
                    String end     = gradientMatcher.group(3);

                    finalResult = finalResult.replace(
                            gradientMatcher.group(),
                            color(
                                    content,
                                    new Color(Integer.parseInt(start, 16)),
                                    new Color(Integer.parseInt(end, 16))
                            )
                    );
                }

                Matcher solidMatcher = SOLID_PATTERN.matcher(finalResult);

                while (solidMatcher.find()) {
                    String color = solidMatcher.group(1);

                    Color colorized = new Color(Integer.parseInt(color, 16));

                    finalResult = finalResult.replace(
                            solidMatcher.group(),
                            ChatColorUtils.getColor(
                                    ChatColor.of(colorized),
                                    true
                            ) + ""
                    );
                }

                return ChatColor.translateAlternateColorCodes('&', finalResult);
            }
        }

        if (hasSolid()) {
            String finalResult = getContent();

            Matcher solidMatcher = SOLID_PATTERN.matcher(finalResult);

            while (solidMatcher.find()) {
                String color = solidMatcher.group(1);

                Color colorized = new Color(Integer.parseInt(color, 16));

                finalResult = finalResult.replace(
                        solidMatcher.group(),
                        ChatColorUtils.getColor(
                                ChatColor.of(colorized),
                                true
                        ) + ""
                );
            }
            return ChatColor.translateAlternateColorCodes('&', finalResult);
        }

        return getContent();
    }

    private String color(String content,Color... color) {
        List<ChatColor> colorList = new ArrayList<>();
        for (Color color1 : color) {
            colorList.add(ChatColor.of(color1));
        }

        StringBuilder builder = new StringBuilder();

        String[] characters = content.split("");

        ChatColor[] colors = colorList.toArray(new ChatColor[0]);

        int id = 0;

        for (String character : characters) {
            builder.append(colors[id++])
                    .append(character);
        }
        return builder.toString();
    }

    @Override
    public String empty() {
        return "";
    }

}
