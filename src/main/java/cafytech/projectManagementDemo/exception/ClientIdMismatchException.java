package cafytech.projectManagementDemo.exception;


//later, may change to ClientAlreadyExistException
public class ClientIdMismatchException extends RuntimeException  {

    public ClientIdMismatchException() {
        super();
    }

    public ClientIdMismatchException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ClientIdMismatchException(final String message) {
        super(message);
    }

    public ClientIdMismatchException(final Throwable cause) {
        super(cause);
    }


}
