package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ChapterNameFootnote;
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
public interface ChapterNameFootnoteRepository extends JpaRepository<ChapterNameFootnote, Integer> {
	List<ChapterNameFootnote> findAllByOrderByIdAsc();
	List<ChapterNameFootnote> findAllByOrderByIdDesc();
	Page<ChapterNameFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<ChapterNameFootnote> findAllByOrderByIdDesc(Pageable page);

	List<ChapterNameFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<ChapterNameFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ChapterNameFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<ChapterNameFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM ChapterNameFootnote t WHERE ChapterId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<ChapterNameFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

