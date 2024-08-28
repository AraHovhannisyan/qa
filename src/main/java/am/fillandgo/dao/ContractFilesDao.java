package am.fillandgo.dao;

import am.fillandgo.models.ContractFiles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * The ContractDao interface provides methods to interact with the Contract entity in the database.
 */
@Mapper
public interface ContractFilesDao {

    /**
     * Saves a Contract entity to the database.
     * @param contract The Contract object to be saved.
     */
    void save(@Param("contractFile") ContractFiles contract);


}
