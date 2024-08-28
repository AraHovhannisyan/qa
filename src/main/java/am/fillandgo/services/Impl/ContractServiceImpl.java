package am.fillandgo.services.Impl;

import am.fillandgo.dao.ContractDao;
import am.fillandgo.dao.ContractFilesDao;
import am.fillandgo.dto.responses.ContractDto;
import am.fillandgo.exceptions.ContractAlreadyExistsException;
import am.fillandgo.models.Contract;
import am.fillandgo.models.ContractFiles;
import am.fillandgo.services.IContractService;
import am.fillandgo.util.Page;
import am.fillandgo.util.dataMappers.ContractMapper;
import am.fillandgo.util.generators.RandomStringGeneratorUtil;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

/**
 * The ContractServiceImpl class is an implementation of the IContractService interface.
 * It provides methods for saving contracts and retrieving contracts with pagination.
 */
@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements IContractService {

    private final ContractDao contractDao;
    private final FileService fileService;
    private final ContractFilesDao contractFilesDao;

    /**
     * Saves a contract to the database.
     * @param contract The contract to be saved.
     * @throws IllegalArgumentException If the file is empty, the file size exceeds the maximum limit, or the file format is invalid.
     */
    @Override
    public void saveContract(ContractDto contract, MultipartFile[] files) {

        if (contractDao.checkIfContractExists(contract.getMobileNumber())) {
            throw new ContractAlreadyExistsException("Contract already exists with mobile number %s", contract.getMobileNumber());
        }

        Contract contract1 = ContractMapper.toContract(contract);
        contract1.setId(RandomStringGeneratorUtil.uuId());
        String userId = contract1.getId();

        for (MultipartFile file : files) {
            // Validate file emptiness
            if (file.isEmpty() || file.getSize() == 0) {
                throw new IllegalArgumentException("File cannot be empty");
            }

            // Validate file size (example: max size of 5MB)
            long maxFileSize = 5 * 1024 * 1024; // 5 MB
            if (file.getSize() > maxFileSize) {
                throw new IllegalArgumentException("File size exceeds the maximum limit of 5 MB");
            }

            // Validate file format (example: only allow PDF, JPEG, PNG)
            List<String> allowedFileTypes = Arrays.asList("application/pdf", "image/jpeg", "image/png");
            if (!allowedFileTypes.contains(file.getContentType())) {
                throw new IllegalArgumentException("Invalid file format. Only PDF, JPEG, and PNG are allowed.");
            }

            String s = fileService.saveFile(file);
            ContractFiles contractFile = new ContractFiles();
            contractFile.setId(RandomStringGeneratorUtil.uuId());
            contractFile.setName(s);
            contractFile.setUserId(userId);
            contractFilesDao.save(contractFile);
        }
        contractDao.save(contract1);
    }

    /**
     * Retrieves a page of ContractDto objects with pagination.
     * @param page The page number.
     * @param size The number of contracts per page.
     * @return A Page object containing the list of ContractDto objects, current page number, page size, and total number of contracts.
     */
    public Page<ContractDto> getContracts(int page, int size) {
        int offset = (page - 1) * size;
        RowBounds rowBounds = new RowBounds(offset, size);
        List<Contract> contractList = contractDao.getAll(rowBounds);
        List<ContractDto> contractDtoList = contractList.stream()
                .map(ContractMapper::toContractDto)
                .toList();

        long totalItems = contractDao.countContracts();
        return new Page<>(contractDtoList, page, size, totalItems);
    }
}