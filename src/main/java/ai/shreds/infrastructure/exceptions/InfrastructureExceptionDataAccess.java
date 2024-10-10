package ai.shreds.infrastructure.exceptions;

public class InfrastructureExceptionDataAccess extends RuntimeException {

    public InfrastructureExceptionDataAccess(String message) {
        super(message);
    }

    public InfrastructureExceptionDataAccess(String message, Throwable cause) {
        super(message, cause);
    }

    public InfrastructureExceptionDataAccess(Throwable cause) {
        super(cause);
    }
}