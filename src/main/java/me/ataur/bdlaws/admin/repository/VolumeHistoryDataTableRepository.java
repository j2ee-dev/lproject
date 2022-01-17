package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.VolumeHistory;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

/*IMPORT_MODEL*/

/**
 * @author Amran
 */
@Repository
public interface VolumeHistoryDataTableRepository extends DataTablesRepository<VolumeHistory, Integer>{
}

