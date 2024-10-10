package ai.shreds.application.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ai.shreds.application.exceptions.ApplicationExceptionAuthentication;
import ai.shreds.application.ports.ApplicationOutputPortAuthentication;
import ai.shreds.shared.SharedUserDTO;
import ai.shreds.infrastructure.external_services.InfrastructureClientAuthentication;

@Service
public class ApplicationServiceAuthentication implements ApplicationOutputPortAuthentication {

    private final InfrastructureClientAuthentication infrastructureClientAuthentication;

    @Autowired
    public ApplicationServiceAuthentication(InfrastructureClientAuthentication infrastructureClientAuthentication) {
        this.infrastructureClientAuthentication = infrastructureClientAuthentication;
    }

    @Override
    public SharedUserDTO validateAuthToken(String token) {
        try {
            SharedUserDTO user = infrastructureClientAuthentication.validateAuthToken(token);

            if (user == null) {
                throw new ApplicationExceptionAuthentication("Authentication failed. Invalid or expired token.", 401);
            }

            return user;
        } catch (ApplicationExceptionAuthentication e) {
            throw e;
        } catch (Exception e) {
            throw new ApplicationExceptionAuthentication("Authentication service error.", 500, e);
        }
    }
}