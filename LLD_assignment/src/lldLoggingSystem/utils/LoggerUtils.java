package lldLoggingSystem.utils;

import lldLoggingSystem.models.LogLevel;
import lldLoggingSystem.models.SinkType;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LoggerUtils {

    public static int getLogLevelFromInput(String logLevelInput) {
        if (logLevelInput == null || logLevelInput.isBlank()) {
            throw new IllegalArgumentException("Log level should be provided");
        }

        try {
            return LogLevel.valueOf(logLevelInput.toUpperCase().trim()).getLevelValue();
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
