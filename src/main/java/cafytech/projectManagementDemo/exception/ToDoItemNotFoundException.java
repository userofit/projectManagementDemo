package cafytech.projectManagementDemo.exception;

public class ToDoItemNotFoundException extends RuntimeException {

    public ToDoItemNotFoundException() {
        super();
    }

    public ToDoItemNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ToDoItemNotFoundException(final String message) {
        super(message);
    }

    public ToDoItemNotFoundException(final Throwable cause) {
        super(cause);
    }

}
