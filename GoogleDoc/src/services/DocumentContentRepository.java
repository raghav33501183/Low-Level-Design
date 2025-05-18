package services;

import models.DocumentContent;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentContentRepository {
    Optional<DocumentContent> getCurrentContent(UUID documentId);
    void saveVersion(DocumentContent content);
    List<DocumentContent> getVersionHistory(UUID documentId);
}
