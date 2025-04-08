package lldLoggingSystem.service;

import lldLoggingSystem.models.LogMessage;

import java.io.*;
import java.util.Objects;

public class FileLogger implements LogObserver {

    private static final String DEFAULT_FILE_NAME = "test.txt";

    @Override
    public synchronized void log(LogMessage message) {
        System.out.println("Writing to File");

        var filePath = Objects.requireNonNullElse(message.getFilePath(), DEFAULT_FILE_NAME);
        rotateLog(filePath);

        try (var fw = new FileWriter(filePath, true);
             var bw = new BufferedWriter(fw)) {
            bw.write(message.formatMessage());
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rotateLog(String filePath) {
        try {
            var logFile = new File(filePath);
            if (logFile.exists()) {
                for (int i = 2; i > 0; i--) {
                    var oldFile = new File(filePath + "." + i + ".gz");
                    var newFile = new File(filePath + "." + (i + 1) + ".gz");

                    if (oldFile.exists()) {
                        var ignored = oldFile.renameTo(newFile);
                    }
                }

                var ignored = logFile.renameTo(new File(filePath + ".1.gz"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
