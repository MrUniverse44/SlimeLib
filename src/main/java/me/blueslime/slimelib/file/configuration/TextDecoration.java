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
        return switch (this) {
            case SOLID -> SlimeColor.ColorMethod.SOLID;
            case GRADIENT -> SlimeColor.ColorMethod.GRADIENT;
            default -> SlimeColor.ColorMethod.ALL;
        };
    }
}
