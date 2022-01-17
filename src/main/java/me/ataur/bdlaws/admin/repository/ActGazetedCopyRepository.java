package me.ataur.bdlaws.admin.repository;


import me.ataur.bdlaws.admin.model.ActGazettedCopyForAct;
import me.ataur.bdlaws.admin.model.ActScheduleForAct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * Created by imran
 */
@Repository
public interface ActGazetedCopyRepository extends JpaRepository<ActGazettedCopyForAct, Integer> {

			

}

