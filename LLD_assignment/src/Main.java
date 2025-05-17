import lldLoggingSystem.models.LogLevel;
import lldLoggingSystem.models.LogMessage;
import lldLoggingSystem.models.SinkType;
import lldLoggingSystem.service.Logger;

import java.util.Scanner;

import static lldLoggingSystem.utils.LoggerUtils.getLogLevelFromInput;
import static lldLoggingSystem.utils.LoggerUtils.getSinkTypeFromInput;

public class Main {
    public static void main(String[] args) {
        var sc = new Scanner(System.in);
        System.out.print("Enter the log level: ");
        var logLevelInput = sc.next();
        sc.nextLine();

        var logLevelNum = getLogLevelFromInput(logLevelInput);
        var logLevel = LogLevel.valueOf(logLevelInput.toUpperCase());

        System.out.print("Enter the sink type comma separated: ");
        var sinkTos = getSinkTypeFromInput(sc.nextLine());

        String filePath = null;
        if (sinkTos.contains(SinkType.FILE)) {
            System.out.print("Enter the file path: ");
            filePath = sc.next();
            sc.nextLine();
        }

        String dbHost = null;
        String dbPort = null;

        if (sinkTos.contains(SinkType.DB)) {
            System.out.print("Enter the DB host name: ");
            dbHost = sc.next();
            sc.nextLine();

            System.out.print("Enter the DB port name: ");
            dbPort = sc.next();
            sc.nextLine();
        }

        System.out.print("Enter the logging content: ");
        var text = sc.nextLine();

        var logMessage = new LogMessage(text, logLevel, filePath, dbHost, dbPort);
        var logger = Logger.getLogger(sinkTos, logLevel.getLevelValue());
        logger.createLog(logLevel.getLevelValue(), logMessage);
    }
}