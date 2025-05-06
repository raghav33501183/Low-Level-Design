package lldLoggingSystem.models;

public enum LogLevel {
    DEBUG(1),
    INFO(2),
    ERROR(3),
    WARN(4),
    FATAL(5);

    private final int levelValue;

    // Constructor to set the numerical value for each log level
    LogLevel(int levelValue) {
        this.levelValue = levelValue;
    }

    // Getter method to retrieve the numerical value of the log level
    public int getLevelValue() {
        return levelValue;
    }
}
