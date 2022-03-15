package dev.mruniverse.slimelib.logs;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public abstract class SlimeLogs {

    private SlimeLogger logger;
    private SlimeLoggerProperties properties;
    private SlimeLoggerProperties.Prefixes prefixes;

    public SlimeLogs() {
        this(new SlimeLogger());
    }

    public SlimeLogs(SlimeLogger logger) {
        this.logger     = logger;
        this.properties = logger.getProperties();
        this.prefixes   = logger.getProperties().getPrefixes();
    }

    public void setSlimeLogger(SlimeLogger slimeLogger) {
        prefixes   = slimeLogger.getProperties().getPrefixes();
        properties = slimeLogger.getProperties();
        logger     = slimeLogger;
    }

    public SlimeLogger getSlimeLogger() {
        return logger;
    }

    public SlimeLoggerProperties.Prefixes getPrefixes() {
        return prefixes;
    }

    public SlimeLoggerProperties getProperties() {
        return properties;
    }

    public void error(String message) {
        send(prefixes.getIssue().getPrefix() + message);
    }

    public void error(Exception exception) {

        Class<?> current = exception.getClass();

        String location = current.getName();
        String error = current.getSimpleName();

        error("&b -------------------------");
        error("&bLocation: " + location.replace("." + error,""));
        error("&bError: " + error);

        if (exception.getStackTrace() != null) {

            error("&bInternal - StackTrace: ");

            List<StackTraceElement> other = new ArrayList<>();

            for (StackTraceElement line : exception.getStackTrace()) {

                int number = line.getLineNumber();

                String text = line.toString();

                String replace = "(" + line.getFileName() + ":" + number + ")";

                if (text.contains(logger.getContainIdentifier())) {
                    error("&b (Line: " + number + ") " + text.replace(replace,"").replace(logger.getHidePackage(),""));
                } else {
                    other.add(line);
                }
            }

            error("&b -------------------------");
            error("&bExternal - StackTrace: ");

            for (StackTraceElement line : other) {
                error("&b (Line: " + line.getLineNumber() + ") (Class: " + line.getFileName() + ") (Method: " + line.getMethodName() + ")".replace(".java",""));
            }
        }

        error("&b -------------------------");
    }

    public void error(String message, Exception exception) {
        error(message);
        error(exception);
    }

    public void warn(String message) {
        send(prefixes.getWarn().getPrefix() + message);
    }

    public void debug(String message) {
        send(prefixes.getDebug().getPrefix() + message);
    }

    public void info(String message) {
        send(prefixes.getInfo().getPrefix() + message);
    }

    public void send(String message) {
        // This need to be setted by the user
    }
}
