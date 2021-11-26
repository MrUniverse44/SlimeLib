package dev.mruniverse.guardianstorageapi.logs;

import dev.mruniverse.guardianstorageapi.interfaces.GLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class SpigotLogs implements GLogger {
    private final String hidePackage;
    private String pluginName = "GuardianStorageAPI";
    private String containIdentifier = "mruniverse";

    private String errorPrefix = "&f[&cERROR &7| &f%plugin%] ";

    private String debugPrefix = "&f[&9DEBUG &7| &f%plugin%] ";

    private String infoPrefix = "&f[&bINFO &7| &f%plugin%] ";

    private String warnPrefix = "&f[&eWARN &7| &f%plugin%] ";

    /**
     * Call the External Logger
     *
     * @param pluginName this is the name of your plugin.
     * @param hidePackage hide package example: dev.mruniverse.guardianrftb.
     */
    public SpigotLogs(String pluginName,String hidePackage) {
        this.hidePackage = hidePackage;
        if(pluginName != null) this.pluginName = pluginName;
    }

    /**
     * Call the External Logger
     *
     * @param pluginName this is the name of your plugin.
     * @param hidePackage hide package example: dev.mruniverse.guardianrftb.
     * @param containIdentifier when a package contain this word this package will show in Internal - StackTrace
     */
    public SpigotLogs(String pluginName,String hidePackage, String containIdentifier) {
        this.hidePackage = hidePackage;
        if(pluginName != null) this.pluginName = pluginName;
        if(containIdentifier == null) return;
        this.containIdentifier = containIdentifier;
    }

    public GLogger setErrorPrefix(String errorPrefix) {
        this.errorPrefix = errorPrefix;
        return this;
    }

    public GLogger setDebugPrefix(String debugPrefix) {
        this.debugPrefix = debugPrefix;
        return this;
    }

    public GLogger setInfoPrefix(String infoPrefix) {
        this.infoPrefix = infoPrefix;
        return this;
    }

    public GLogger setWarnPrefix(String warnPrefix) {
        this.warnPrefix = warnPrefix;
        return this;
    }
    
    /**
     * Colorize a string provided to method
     *
     * @param message Message to transform.
     * @return transformed message with colors.
     */
    public String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    /**
     * Send an error message to console.
     * @param message message to send.
     */
    public void error(String message) {
        String pr = errorPrefix.replace("%plugin%",pluginName);
        sendMessage(pr + "" + message);
    }
    /**
     * Send an error message to console.
     * @param throwable throwable to send.
     */
    public void error(Throwable throwable) {
        String location = throwable.getClass().getName();
        String error = throwable.getClass().getSimpleName();
        String pr = errorPrefix.replace("%plugin%",pluginName);
        sendMessage(pr + "-------------------------");
        sendMessage(pr + "&aLocation: " + location.replace("." + error,""));
        sendMessage(pr + "&aError: " + error);
        if(throwable.getStackTrace() != null) {
            sendMessage(pr + "&6Internal - StackTrace: ");
            List<StackTraceElement> other = new ArrayList<>();
            for(StackTraceElement line : throwable.getStackTrace()) {
                if(line.toString().contains(containIdentifier)) {
                    sendMessage(pr + "&b(Line: " + line.getLineNumber() + ") " + line.toString().replace("(" + line.getFileName() + ":" + line.getLineNumber() + ")","").replace(hidePackage,""));
                } else {
                    other.add(line);
                }
            }
            sendMessage(pr + " -------------------------");
            sendMessage(pr + "&6External - StackTrace: ");
            for(StackTraceElement line : other) {
                sendMessage(pr + "&b(Line: " + line.getLineNumber() + ") (Class: " + line.getFileName() + ") (Method: " + line.getMethodName() + ")".replace(".java",""));
            }

        }
        sendMessage(pr + " -------------------------");
    }

    /**
     * Send an advice message to console.
     * @param message message to send.
     */
    public void warn(String message) {
        String pr = warnPrefix.replace("%plugin%",pluginName);
        sendMessage(pr + message);
    }

    /**
     * Send a debug message to console.
     * @param message message to send.
     */
    public void debug(String message) {
        String pr = debugPrefix.replace("%plugin%",pluginName);
        sendMessage(pr + message);
    }

    /**
     * Send an info message to console.
     * @param message message to send.
     */
    public void info(String message) {
        String pr = infoPrefix.replace("%plugin%",pluginName);
        sendMessage(pr + message);
    }

    /**
     * Sends a message to a Bukkit command sender.
     *
     * @param sender Bukkit CommandSender
     * @param message Message to send.
     */
    public void sendMessage(CommandSender sender, String message) {
        sender.sendMessage(color(message));
    }


    /**
     * Used to other methods and prevent this copy pasta
     * to those methods.
     *
     * @param message Provided message
     */
    public void sendMessage(String message) {
        Bukkit.getServer().getConsoleSender().sendMessage(color(message));
    }
}
