package me.blueslime.slimelib.colors;

import java.awt.*;
import java.util.*;
import java.util.List;

public class ColorUtils {
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
     * @param colors color list need to be == 2
     * @return the ChatColor list
     */
    public static List<Color> createColorGradientHSV(int length, List<Color> colors) {

        if (colors == null|| colors.size() < 2) {
            return null;
        }

        Color from = colors.get(0);
        Color to = colors.get(1);

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

        final List<Color> colorList = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            colorList.add(
                    Color.getHSBColor(
                            (float) h[i], (float) s[i], (float) v[i]
                    )
            );
        }

        return colorList;
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
}