package ai.shreds.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SharedExceptionAuthorization extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private String message;
    private int errorCode;

    public SharedExceptionAuthorization(String message) {
        super(message);
        this.message = message;
        this.errorCode = 0; // Default error code
    }
}