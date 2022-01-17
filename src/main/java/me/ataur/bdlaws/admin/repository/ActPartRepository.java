package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActPart;
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
public interface ActPartRepository extends JpaRepository<ActPart, Integer> {
	List<ActPart> findAllByOrderByIdAsc();
	List<ActPart> findAllByOrderByIdDesc();
	Page<ActPart> findAllByOrderByIdAsc(Pageable page);
	Page<ActPart> findAllByOrderByIdDesc(Pageable page);

	List<ActPart> findByCreatedByOrderByIdAsc(String createdBy);
	List<ActPart> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ActPart> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<ActPart> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);


	List<ActPart> findAllByOrderByPartNoAsc();
		
	@Query(value = "SELECT id as id, partNo as name FROM ActPart WHERE ActId=?1 ORDER BY partNo ASC", nativeQuery = true)
	List<Object[]> getActPartDropDownListByActId(Integer ActId);

    ActPart findByIdAndStatusIsTrue(Integer partId);

	ActPart findByIdAndStatusIsTrueAndActId_idIs(Integer partId, Integer actId);

	@Query(value = "SELECT * FROM ActPart t order by id desc limit 1", nativeQuery=true)
	ActPart getNewActPart();
	
	/*EXTRA*/
			

}

