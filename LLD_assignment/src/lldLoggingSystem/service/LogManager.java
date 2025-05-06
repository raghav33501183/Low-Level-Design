package lldLoggingSystem.service;

import lldLoggingSystem.models.*;

import java.util.List;

import static lldLoggingSystem.service.LogObserverFactory.createLogObserver;

class LogManager {

    static AbstractLogger doChaining() {
        var debugLogger = new DebugLogger(1);

        var infoLogger = new InfoLogger(2);
        debugLogger.setNextLevelLogger(infoLogger);

        var errorLogger = new ErrorLogger(3);
        infoLogger.setNextLevelLogger(errorLogger);

        var warnLogger = new WarnLogger(4);
        errorLogger.setNextLevelLogger(warnLogger);

        var fatalLogger = new FatalLogger(5);
        warnLogger.setNextLevelLogger(fatalLogger);

        return debugLogger;
    }

    static LoggerSubject addObservers(List<SinkType> sinkTypes, int logLevel) {
        var loggerSubject = new LoggerSubject();
        sinkTypes.forEach(sinkType -> loggerSubject.addObserver(logLevel, LogObserverFactory.createLogObserver(sinkType)));
        return loggerSubject;
    }
}

