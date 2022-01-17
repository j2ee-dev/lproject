package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionNameFootnote;
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
public interface SectionNameFootnoteRepository extends JpaRepository<SectionNameFootnote, Integer> {
	List<SectionNameFootnote> findAllByOrderByIdAsc();
	List<SectionNameFootnote> findAllByOrderByIdDesc();
	Page<SectionNameFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<SectionNameFootnote> findAllByOrderByIdDesc(Pageable page);

	List<SectionNameFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<SectionNameFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<SectionNameFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<SectionNameFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM SectionNameFootnote t WHERE SectionId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
	List<SectionNameFootnote>  getFootnoteWithGrateaterOrder(Integer sectionId,Integer footNoteNumber);

	@Query(value = "SELECT * FROM SectionNameFootnote t WHERE id=?1", nativeQuery = true)
	SectionNameFootnote getSectionId(Integer id);


	
	/*EXTRA*/
			

}

