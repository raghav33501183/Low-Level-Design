package lldLoggingSystem.models;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class LogMessage {
    private String content;
    private LogLevel level;
    private String namespace = "Main method"; // hard-coded for current use case
    private String timestamp;
    private String filePath;
    private String dbHost;
    private String dbPort;
    private String trackingId;
    private String hostName;

    public LogMessage(String content, LogLevel level, String filePath, String dbHost, String dbPort) {
        this.content = content;
        this.level = level;
        this.filePath = filePath;

        this.dbHost = dbHost;
        this.dbPort = dbPort;
        this.trackingId = UUID.randomUUID().toString();

        try {
            this.hostName = InetAddress.getLocalHost().getHostName();
        } catch (Exception e) {
            this.hostName = "UnknownHost";
        }
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public String getDbPort() {
        return dbPort;
    }

    public void setDbPort(String dbPort) {
        this.dbPort = dbPort;
    }

    public String getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(String trackingId) {
        this.trackingId = trackingId;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String formatMessage() {
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        var timestamp = dateFormat.format(new Date());
        return String.format("%s :: [%s] :: %s :: %s :: %s", this.level, timestamp, this.trackingId, this.hostName, this.content);
    }
}
