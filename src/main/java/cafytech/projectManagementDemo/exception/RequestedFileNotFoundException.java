package cafytech.projectManagementDemo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404 Not Found
public class RequestedFileNotFoundException extends RuntimeException {
    public RequestedFileNotFoundException(String message) {
        super(message);
    }

    public RequestedFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RequestedFileNotFoundException() {
        super();
    }

}