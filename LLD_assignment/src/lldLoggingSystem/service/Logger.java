package lldLoggingSystem.service;

import lldLoggingSystem.models.AbstractLogger;
import lldLoggingSystem.models.LogMessage;
import lldLoggingSystem.models.SinkType;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

import static lldLoggingSystem.service.LogManager.addObservers;
import static lldLoggingSystem.service.LogManager.doChaining;

public class Logger implements Cloneable, Serializable {
    private volatile static Logger logger;
    private volatile static AbstractLogger chainOfLogger;
    private volatile static LoggerSubject loggerSubject;

    private Logger() {
        if (logger != null)
            throw new IllegalStateException("Object already created");
    }

    public static Logger getLogger(List<SinkType> sinkTypes, int logLevel) {

        if (logger == null) {
            logger = new Logger();
            chainOfLogger = doChaining();
        }

        loggerSubject = addObservers(sinkTypes, logLevel);

        return logger;
    }

    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    @Serial
    protected Object readResolve() {
        return logger;
    }

    public void info(LogMessage logMessage) {
        createLog(1, logMessage);
    }

    public void error(LogMessage logMessage) {
        createLog(2, logMessage);
    }

    public void debug(LogMessage logMessage) {
        createLog(3, logMessage);
    }

    public void warn(LogMessage logMessage) {
        createLog(4, logMessage);
    }

    public void fatal(LogMessage logMessage) {
        createLog(5, logMessage);
    }

    private void createLog(int level, LogMessage message) {
        chainOfLogger.logMessage(level, message, loggerSubject);
    }
}
