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
                return new SharedUserDTO(response.getUserId(), response.getUsername(), response.getRoles());
            } else {
                throw new ApplicationExceptionAuthentication("Authentication failed. Invalid or expired token.", 401);
            }
        } catch (FeignException e) {
            throw new InfrastructureExceptionNetworkError("Network error occurred while communicating with Authentication Service.", e);
        } catch (ApplicationExceptionAuthentication e) {
            throw e; // Re-throw the specific application exception
        } catch (Exception e) {
            throw new ApplicationExceptionAuthentication("An unexpected error occurred during authentication.", 500, e);
        }
    }
}

@FeignClient(name = "authentication-service", url = "${authentication.service.url}")
interface AuthenticationServiceFeignClient {
    @GetMapping("/validate-token")
    AuthenticationServiceResponse validateToken(@RequestHeader("Authorization") String token);
}