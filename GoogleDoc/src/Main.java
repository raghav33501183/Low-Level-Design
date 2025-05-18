import models.Document;
import services.DocumentService;
import services.ServiceFactory;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DocumentService documentService = ServiceFactory.createDocumentService();

        UUID userId = UUID.randomUUID();
        UUID docId = UUID.randomUUID();

        Document document = new Document();
        document.id = docId;
        document.title = "My First Document";
        document.ownerId = userId;

        documentService.createDocument(document, "Initial content", userId);

        System.out.println("Document created with ID: " + docId);

        if (documentService.hasEditPermission(userId, docId)) {
            documentService.saveNewVersion(docId, "Updated content v2", userId);
            System.out.println("New version saved");
        }
    }
}