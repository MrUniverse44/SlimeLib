package dev.mruniverse.slimelib.logs;

@SuppressWarnings("unused")
public class SlimeLoggerProperties {
    private Prefixes prefixes;

    public SlimeLoggerProperties() {
        this.prefixes = new Prefixes();
    }

    public Prefixes getPrefixes() {
        return prefixes;
    }

    public void setPrefixes(Prefixes prefixes) {
        this.prefixes = prefixes;
    }

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

        public void setIssue(Issue issue) {
            this.issue = issue;
        }

        public void setWarn(Warn warn) {
            this.warn = warn;
        }

        public void setDebug(Debug debug) {
            this.debug = debug;
        }

        public void setInfo(Info info) {
            this.info = info;
        }

        public Debug getDebug() {
            return debug;
        }

        public Info getInfo() {
            return info;
        }

        public Issue getIssue() {
            return issue;
        }

        public Warn getWarn() {
            return warn;
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
                super("&8[&aSlimeLib&8] &cERROR &8| &b");
            }

            public Info(String prefix) {
                super(prefix);
            }
        }

    }
}
