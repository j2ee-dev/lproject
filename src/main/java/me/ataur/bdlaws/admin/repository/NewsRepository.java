package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.News;
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
public interface NewsRepository extends JpaRepository<News, Integer> {
	List<News> findAllByOrderByIdAsc();
	List<News> findAllByOrderByIdDesc();
	Page<News> findAllByOrderByIdAsc(Pageable page);
	Page<News> findAllByOrderByIdDesc(Pageable page);

	List<News> findByCreatedByOrderByIdAsc(String createdBy);
	List<News> findByCreatedByOrderByIdDesc(String createdBy);
	Page<News> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<News> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

    Page<News> findByStatusIsTrueOrderByCreatedAt(Pageable pageable);

	News findByIdAndStatusIsTrue(Integer id);


	
	/*EXTRA*/
			

}

