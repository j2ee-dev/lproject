package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.StaticPage;
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
public interface StaticPageRepository extends JpaRepository<StaticPage, Integer> {
	List<StaticPage> findAllByOrderByIdAsc();
	List<StaticPage> findAllByOrderByIdDesc();
	Page<StaticPage> findAllByOrderByIdAsc(Pageable page);
	Page<StaticPage> findAllByOrderByIdDesc(Pageable page);

	List<StaticPage> findByCreatedByOrderByIdAsc(String createdBy);
	List<StaticPage> findByCreatedByOrderByIdDesc(String createdBy);
	Page<StaticPage> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<StaticPage> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

    StaticPage findByPageUrl(String s);


	
	/*EXTRA*/
			

}

