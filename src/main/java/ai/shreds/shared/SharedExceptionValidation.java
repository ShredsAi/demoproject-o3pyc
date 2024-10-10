package ai.shreds.shared;

import lombok.Getter;

@Getter
public class SharedExceptionValidation extends RuntimeException {

    private final String message;
    private final int errorCode;

    public SharedExceptionValidation(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
    }
}