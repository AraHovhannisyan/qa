package am.fillandgo.services;

import am.fillandgo.dto.responses.ContractDto;
import am.fillandgo.models.Contract;
import am.fillandgo.util.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * The IContractService interface provides methods for saving contracts and retrieving contracts with pagination.
 */
public interface IContractService {


    /**
     * Saves a contract with the provided information and uploads a file.
     * @param contract the contract object to be saved
     */
    @Transactional
    void saveContract(ContractDto contract, MultipartFile[] files);

    /**
     * Retrieves contracts using pagination parameters.
     * @param page the page number
     * @param size the number of contracts per page
     * @return a Page object containing a list of ContractDto objects
     */
    Page<ContractDto> getContracts(int page, int size);
}