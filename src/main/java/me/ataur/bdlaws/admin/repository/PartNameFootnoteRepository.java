package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PartNameFootnote;
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
public interface PartNameFootnoteRepository extends JpaRepository<PartNameFootnote, Integer> {
	List<PartNameFootnote> findAllByOrderByIdAsc();
	List<PartNameFootnote> findAllByOrderByIdDesc();
	Page<PartNameFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<PartNameFootnote> findAllByOrderByIdDesc(Pageable page);

	List<PartNameFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<PartNameFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<PartNameFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<PartNameFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM PartNameFootnote t WHERE PartId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<PartNameFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

