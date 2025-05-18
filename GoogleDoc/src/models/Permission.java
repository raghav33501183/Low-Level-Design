package models;

import java.util.UUID;

public class Permission {
    public UUID documentId;
    public UUID userId;
    public PermissionType permissionType;

    public Permission(UUID documentId, UUID userId, PermissionType permissionType) {
        this.documentId = documentId;
        this.userId = userId;
        this.permissionType = permissionType;
    }
}
