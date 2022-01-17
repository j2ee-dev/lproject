package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionTableFootnote;
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
public interface SectionTableFootnoteRepository extends JpaRepository<SectionTableFootnote, Integer> {
	List<SectionTableFootnote> findAllByOrderByIdAsc();
	List<SectionTableFootnote> findAllByOrderByIdDesc();
	Page<SectionTableFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<SectionTableFootnote> findAllByOrderByIdDesc(Pageable page);
	List<SectionTableFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<SectionTableFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<SectionTableFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<SectionTableFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM SectionTableFootnote t WHERE SectionId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
	List<SectionTableFootnote>  getFootnoteWithGrateaterOrder(Integer sectionId,Integer footNoteNumber);

	@Query(value = "SELECT * FROM SectionTableFootnote t WHERE id=?1", nativeQuery = true)
	SectionTableFootnote getSectionId(Integer id);
	
	/*EXTRA*/
			

}

