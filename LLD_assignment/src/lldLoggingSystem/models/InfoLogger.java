package lldLoggingSystem.models;

import lldLoggingSystem.service.LoggerSubject;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int levels) {
        this.levels = levels;
    }

    @Override
    protected void display(LogMessage msg, LoggerSubject loggerSubject) {
        loggerSubject.notifyAllObserver(1, msg);
    }
}
