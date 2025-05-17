package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class ErrorLogger extends AbstractLogger {

    public ErrorLogger(int levels) {
        super(levels);
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(3, msg);
    }
}
