package services;

import models.Document;

import java.util.*;

public class InMemoryDocumentRepo implements DocumentRepository {
    private final Map<UUID, Document> documents = new HashMap<>();

    public void save(Document document) {
        documents.put(document.id, document);
    }

    public Optional<Document> findById(UUID id) {
        return Optional.ofNullable(documents.get(id));
    }

    public List<Document> findByUserId(UUID userId) {
        List<Document> results = new ArrayList<>();
        for (Document d : documents.values()) {
            if (d.ownerId.equals(userId)) results.add(d);
        }
        return results;
    }

    public void delete(UUID id) {
        documents.remove(id);
    }
}
