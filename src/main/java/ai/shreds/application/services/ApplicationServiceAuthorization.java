package ai.shreds.application.services;

import ai.shreds.application.ports.ApplicationOutputPortAuthorization;
import ai.shreds.application.exceptions.ApplicationExceptionAuthorization;
import ai.shreds.infrastructure.external_services.AuthorizationServiceClient;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ApplicationServiceAuthorization implements ApplicationOutputPortAuthorization {

    private final AuthorizationServiceClient authorizationServiceClient;

    @Autowired
    public ApplicationServiceAuthorization(AuthorizationServiceClient authorizationServiceClient) {
        this.authorizationServiceClient = authorizationServiceClient;
    }

    @Override
    public Boolean checkUploadPermission(String userId) {
        try {
            return authorizationServiceClient.checkUploadPermission(userId);
        } catch (Exception e) {
            throw new ApplicationExceptionAuthorization("Authorization failed", e);
        }
    }
}