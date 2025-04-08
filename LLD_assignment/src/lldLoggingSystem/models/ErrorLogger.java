package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(2, msg);
    }
}
