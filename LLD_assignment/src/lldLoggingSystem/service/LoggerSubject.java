package lldLoggingSystem.service;

import lldLoggingSystem.models.LogMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoggerSubject {
    Map<Integer, List<LogObserver>> logObservers = new HashMap<>();

    void addObserver(int level, LogObserver logObserver) {
        var logObservers = this.logObservers.getOrDefault(level, new ArrayList<>());
        logObservers.add(logObserver);
        this.logObservers.put(level, logObservers);
    }

    void removeObserver(LogObserver logObserver) {
        for (var entry : logObservers.entrySet()) {
            entry.getValue().remove(logObserver);
        }
    }

    public void notifyAllObserver(int level, LogMessage message) {
        for (var logObserver : logObservers.entrySet()) {
            if (logObserver.getKey() == level) {
                logObserver.getValue().forEach(observer -> observer.log(message));
            }
        }
    }
}
