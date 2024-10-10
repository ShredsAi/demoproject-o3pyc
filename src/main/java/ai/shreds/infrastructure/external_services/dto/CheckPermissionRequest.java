package ai.shreds.infrastructure.external_services.dto;

public class CheckPermissionRequest {
    private final String userId;
    private final String permission;

    public CheckPermissionRequest(String userId, String permission) {
        this.userId = userId;
        this.permission = permission;
    }

    public String getUserId() {
        return userId;
    }

    public String getPermission() {
        return permission;
    }
}