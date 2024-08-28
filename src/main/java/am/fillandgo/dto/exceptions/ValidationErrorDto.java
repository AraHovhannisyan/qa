package am.fillandgo.dto.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ValidationErrorDto is a data transfer object class that represents a validation error.
 * It contains information about the field that caused the error and the error message.
 */
@Data
@AllArgsConstructor
@Schema(name = "ValidationError", description = "Validation Error object")
public class ValidationErrorDto {

    @Schema(description = "Error Field", example = "someFieldName")
    private String field;
    @Schema(description = "Error Message", example = "someErrorMessage")
    private String message;

}
