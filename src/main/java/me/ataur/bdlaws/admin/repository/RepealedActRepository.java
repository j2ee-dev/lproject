package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.RepealedAct;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author Amran
 */
@Repository
public interface RepealedActRepository extends JpaRepository<RepealedAct, Integer> {
	List<RepealedAct> findAllByOrderByIdAsc();
	List<RepealedAct> findAllByOrderByIdDesc();
	Page<RepealedAct> findAllByOrderByIdAsc(Pageable page);
	Page<RepealedAct> findAllByOrderByIdDesc(Pageable page);

	List<RepealedAct> findByCreatedByOrderByIdAsc(String createdBy);
	List<RepealedAct> findByCreatedByOrderByIdDesc(String createdBy);
	Page<RepealedAct> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<RepealedAct> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);


	
	/*EXTRA*/
			

}

