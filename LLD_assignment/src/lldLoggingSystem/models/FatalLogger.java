package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class FatalLogger extends AbstractLogger {

    public FatalLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(5, msg);
    }
}
