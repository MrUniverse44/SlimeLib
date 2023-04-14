package me.blueslime.slimelib.colors;

import net.md_5.bungee.api.ChatColor;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ChatColorUtils {

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

    public static double[] interpolate(double from, double to, int max) {
        final double[] results = new double[max];
        for (int i = 0; i < max; i++) {
            results[i] = from + i * ((to - from) / (max - 1));
        }
        return results;
    }

    /**
     * Create a Gradient with HSV in ChatColor
     * @param length of the text
     * @param chatColorList color list need to be == 2
     * @return the ChatColor list
     */
    public static List<ChatColor> createGradientHSV(int length, List<ChatColor> chatColorList) {

        if (chatColorList == null|| chatColorList.size() < 2) {
            return null;
        }

        Color from = chatColorList.get(0).getColor();
        Color to = chatColorList.get(1).getColor();

        final float[] hsvFrom = Color.RGBtoHSB(
                from.getRed(),
                from.getGreen(),
                from.getBlue(),
                null
        );

        final float[] hsvTo = Color.RGBtoHSB(
                to.getRed(),
                to.getGreen(),
                to.getBlue(),
                null
        );

        final double[] h = interpolate(hsvFrom[0], hsvTo[0], length);
        final double[] s = interpolate(hsvFrom[1], hsvTo[1], length);
        final double[] v = interpolate(hsvFrom[2], hsvTo[2], length);

        final List<ChatColor> colorList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            colorList.add(
                    ChatColor.of(
                            Color.getHSBColor(
                                    (float) h[i], (float) s[i], (float) v[i]
                            )

                    )
            );
        }

        return colorList;
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
