package am.fillandgo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ContractAlreadyExistsException extends RuntimeException {

    public ContractAlreadyExistsException(String message, String mobileNumber) {
        super(message);
    }
}


