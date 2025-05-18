package services;

public class ServiceFactory {
    public static DocumentService createDocumentService() {
        // inject concrete implementations or use dependency injection frameworks
        return new DocumentService(new InMemoryDocumentRepo(), new InMemoryContentRepo(), new InMemoryPermissionRepo());
    }
}
