package me.blueslime.slimelib.colors.platforms;

import me.blueslime.slimelib.colors.SlimeText;

public class StringComponent {

    private final SlimeText<String> slimeText;

    @SuppressWarnings("unchecked")
    public StringComponent(SlimeText<?> slimeText) {
        this.slimeText = (SlimeText<String>) slimeText;
    }

    public SlimeText<String> getSlimeText() {
        return slimeText;
    }

    public String build() {
        return slimeText.build();
    }

}
