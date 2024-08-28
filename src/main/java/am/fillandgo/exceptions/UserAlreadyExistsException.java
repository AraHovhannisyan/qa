package am.fillandgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserAlreadyExistsException is an exception class that is thrown when a user with the same email already exists.
 * It extends the RuntimeException class and has an additional constructor that takes a message parameter.
 * The class is annotated with @ResponseStatus(value = HttpStatus.BAD_REQUEST) to indicate that it should return a
 * HTTP status of 400 (Bad Request) when this exception is thrown.
 * @see RuntimeException
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super(message);
    }

}
