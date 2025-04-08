package lldLoggingSystem.service;

import lldLoggingSystem.models.SinkType;

public class LogObserverFactory {
    public static LogObserver createLogObserver(SinkType sinkType) {
        switch (sinkType) {
            case CONSOLE:
                return new ConsoleLogger();

            case FILE:
                return new FileLogger();

            case DB:
                return new DBLogger();

            default:
                throw new RuntimeException("Unknown sink type: " + sinkType);
        }
    }
}
