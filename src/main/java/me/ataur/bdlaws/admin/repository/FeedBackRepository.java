package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.FeedBack;
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
public interface FeedBackRepository extends JpaRepository<FeedBack, Integer> {
	List<FeedBack> findAllByOrderByIdAsc();
	List<FeedBack> findAllByOrderByIdDesc();
	Page<FeedBack> findAllByOrderByIdAsc(Pageable page);
	Page<FeedBack> findAllByOrderByIdDesc(Pageable page);

	List<FeedBack> findByCreatedByOrderByIdAsc(String createdBy);
	List<FeedBack> findByCreatedByOrderByIdDesc(String createdBy);
	Page<FeedBack> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<FeedBack> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);


	
	/*EXTRA*/
			

}

