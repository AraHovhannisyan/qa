package am.fillandgo.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * ContractDto class represents the data transfer object for Contract information.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "ContractDto", description = "Contract information")
public class ContractDto {

    @Schema(example = "Vazgen", description = "Name of the contractor")
    private String name;

    @Schema(example = "Petrosyan", description = "Surname of the contractor")
    private String surname;

    @Schema(example = "Gurgeni", description = "Patronymic of the contractor")
    private String patronymic;

    @Schema(example = "+374949494", description = "Phone number of the contractor")
    private String mobileNumber;

}
