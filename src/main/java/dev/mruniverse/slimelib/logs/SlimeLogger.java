package dev.mruniverse.slimelib.logs;

@SuppressWarnings("unused")
public class SlimeLogger {

    private SlimeLoggerProperties properties;

    private String containIdentifier = "mruniverse";

    private String hidePackage = "dev.mruniverse.slimelib.";

    private String pluginName = "SlimeLib";

    public SlimeLogger() {
        properties = new SlimeLoggerProperties();
    }

    public SlimeLogger(SlimeLoggerProperties properties) {
        this.properties = properties;
    }

    public SlimeLoggerProperties getProperties() {
        return properties;
    }

    public String getContainIdentifier() {
        return containIdentifier;
    }

    public String getHidePackage() {
        return hidePackage;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setContainIdentifier(String containIdentifier) {
        this.containIdentifier = containIdentifier;
    }

    public void setHidePackage(String hidePackage) {
        this.hidePackage = hidePackage;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }

    public void setProperties(SlimeLoggerProperties properties) {
        this.properties = properties;
    }

}
