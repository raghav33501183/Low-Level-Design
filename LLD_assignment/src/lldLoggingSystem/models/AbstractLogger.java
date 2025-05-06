package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public abstract class AbstractLogger {
    int levels;
    private AbstractLogger nextLevelLogger;

    public AbstractLogger(int levels) {
        this.levels = levels;
    }

    public void setNextLevelLogger(AbstractLogger nextLevelLogger) {
        this.nextLevelLogger = nextLevelLogger;
    }

    public void logMessage(int levels, LogMessage msg, LoggerSubject loggerSubject) {
        if (this.levels == levels) {
            display(msg, loggerSubject);
        } else if (nextLevelLogger != null) {
            nextLevelLogger.logMessage(levels, msg, loggerSubject);
        }
    }

    protected abstract void display(LogMessage msg, LoggerSubject loggerSubject);
}
