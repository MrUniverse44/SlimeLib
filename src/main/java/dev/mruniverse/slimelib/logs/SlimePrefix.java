package dev.mruniverse.slimelib.logs;

@SuppressWarnings("unused")
public abstract class SlimePrefix {
    private String prefix;

    public SlimePrefix() {
        this("&8[&aSlimeLib&8] &dUnknown &8| &e");
    }

    public SlimePrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
