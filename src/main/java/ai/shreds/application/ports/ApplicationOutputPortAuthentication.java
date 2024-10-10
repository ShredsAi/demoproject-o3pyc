package ai.shreds.application.ports;

import ai.shreds.shared.SharedUserDTO;

public interface ApplicationOutputPortAuthentication {
    SharedUserDTO validateAuthToken(String token);
}