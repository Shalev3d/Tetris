public class Logger {



    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String BLACK = "\u001B[30m";

    private Consts.LogLevel level;

    public Logger(Consts.LogLevel level) {
        this.level = level;
    }

    public void log(Consts.LogLevel logLevel, String message) {
        if (logLevel.ordinal() >= level.ordinal()) {
            System.out.println(getColorCode(logLevel) + "[" + logLevel + "] " + message + RESET);
        }
    }

    private String getColorCode(Consts.LogLevel logLevel) {
        switch (logLevel) {
            case INFO:
                return BLACK;
            case WARNING:
                return YELLOW;
            case ERROR:
                return RED;
            case DEBUG:
                return BLUE;
            default:
                return RESET;
        }
    }

    public void info(String message) {
        log(Consts.LogLevel.INFO, message);
    }

    public void warning(String message) {
        log(Consts.LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(Consts.LogLevel.ERROR, message);
    }

    public void debug(String message) {
        log(Consts.LogLevel.DEBUG, message);
    }
}
