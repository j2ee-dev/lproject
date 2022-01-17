package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PartNoFootnote;
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
public interface PartNoFootnoteRepository extends JpaRepository<PartNoFootnote, Integer> {
	List<PartNoFootnote> findAllByOrderByIdAsc();
	List<PartNoFootnote> findAllByOrderByIdDesc();
	Page<PartNoFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<PartNoFootnote> findAllByOrderByIdDesc(Pageable page);

	List<PartNoFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<PartNoFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<PartNoFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<PartNoFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM PartNoFootnote t WHERE PartId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<PartNoFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

