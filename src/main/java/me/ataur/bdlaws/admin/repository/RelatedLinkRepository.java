package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.RelatedLink;
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
public interface RelatedLinkRepository extends JpaRepository<RelatedLink, Integer> {
	List<RelatedLink> findAllByOrderByIdAsc();
	List<RelatedLink> findAllByOrderByIdDesc();
	Page<RelatedLink> findAllByOrderByIdAsc(Pageable page);
	Page<RelatedLink> findAllByOrderByIdDesc(Pageable page);

	List<RelatedLink> findByCreatedByOrderByIdAsc(String createdBy);
	List<RelatedLink> findByCreatedByOrderByIdDesc(String createdBy);
	Page<RelatedLink> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<RelatedLink> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

    List<RelatedLink> findByStatusIsTrueOrderByIdAsc();


	
	/*EXTRA*/
			

}

