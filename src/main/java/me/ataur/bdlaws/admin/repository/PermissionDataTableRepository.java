package me.ataur.bdlaws.admin.repository;


/*IMPORT_MODEL*/

import me.ataur.bdlaws.admin.model.Permission;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Amran
 */
@Repository
public interface PermissionDataTableRepository extends DataTablesRepository<Permission, Integer>{
}

