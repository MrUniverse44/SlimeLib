package dev.mruniverse.slimelib.colors;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ColorUtils {

    private static final Map<ChatColor, Color> minecraftColors = new LinkedHashMap<>();

    static {
        minecraftColors.put(ChatColor.WHITE, new Color(0xFFFFFF));
        minecraftColors.put(ChatColor.GRAY, new Color(0xAAAAAA));
        minecraftColors.put(ChatColor.DARK_GRAY, new Color(0x555555));
        minecraftColors.put(ChatColor.BLACK, new Color(0x000000));
        minecraftColors.put(ChatColor.DARK_AQUA, new Color(0x00AAAA));
        minecraftColors.put(ChatColor.AQUA, new Color(0x55FFFF));
        minecraftColors.put(ChatColor.BLUE, new Color(0x05555FF));
        minecraftColors.put(ChatColor.DARK_BLUE, new Color(0x0000AA));
        minecraftColors.put(ChatColor.GREEN, new Color(0x55FF55));
        minecraftColors.put(ChatColor.DARK_GREEN, new Color(0x00AA00));
        minecraftColors.put(ChatColor.YELLOW, new Color(0xFFFF55));
        minecraftColors.put(ChatColor.GOLD, new Color(0xFFAA00));
        minecraftColors.put(ChatColor.RED, new Color(0xFF5555));
        minecraftColors.put(ChatColor.DARK_RED, new Color(0xAA0000));
        minecraftColors.put(ChatColor.LIGHT_PURPLE, new Color(0xFF55FF));
        minecraftColors.put(ChatColor.DARK_PURPLE, new Color(0xAA00AA));
    }

    public static List<ChatColor> createGradient(long length, List<ChatColor> gradient, boolean rgb) {
        List<ChatColor> colors = new ArrayList<>();
        if (gradient.size() < 2 || length < 2) {
            if (gradient.isEmpty()) {
                return gradient;
            }
            return Collections.singletonList(gradient.get(0));
        }

        float phase = 0;

        float mathLength = (float) (length - 1) / (gradient.size() - 1);

        float mathMultiplier = 1.0F / (mathLength);

        long index = 0;

        int colorIndex = 0;

        for (long i = 0; i < length; i++) {

            if (mathMultiplier * index > 1) {
                colorIndex++;
                index = 0;
            }

            float factor = mathLength * (index++ + phase);

            if (factor > 1) {
                factor = 1 - (factor - 1);
            }

            Color color = interpolate(
                    getColor(gradient.get(colorIndex), rgb),
                    getColor(gradient.get(Math.min(gradient.size() - 1, colorIndex + 1)), rgb),
                    factor
            );

            if (color != null) {
                if (rgb) {
                    colors.add(ChatColor.of(color));
                } else {
                    ChatColor chatColor = getClosestLegacy(color);
                    if (colors.isEmpty() || chatColor != colors.get(colors.size() - 1)) {
                        colors.add(chatColor);
                    }
                }
            }
        }

        return colors;
    }

    public static List<Color> createColorGradient(long length, List<Color> gradient, boolean rgb) {
        List<Color> colors = new ArrayList<>();
        if (gradient.size() < 2 || length < 2) {
            if (gradient.isEmpty()) {
                return gradient;
            }
            return Collections.singletonList(gradient.get(0));
        }

        float phase = 0;

        float mathLength = (float) (length - 1) / (gradient.size() - 1);

        float mathMultiplier = 1.0F / (mathLength);

        long index = 0;

        int colorIndex = 0;

        for (long i = 0; i < length; i++) {

            if (mathMultiplier * index > 1) {
                colorIndex++;
                index = 0;
            }

            float factor = mathLength * (index++ + phase);

            if (factor > 1) {
                factor = 1 - (factor - 1);
            }

            Color color = interpolate(
                    gradient.get(colorIndex),
                    gradient.get(Math.min(gradient.size() - 1, colorIndex + 1)),
                    factor
            );

            if (color != null) {
                colors.add(color);
            }
        }

        return colors;
    }

    public static ChatColor getClosestLegacy(Color color) {
        ChatColor closest = null;

        double smallestDistance = Double.MAX_VALUE;

        for (Map.Entry<ChatColor, Color> legacy : minecraftColors.entrySet()) {
            double distance = distance(color, legacy.getValue());
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closest = legacy.getKey();
            }
        }

        return closest;
    }

    public static double distance(Color start, Color end) {
        if (start.getRGB() == end.getRGB()) {
            return 0;
        }
        return Math.sqrt(
                Math.pow(start.getRed() - end.getRed(), 2) +
                Math.pow(start.getGreen() - end.getGreen(), 2) +
                Math.pow(start.getBlue() - end.getBlue(), 2)
        );
    }

    private static Color interpolate(Color color1, Color color2, float factor) {
        if (color1 == null || color2 == null) {
            return null;
        }
        return new Color(
                Math.round(color1.getRed() + factor * (color2.getRed() - color1.getRed())),
                Math.round(color1.getGreen() + factor * (color2.getGreen() - color1.getGreen())),
                Math.round(color1.getBlue() + factor * (color2.getBlue() - color1.getBlue()))
        );
    }

    public static Color getColor(ChatColor color, boolean rgb) {
        if (minecraftColors.containsKey(color)) {
            return minecraftColors.get(color);
        }

        if (color.getName().startsWith("#")) {
            Color c = new Color(Integer.parseInt(color.getName().substring(1), 16));
            if (rgb) {
                return c;
            } else {
                return minecraftColors.get(getClosestLegacy(c));
            }
        } else if (rgb) {
            return color.getColor();
        }

        return null;
    }
}
