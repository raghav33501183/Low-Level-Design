package lldLoggingSystem.utils;

import lldLoggingSystem.models.LogLevel;
import lldLoggingSystem.models.SinkType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoggerUtils {

    private static int mapToLogLevelNumber(LogLevel logLevel) {
        return switch (logLevel) {
            case INFO -> 1;
            case ERROR -> 2;
            case DEBUG -> 3;
            case WARN -> 4;
            case FATAL -> 5;
        };
    }

    public static int getLogLevelFromInput(String logLevelInput) {
        if (logLevelInput == null || logLevelInput.isBlank()) {
            throw new IllegalArgumentException("Log level should be provided");
        }

        try {
            return mapToLogLevelNumber(LogLevel.valueOf(logLevelInput.toUpperCase().trim()));
        } catch (Exception e) {
            throw new IllegalArgumentException("Log level should be a valid log level");
        }
    }

    public static List<SinkType> getSinkTypeFromInput(String sinkTypeInput) {
        if (sinkTypeInput == null || sinkTypeInput.isBlank()) {
            throw new IllegalArgumentException("Sink type should be provided");
        }

        var split = sinkTypeInput.split(",", -1);
        return Arrays.stream(split).filter(s -> s != null && !s.isBlank()).map(s -> {
            try {
                return SinkType.valueOf(s.toUpperCase().trim());
            } catch (Exception e) {
                throw new IllegalArgumentException("Log level should be a valid log level");
            }
        }).collect(Collectors.toList());
    }
}
