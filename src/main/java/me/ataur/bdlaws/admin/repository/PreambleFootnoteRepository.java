package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PreambleFootnote;
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
public interface PreambleFootnoteRepository extends JpaRepository<PreambleFootnote, Integer> {
	List<PreambleFootnote> findAllByOrderByIdAsc();
	List<PreambleFootnote> findAllByOrderByIdDesc();
	Page<PreambleFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<PreambleFootnote> findAllByOrderByIdDesc(Pageable page);

	List<PreambleFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<PreambleFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<PreambleFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<PreambleFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);
	@Query(value = "SELECT * FROM PreambleFootnote t WHERE ActId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<PreambleFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

