package am.fillandgo.dao;

import am.fillandgo.models.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

/**
 * The ContractDao interface provides methods to interact with the Contract entity in the database.
 */
@Mapper
public interface ContractDao {

    /**
     * Saves a Contract entity to the database.
     * @param contract The Contract object to be saved.
     */
    void save(@Param("contract") Contract contract);

    /**
     * Retrieves all Contract entities from the database with pagination.
     * @param rowBounds The pagination details (offset and limit).
     * @return A list of Contract objects.
     */
    List<Contract> getAll(RowBounds rowBounds);

    /**
     * Counts the number of contracts in the database.
     * @return The total number of contracts.
     */
    long countContracts();

    boolean checkIfContractExists(@Param("mobileNumber") String mobileNumber);
}
