package am.fillandgo.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * ResponseDto is a data transfer object class that represents a response.
 * It contains information about the status code and status message.
 */
@Data
@AllArgsConstructor
@Builder
public class ResponseDto {

    private HttpStatus statusCode;

    private String statusMessage;

}
