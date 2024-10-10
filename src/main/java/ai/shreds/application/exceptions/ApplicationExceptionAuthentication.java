package ai.shreds.application.exceptions;

import ai.shreds.shared.SharedExceptionAuthentication;

public class ApplicationExceptionAuthentication extends SharedExceptionAuthentication {

    public ApplicationExceptionAuthentication(String message, int errorCode) {
        super(message, errorCode);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}