package models;

import java.util.UUID;

public class DocumentContent {
    public UUID id;
    public UUID documentId;
    public int version;
    public String content;
    public UUID createdBy;
    public boolean isCurrent;

    // Constructor, Getters & Setters
    public DocumentContent(UUID id, UUID documentId, int version, String content, UUID createdBy, boolean isCurrent) {
        this.id = id;
        this.documentId = documentId;
        this.version = version;
        this.content = content;
        this.createdBy = createdBy;
        this.isCurrent = isCurrent;
    }
}
