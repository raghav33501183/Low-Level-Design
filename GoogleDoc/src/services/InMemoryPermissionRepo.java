package services;

import models.Permission;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryPermissionRepo implements PermissionRepository {
    private final Map<String, Permission> permissions = new HashMap<>();

    public Optional<Permission> getPermission(UUID documentId, UUID userId) {
        return Optional.ofNullable(permissions.get(key(documentId, userId)));
    }

    public void grantPermission(Permission permission) {
        permissions.put(key(permission.documentId, permission.userId), permission);
    }

    public void revokePermission(UUID documentId, UUID userId) {
        permissions.remove(key(documentId, userId));
    }

    private String key(UUID docId, UUID userId) {
        return docId.toString() + ":" + userId.toString();
    }
}
