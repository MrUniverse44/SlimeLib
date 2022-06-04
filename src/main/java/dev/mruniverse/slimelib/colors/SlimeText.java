package dev.mruniverse.slimelib.colors;

import java.util.regex.Pattern;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class SlimeText<T> {

    public final static Pattern GRADIENT_PATTERN = Pattern.compile("%\\(slimecolor start:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point:([\\dA-Fa-f]{6})\\)%");

    public final static Pattern SOLID_PATTERN = Pattern.compile("%\\(slimecolor solid:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point\\)%");

    private boolean addGradient;

    private boolean addLegacy;

    private boolean addSolid;

    private String content;

    private T result;

    public SlimeText(String content, boolean hasHexSupport, SlimeColor.ColorMethod method) {
        this.content = content;
        if (hasHexSupport && method == SlimeColor.ColorMethod.ALL) {
            applyGradient();
            applySolid();
            applyLegacy();
            return;
        }
        check(method);
    }

    public SlimeText(String content, boolean hasHexSupport) {
        this.content = content;
        if (hasHexSupport) {
            applyGradient();
            applySolid();
        }
        applyLegacy();
    }

    public SlimeText(String content) {
        this.content = content;
    }

    public SlimeText() {
        this("There is not content here");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGradient(boolean addGradient) {
        this.addGradient = addGradient;
    }

    public void setLegacy(boolean addLegacy) {
        this.addLegacy = addLegacy;
    }

    public void setSolid(boolean addSolid) {
        this.addSolid = addSolid;
    }

    private void check(SlimeColor.ColorMethod method) {
        switch (method) {
            case SOLID:
                applySolid();
                break;
            case ALL:
            case LEGACY:
                applyLegacy();
                break;
            case GRADIENT:
                applyGradient();
        }
    }

    public boolean hasGradient() {
        return addGradient;
    }

    public boolean hasSolid() {
        return addSolid;
    }

    public boolean hasLegacy() {
        return addLegacy;
    }

    public SlimeText<T> applyGradient() {
        addGradient = true;
        return this;
    }

    public SlimeText<T> applySolid() {
        addSolid = true;
        return this;
    }

    public SlimeText<T> applyLegacy() {
        addLegacy = true;
        return this;
    }

    public String getContent() {
        return content;
    }

    public T build() {
        return result;
    }

    public T empty() {
        return null;
    }

    @Override
    public String toString() {
        return "SlimeText{" +
                "content='" + content + '\'' +
                ", result=" + result +
                '}';
    }
}
