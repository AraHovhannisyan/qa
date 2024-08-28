package am.fillandgo.dto.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * ErrorResponseDto is a data transfer object class that represents an error response.
 * It contains information about the error such as API path, HTTP status code, error message, and error timestamp.
 */
@Data
@AllArgsConstructor
@Builder
@Schema(name = "Error", description = "Error object")
public class ErrorResponseDto {

    @Schema(description = "error path", example = "uri=/user...")
    private String apiPath;
    @Schema(description = "Http Status" , example = "SOME_STATUS")
    private HttpStatus errorCode;
    @Schema(description = "Error Message", example = "Some error message")
    private String errorMessage;
    @Schema(description = "error local date time", example = "2024-08-20T20:10:35.6842274")
    private LocalDateTime errorTime;

}