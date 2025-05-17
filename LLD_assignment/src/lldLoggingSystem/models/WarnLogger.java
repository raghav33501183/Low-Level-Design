package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class WarnLogger extends AbstractLogger {

    public WarnLogger(int levels) {
        super(levels);
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(4,  msg);
    }
}
