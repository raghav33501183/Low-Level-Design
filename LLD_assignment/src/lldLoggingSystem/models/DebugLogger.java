package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class DebugLogger extends AbstractLogger {

    public DebugLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(3, msg);
    }
}
