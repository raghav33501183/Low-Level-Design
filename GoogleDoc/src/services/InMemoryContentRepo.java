package services;

import models.DocumentContent;

import java.util.*;

public class InMemoryContentRepo implements DocumentContentRepository {
    private final Map<UUID, List<DocumentContent>> contentMap = new HashMap<>();

    public Optional<DocumentContent> getCurrentContent(UUID documentId) {
        return contentMap.getOrDefault(documentId, new ArrayList<>()).stream()
                .filter(dc -> dc.isCurrent).findFirst();
    }

    public void saveVersion(DocumentContent content) {
        contentMap.computeIfAbsent(content.documentId, k -> new ArrayList<>()).add(content);
    }

    public List<DocumentContent> getVersionHistory(UUID documentId) {
        return contentMap.getOrDefault(documentId, new ArrayList<>());
    }
}
