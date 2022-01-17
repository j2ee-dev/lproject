package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ChapterNoFootnote;
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
public interface ChapterNoFootnoteRepository extends JpaRepository<ChapterNoFootnote, Integer> {
	List<ChapterNoFootnote> findAllByOrderByIdAsc();
	List<ChapterNoFootnote> findAllByOrderByIdDesc();
	Page<ChapterNoFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<ChapterNoFootnote> findAllByOrderByIdDesc(Pageable page);

	List<ChapterNoFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<ChapterNoFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ChapterNoFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<ChapterNoFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);
	@Query(value = "SELECT * FROM ChapterNoFootnote t WHERE ChapterId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<ChapterNoFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

