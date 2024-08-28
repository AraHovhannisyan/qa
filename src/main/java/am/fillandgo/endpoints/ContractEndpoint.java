package am.fillandgo.endpoints;

import am.fillandgo.constants.FIleConstants;
import am.fillandgo.dto.responses.ContractDto;
import am.fillandgo.dto.responses.ResponseDto;
import am.fillandgo.models.Contract;
import am.fillandgo.services.IContractService;
import am.fillandgo.util.Page;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ContractEndpoint is a REST controller class that handles HTTP requests related to Contract operations.
 */
@Tag(name = "CONTRACT",
        description = "ContractEndpoint is a REST controller class that handles HTTP requests related to Contract operations.")
@Validated
@RestController
@RequestMapping("/contracts")
@RequiredArgsConstructor
public class ContractEndpoint {

    private final IContractService contractService;


    /**
     * Uploads a file and saves a contract with the provided information.
     * @param file           the file to upload
     * @return the response entity containing the status code and status message
     */
    @Operation(summary = "Create Contract REST API",
            description = "Saves contract object and uploads signed contract in Fill&Go and returns information about creation status")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "HTTP Status CREATED"),
            @ApiResponse(responseCode = "400", description = "HTTP Status BAD_REQUEST")})
    @PostMapping
    public ResponseEntity<ResponseDto> addUserWithImage(
            @RequestParam(name = "contract") String contract,
            @RequestParam("file") MultipartFile[] files) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        ContractDto contractObj = objectMapper.readValue(contract, ContractDto.class);

        contractService.saveContract(contractObj,  files);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResponseDto.builder()
                .statusCode(HttpStatus.CREATED)
                .statusMessage(FIleConstants.FILE_UPLOADED)
                .build());
    }


    /**
     * Retrieves contracts using pagination parameters.
     * @param page the page number
     * @param size the number of contracts per page
     * @return a Page object containing a list of ContractDto objects
     */
    @Operation(summary = "Get Contracts REST API",
            description = "Get contracts using pagination params (page, size)")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "HTTP Status OK")})
    @GetMapping
    public Page<ContractDto> getContracts(@RequestParam(defaultValue = "1") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return contractService.getContracts(page, size);
    }
}
