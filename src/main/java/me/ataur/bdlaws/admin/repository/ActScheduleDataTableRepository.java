package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActSchedule;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Created by imran
 */
@Repository
public interface ActScheduleDataTableRepository extends DataTablesRepository<ActSchedule, Integer>{
}

