package ai.shreds.infrastructure.external_services;

import java.util.List;

public class AuthenticationServiceResponse {
    private boolean valid;
    private String userId;
    private String username;
    private List<String> roles;

    public AuthenticationServiceResponse(boolean valid, String userId, String username, List<String> roles) {
        this.valid = valid;
        this.userId = userId;
        this.username = username;
        this.roles = roles;
    }

    public boolean isValid() {
        return valid;
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}