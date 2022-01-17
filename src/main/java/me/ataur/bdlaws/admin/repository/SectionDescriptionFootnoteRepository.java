package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionDescriptionFootnote;
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
public interface SectionDescriptionFootnoteRepository extends JpaRepository<SectionDescriptionFootnote, Integer> {
	List<SectionDescriptionFootnote> findAllByOrderByIdAsc();
	List<SectionDescriptionFootnote> findAllByOrderByIdDesc();
	Page<SectionDescriptionFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<SectionDescriptionFootnote> findAllByOrderByIdDesc(Pageable page);

	List<SectionDescriptionFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<SectionDescriptionFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<SectionDescriptionFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<SectionDescriptionFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM SectionDescriptionFootnote t WHERE SectionId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<SectionDescriptionFootnote>  getFootnoteWithGrateaterOrder(Integer sectionId,Integer footNoteNumber);

	@Query(value = "SELECT * FROM SectionDescriptionFootnote t WHERE id=?1", nativeQuery = true)
	SectionDescriptionFootnote getSectionId(Integer id);


	
	/*EXTRA*/
			

}

