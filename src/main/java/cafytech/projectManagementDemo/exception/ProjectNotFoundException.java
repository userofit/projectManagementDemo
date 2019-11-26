package cafytech.projectManagementDemo.exception;

public class ProjectNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */

    public ProjectNotFoundException() {
        super();
    }

    public ProjectNotFoundException (final String message, final Throwable cause) {
        super(message, cause);
    }

    public ProjectNotFoundException(final String message) {
        super(message);
    }

    public ProjectNotFoundException(final Throwable cause) {
        super(cause);
    }

}
