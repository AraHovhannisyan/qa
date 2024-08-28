package am.fillandgo.util.dataMappers;

import am.fillandgo.dto.responses.ContractDto;
import am.fillandgo.models.Contract;

/**
 * The ContractMapper class is a utility class that is used for mapping Contract objects to ContractDto objects.
 */
public class ContractMapper {

    private ContractMapper() {
    }

    /**
     * Converts a Contract object to a ContractDto object.
     * @param contract The Contract object to be converted.
     * @return The converted ContractDto object.
     */
    public static ContractDto toContractDto(Contract contract) {
        return ContractDto.builder()
                .name(contract.getName() != null ? contract.getName() : null)
                .surname(contract.getSurname() != null ? contract.getSurname() : null)
                .patronymic(contract.getPatronymic() != null ? contract.getPatronymic() : null)
                .mobileNumber(contract.getMobileNumber() != null ? contract.getMobileNumber() : null)
                .build();
    }

    /**
     * Converts a Contract object to a ContractDto object.
     * @param contract The Contract object to be converted.
     * @return The converted ContractDto object.
     */
    public static Contract toContract(ContractDto contract) {
        return Contract.builder()
                .name(contract.getName() != null ? contract.getName() : null)
                .surname(contract.getSurname() != null ? contract.getSurname() : null)
                .patronymic(contract.getPatronymic() != null ? contract.getPatronymic() : null)
                .mobileNumber(contract.getMobileNumber() != null ? contract.getMobileNumber() : null)
                .build();
    }
}
