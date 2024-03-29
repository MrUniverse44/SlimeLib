package me.blueslime.slimelib.colors;

import java.util.regex.Pattern;

@SuppressWarnings({"unused", "UnusedReturnValue"})
public abstract class SlimeText<T> {

    public final static Pattern GRADIENT_PATTERN = Pattern.compile("%\\(slimecolor start:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point:([\\dA-Fa-f]{6})( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

    public final static Pattern SOLID_PATTERN = Pattern.compile("%\\(slimecolor solid:([\\dA-Fa-f]{6})\\)(.*?)\\(end-point( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?( add:.+?)?\\)%");

    private boolean addGradient;

    private boolean addSolid;

    private String content;

    private T result;

    public SlimeText(String content, boolean hasHexSupport, SlimeColor.ColorMethod method) {
        this.content = content;
        if (hasHexSupport && method == SlimeColor.ColorMethod.ALL) {
            applyGradient();
            applySolid();
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

    public void setSolid(boolean addSolid) {
        this.addSolid = addSolid;
    }

    private void check(SlimeColor.ColorMethod method) {
        switch (method) {
            case SOLID:
                applySolid();
                break;
            case ALL:
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

    public SlimeText<T> applyGradient() {
        addGradient = true;
        return this;
    }

    public SlimeText<T> applySolid() {
        addSolid = true;
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
