package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionHeadFootnote;
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
public interface SectionHeadFootnoteRepository extends JpaRepository<SectionHeadFootnote, Integer> {
	List<SectionHeadFootnote> findAllByOrderByIdAsc();
	List<SectionHeadFootnote> findAllByOrderByIdDesc();
	Page<SectionHeadFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<SectionHeadFootnote> findAllByOrderByIdDesc(Pageable page);

	List<SectionHeadFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<SectionHeadFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<SectionHeadFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<SectionHeadFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM SectionHeadFootnote t WHERE SectionId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
	List<SectionHeadFootnote>  getFootnoteWithGrateaterOrder(Integer sectionId,Integer footNoteNumber);

	@Query(value = "SELECT * FROM SectionHeadFootnote t WHERE id=?1", nativeQuery = true)
	SectionHeadFootnote getSectionId(Integer id);


	
	/*EXTRA*/
			

}

