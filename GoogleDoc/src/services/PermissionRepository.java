package services;

import models.Permission;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository {
    Optional<Permission> getPermission(UUID documentId, UUID userId);

    void grantPermission(Permission permission);

    void revokePermission(UUID documentId, UUID userId);
}
