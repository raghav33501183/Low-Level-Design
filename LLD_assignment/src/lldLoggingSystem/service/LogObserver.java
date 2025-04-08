package lldLoggingSystem.service;

import lldLoggingSystem.models.LogMessage;

public interface LogObserver {
    default void log(LogMessage message) {
        System.out.println(String.join(" | ", message.getLevel().name(), message.getContent()));
    }
}
