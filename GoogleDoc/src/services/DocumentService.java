package services;

import models.Document;
import models.DocumentContent;
import models.Permission;
import models.PermissionType;

import java.util.UUID;

public class DocumentService {
    private final DocumentRepository documentRepo;
    private final DocumentContentRepository contentRepo;
    private final PermissionRepository permissionRepo;

    public DocumentService(DocumentRepository docRepo, DocumentContentRepository contentRepo, PermissionRepository permRepo) {
        this.documentRepo = docRepo;
        this.contentRepo = contentRepo;
        this.permissionRepo = permRepo;
    }

    public void createDocument(Document doc, String content, UUID userId) {
        documentRepo.save(doc);
        DocumentContent version = new DocumentContent(UUID.randomUUID(), doc.id, 1, content, userId, true);
        contentRepo.saveVersion(version);
        permissionRepo.grantPermission(new Permission(doc.id, userId, PermissionType.EDIT));
    }

    public void saveNewVersion(UUID docId, String newContent, UUID userId) {
        DocumentContent current = contentRepo.getCurrentContent(docId).orElseThrow();
        current.isCurrent = false;
        contentRepo.saveVersion(current);

        DocumentContent newVersion = new DocumentContent(UUID.randomUUID(), docId, current.version + 1, newContent, userId, true);
        contentRepo.saveVersion(newVersion);
    }

    public boolean hasEditPermission(UUID userId, UUID docId) {
        return permissionRepo.getPermission(docId, userId)
                .map(p -> p.permissionType == PermissionType.EDIT)
                .orElse(false);
    }
}
