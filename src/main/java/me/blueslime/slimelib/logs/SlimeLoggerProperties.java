package me.blueslime.slimelib.logs;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("unused")
public class SlimeLoggerProperties {
    private Prefixes prefixes;
    private ExceptionProperties exceptionProperties;

    public SlimeLoggerProperties() {
        this.prefixes = new Prefixes();
        this.exceptionProperties = new ExceptionProperties();
    }

    public static class ExceptionProperties {
        public String BASE_COLOR = "&b";
        public String CODE_COLOR = "&b";
    }

    @Setter
    @Getter
    public static class Prefixes {
        private Issue issue;
        private Warn warn;
        private Debug debug;
        private Info info;

        public Prefixes() {
            issue = new Issue();
            warn = new Warn();
            debug = new Debug();
            info = new Info();
        }

        /**
         * Change the "SlimeLib" from prefixes
         * @param newName the new text
         */
        public void changeMainText(String newName) {
            issue.setPrefix(issue.getPrefix().replace("SlimeLib", newName));
            warn.setPrefix(warn.getPrefix().replace("SlimeLib", newName));
            debug.setPrefix(debug.getPrefix().replace("SlimeLib", newName));
            info.setPrefix(info.getPrefix().replace("SlimeLib", newName));
        }

        /**
         * Change the "SlimeLib" from prefixes
         * @param oldName is the current name
         * @param newName the new text
         */
        public void changeMainText(String oldName, String newName) {
            issue.setPrefix(issue.getPrefix().replace(oldName, newName));
            warn.setPrefix(warn.getPrefix().replace(oldName, newName));
            debug.setPrefix(debug.getPrefix().replace(oldName, newName));
            info.setPrefix(info.getPrefix().replace(oldName, newName));
        }

        public static class Issue extends SlimePrefix {

            public Issue() {
                super("&8[&aSlimeLib&8] &cERROR &8| &b");
            }

            public Issue(String prefix) {
                super(prefix);
            }
        }

        public static class Warn extends SlimePrefix {

            public Warn() {
                super("&8[&aSlimeLib&8] &6WARN &8| &e");
            }

            public Warn(String prefix) {
                super(prefix);
            }
        }

        public static class Debug extends SlimePrefix {

            public Debug() {
                super("&8[&aSlimeLib&8] &9DEBUG &8| &3");
            }

            public Debug(String prefix) {
                super(prefix);
            }
        }

        public static class Info extends SlimePrefix {

            public Info() {
                super("&8[&aSlimeLib&8] &bINFO &8| &b");
            }

            public Info(String prefix) {
                super(prefix);
            }
        }

    }
}
