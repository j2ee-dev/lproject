package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionDescriptionFootnote;
import me.ataur.bdlaws.admin.model.SectionDescriptionFootnoteForActSection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * @author Amran
 */
@Repository
public interface SectionDescriptionFootnoteActSectionRepository extends JpaRepository<SectionDescriptionFootnoteForActSection, Integer> {


    @Query(value = "SELECT * FROM SectionDescriptionFootnote t where footnoteNumber>=?1 and SectionId =?2", nativeQuery=true)
    List<SectionDescriptionFootnoteForActSection> getFootNoatFrom(Integer fNumber , Integer actSectionNo);
    @Query(value = "SELECT * FROM SectionDescriptionFootnote t where footnoteNumber=?1 and SectionId =?2", nativeQuery=true)
    SectionDescriptionFootnoteForActSection getOneFootNoat(Integer fNumber , Integer actSectionNo);

    @Query(value = "SELECT max(id) FROM SectionDescriptionFootnote", nativeQuery=true)
    Integer getLastId();
    @Query(value = "SELECT * FROM SectionDescriptionFootnote where  SectionId=?1 LIMIT 1", nativeQuery=true)
    SectionDescriptionFootnoteForActSection findFirst(Integer sectionId);
    @Modifying
    @Query(value = "DELETE FROM `sectiondescriptionfootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND SectionId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer sectionId);
    @Query(value = "SELECT COUNT(*) c FROM `sectiondescriptionfootnote` WHERE SectionId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer sectionId);
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

