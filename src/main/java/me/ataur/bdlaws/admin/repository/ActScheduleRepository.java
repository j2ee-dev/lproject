package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActSchedule;
import me.ataur.bdlaws.admin.model.ActSchedule;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Created by imran
 */
@Repository
public interface ActScheduleRepository extends JpaRepository<ActSchedule, Integer> {
	List<ActSchedule> findAllByOrderByIdAsc();
	List<ActSchedule> findAllByOrderByIdDesc();
	Page<ActSchedule> findAllByOrderByIdAsc(Pageable page);
	Page<ActSchedule> findAllByOrderByIdDesc(Pageable page);

	List<ActSchedule> findByCreatedByOrderByIdAsc(String createdBy);
	List<ActSchedule> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ActSchedule> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<ActSchedule> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);

	@Query(value = "SELECT * FROM ActSchedule t order by id desc limit 1", nativeQuery=true)
	ActSchedule getNewActSchedule();
	
	/*EXTRA*/
			

}

