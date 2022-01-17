package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActScheduleForAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * Created by imran
 */
@Repository
public interface ActScheduleForActRepository extends JpaRepository<ActScheduleForAct, Integer> {

	@Query(value = "SELECT * FROM ActSchedule t WHERE ActId=?1 order by id", nativeQuery=true)
	List<ActScheduleForAct> findAllByActId(Integer actId);
	
	/*EXTRA*/
			

}

