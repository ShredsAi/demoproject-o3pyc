package ai.shreds.infrastructure.external_services;

import ai.shreds.application.ports.ApplicationOutputPortAuthentication;
import ai.shreds.shared.SharedUserDTO;
import ai.shreds.application.exceptions.ApplicationExceptionAuthentication;
import ai.shreds.infrastructure.exceptions.InfrastructureExceptionNetworkError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import feign.FeignException;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
public class InfrastructureClientAuthentication implements ApplicationOutputPortAuthentication {

    @Autowired
    private AuthenticationServiceFeignClient authenticationServiceFeignClient;

    @Override
    public SharedUserDTO validateAuthToken(String token) {
        if (token == null || token.isEmpty()) {
            throw new ApplicationExceptionAuthentication("Authentication token is missing.", 401);
        }
        try {
            AuthenticationServiceResponse response = authenticationServiceFeignClient.validateToken(token);
            if (response.isValid()) {
                SharedUserDTO userDTO = new SharedUserDTO();
                userDTO.setUserId(response.getUserId());
                userDTO.setUsername(response.getUsername());
                userDTO.setRoles(response.getRoles());
                return userDTO;
            } else {
                throw new ApplicationExceptionAuthentication("Authentication failed. Invalid or expired token.", 401);
            }
        } catch (FeignException e) {
            throw new InfrastructureExceptionNetworkError("Network error occurred while communicating with Authentication Service.");
        } catch (Exception e) {
            throw new ApplicationExceptionAuthentication("Authentication failed. Invalid or expired token.", 401);
        }
    }
}

@FeignClient(name = "authentication-service", url = "${authentication.service.url}")
interface AuthenticationServiceFeignClient {
    @GetMapping("/validate-token")
    AuthenticationServiceResponse validateToken(@RequestHeader("Authorization") String token);
}

@Data
@AllArgsConstructor
public class AuthenticationServiceResponse {
    private boolean valid;
    private String userId;
    private String username;
    private List<String> roles;
}