package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Faq;
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
public interface FaqRepository extends JpaRepository<Faq, Integer> {
	List<Faq> findAllByOrderByIdAsc();
	List<Faq> findAllByOrderByIdDesc();
	Page<Faq> findAllByOrderByIdAsc(Pageable page);
	Page<Faq> findAllByOrderByIdDesc(Pageable page);

	List<Faq> findByCreatedByOrderByIdAsc(String createdBy);
	List<Faq> findByCreatedByOrderByIdDesc(String createdBy);
	Page<Faq> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<Faq> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

    List<Faq> findByStatusIsTrueOrderByIdAsc();


	
	/*EXTRA*/
			

}

