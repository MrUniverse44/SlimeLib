package me.blueslime.slimelib.file.configuration;

import me.blueslime.slimelib.colors.SlimeColor;

public enum TextDecoration {
    NONE,
    STRIP_COLORS,
    LEGACY,
    SOLID,
    GRADIENT,
    ALL;

    public SlimeColor.ColorMethod toColorMethod() {
        switch (this) {
            default:
            case STRIP_COLORS:
            case LEGACY:
            case NONE:
            case ALL:
                return SlimeColor.ColorMethod.ALL;
            case SOLID:
                return SlimeColor.ColorMethod.SOLID;
            case GRADIENT:
                return SlimeColor.ColorMethod.GRADIENT;
        }
    }
}
