package lldLoggingSystem.service;

import lldLoggingSystem.models.*;

import java.util.List;

import static lldLoggingSystem.service.LogObserverFactory.createLogObserver;

class LogManager {

    static AbstractLogger doChaining() {
        var infoLogger = new InfoLogger(1);

        var errorLogger = new ErrorLogger(2);
        infoLogger.setNextLevelLogger(errorLogger);

        var debugLogger = new DebugLogger(3);
        errorLogger.setNextLevelLogger(debugLogger);

        var warnLogger = new WarnLogger(4);
        debugLogger.setNextLevelLogger(warnLogger);

        var fatalLogger = new FatalLogger(5);
        warnLogger.setNextLevelLogger(fatalLogger);

        return infoLogger;
    }

    static LoggerSubject addObservers(List<SinkType> sinkTypes, int logLevel) {
        var loggerSubject = new LoggerSubject();
        sinkTypes.forEach(sinkType -> loggerSubject.addObserver(logLevel, createLogObserver(sinkType)));
        return loggerSubject;
    }
}

