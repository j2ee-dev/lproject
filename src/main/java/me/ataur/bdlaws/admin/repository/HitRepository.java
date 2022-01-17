package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Hit;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * @author Amran
 */
@Repository
public interface HitRepository extends JpaRepository<Hit, Integer> {
	List<Hit> findAllByOrderByIdAsc();
	List<Hit> findAllByOrderByIdDesc();
	Page<Hit> findAllByOrderByIdAsc(Pageable page);
	Page<Hit> findAllByOrderByIdDesc(Pageable page);
	@Query(value = "select * from Hit t where  t.hitDate like %:keyword% ", nativeQuery = true)
	Hit findHitByDate(@Param("keyword") String localdate);
	/*List<Hit> findByCreatedByOrderByIdAsc(String createdBy);
	List<Hit> findByCreatedByOrderByIdDesc(String createdBy);
	Page<Hit> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<Hit> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);*/
	@Query(value = "SELECT * FROM hit WHERE (  DATE_FORMAT(hitDate, '%Y-%m-%d-%T')  BETWEEN  DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y'),'%Y-%m-%d-%T')  AND DATE_FORMAT(STR_TO_DATE(?2, '%d-%m-%Y'),'%Y-%m-%d-%T'))", nativeQuery = true)
	List<Hit> getAllBetweenDate(String f,String t);
	@Query(value = "SELECT * FROM hit WHERE (  DATE_FORMAT(hitDate, '%Y-%m-%d-%T')  BETWEEN  DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y'),'%Y-%m-%d-%T')  AND DATE_FORMAT(STR_TO_DATE(?2, '%d-%m-%Y'),'%Y-%m-%d-%T'))", nativeQuery = true)
	List<Hit> getAllBetweenDatePdf(String f,String t);

	/*EXTRA*/
			

}

