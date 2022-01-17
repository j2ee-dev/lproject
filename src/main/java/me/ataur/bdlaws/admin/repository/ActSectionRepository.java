package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Act;
import me.ataur.bdlaws.admin.model.ActSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * @author Amran
 */
@Repository
public interface ActSectionRepository extends JpaRepository<ActSection, Integer> {
	List<ActSection> findAllByOrderByIdAsc();
	List<ActSection> findAllByOrderByIdDesc();
	Page<ActSection> findAllByOrderByIdAsc(Pageable page);
	Page<ActSection> findAllByOrderByIdDesc(Pageable page);

	List<ActSection> findByCreatedByOrderByIdAsc(String createdBy);
	List<ActSection> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ActSection> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<ActSection> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);


	List<ActSection> findAllByOrderBySectionNameAsc();

    List<ActSection> findByActIdAndStatusIsTrueOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(Act act);

    List<ActSection> findByActId_statusIsTrueAndPartId_idIs(Integer partId);


    List<ActSection> findByActId_statusIsTrueAndPartId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(Integer partId);

	List<ActSection> findByActId_statusIsTrueAndChapterId_idIsOrderBySectionNoAscPartId_partNoAscChapterId_chapterNoAsc(Integer chapterId);

    ActSection findByIdAndStatusIsTrueAndActId_idIs(Integer sectionId, Integer actId);

	@Query(value = "SELECT * FROM ActSection t order by id desc limit 1", nativeQuery=true)
	ActSection getNewActSection();






}

