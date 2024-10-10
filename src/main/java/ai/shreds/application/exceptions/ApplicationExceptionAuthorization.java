package ai.shreds.application.exceptions;

import ai.shreds.shared.SharedExceptionAuthorization;

public class ApplicationExceptionAuthorization extends SharedExceptionAuthorization {

    private static final long serialVersionUID = 1L;

    public ApplicationExceptionAuthorization(String message, int errorCode) {
        super(message, errorCode);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}