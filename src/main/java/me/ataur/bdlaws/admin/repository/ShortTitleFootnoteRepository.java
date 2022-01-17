package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ShortTitleFootnote;
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
public interface ShortTitleFootnoteRepository extends JpaRepository<ShortTitleFootnote, Integer> {
	List<ShortTitleFootnote> findAllByOrderByIdAsc();
	List<ShortTitleFootnote> findAllByOrderByIdDesc();
	Page<ShortTitleFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<ShortTitleFootnote> findAllByOrderByIdDesc(Pageable page);

	List<ShortTitleFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<ShortTitleFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ShortTitleFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<ShortTitleFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM ShortTitleFootnote t WHERE ActId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<ShortTitleFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);

	/*EXTRA*/
			

}

