package ai.shreds.infrastructure.external_services;

public class AuthorizationServiceClient {

    /**
     * Simulates checking if a user has permission to upload media.
     *
     * @param userId the identifier of the user whose permissions are being checked
     * @return true if the user has upload permission, false otherwise
     */
    public Boolean checkUploadPermission(String userId) {
        // Simulate permission check logic
        // For demonstration purposes, assume all users have permission
        return true;
    }
}