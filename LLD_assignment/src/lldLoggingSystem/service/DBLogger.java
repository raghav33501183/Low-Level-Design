package lldLoggingSystem.service;

import lldLoggingSystem.models.LogMessage;

import java.util.concurrent.ForkJoinPool;

public class DBLogger implements LogObserver {

    private String dbHost;
    private String dbPort;

    @Override
    public void log(LogMessage message) {
        ForkJoinPool.commonPool().execute(() -> {
            this.dbHost = message.getDbHost();
            this.dbPort = message.getDbPort();

            System.out.println("[DB] Logging to database at " + dbHost + ":" + dbPort + " - " + message.formatMessage());

            /* Logic to write the logs to the database async */
        });
    }
}
