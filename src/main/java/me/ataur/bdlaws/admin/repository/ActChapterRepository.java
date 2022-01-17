package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActChapter;
/*IMPORT_MODEL*/
import me.ataur.bdlaws.admin.model.Volume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * Created by imran
 */
@Repository
public interface ActChapterRepository extends JpaRepository<ActChapter, Integer> {
	List<ActChapter> findAllByOrderByIdAsc();
	List<ActChapter> findAllByOrderByIdDesc();
	Page<ActChapter> findAllByOrderByIdAsc(Pageable page);
	Page<ActChapter> findAllByOrderByIdDesc(Pageable page);

	List<ActChapter> findByCreatedByOrderByIdAsc(String createdBy);
	List<ActChapter> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ActChapter> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<ActChapter> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);


	List<ActChapter> findAllByOrderByChapterNoAsc();
		
	@Query(value = "SELECT id as id, chapterNo as name FROM ActChapter WHERE PartId=?1 ORDER BY chapterNo ASC", nativeQuery = true)
	List<Object[]> getActChapterDropDownListByPartId(Integer PartId);

	@Query(value = "SELECT id as id, chapterNo as name FROM ActChapter WHERE PartId IS NULL AND ActId=?1 ORDER BY chapterNo ASC", nativeQuery = true)
	List<Object[]> getActChapterDropDownListByActIdAndPartIdIsNull(Integer actId);


	@Query(value = "SELECT * FROM ActChapter t WHERE ActId=?2 and id=?1 and status=true", nativeQuery=true)
    ActChapter findByIdAndStatusIsTrueAndActId_idIs(Integer chapterId, Integer actId);

	@Query(value = "SELECT * FROM ActChapter t order by id desc limit 1", nativeQuery=true)
	ActChapter getNewActChapter();

	@Query(value = "SELECT id as id, chapterNo as name FROM ActChapter WHERE ActId=?1 ORDER BY chapterNo ASC", nativeQuery = true)
	List<Object[]> getActChapterDropDownListByActId(Integer actId);
}

