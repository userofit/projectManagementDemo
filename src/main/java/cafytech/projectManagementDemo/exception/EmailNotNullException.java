package cafytech.projectManagementDemo.exception;

public class EmailNotNullException extends RuntimeException {
    public EmailNotNullException() {
        super();
    }

    public EmailNotNullException (final String message, final Throwable cause) {
        super(message, cause);
    }

    public EmailNotNullException(final String message) {
        super(message);
    }

    public EmailNotNullException(final Throwable cause) {
        super(cause);
    }

}
