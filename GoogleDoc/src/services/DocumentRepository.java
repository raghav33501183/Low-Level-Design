package services;

import models.Document;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentRepository {
    void save(Document document);

    Optional<Document> findById(UUID id);

    List<Document> findByUserId(UUID userId);

    void delete(UUID id);
}
