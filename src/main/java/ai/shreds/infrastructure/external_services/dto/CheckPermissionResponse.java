package ai.shreds.infrastructure.external_services.dto;

public class CheckPermissionResponse {
    private final boolean authorized;

    public CheckPermissionResponse(boolean authorized) {
        this.authorized = authorized;
    }

    public boolean isAuthorized() {
        return authorized;
    }
}