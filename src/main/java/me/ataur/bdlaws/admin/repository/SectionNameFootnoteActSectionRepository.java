package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.SectionNameFootnoteForActSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * @author Amran
 */
@Repository
public interface SectionNameFootnoteActSectionRepository extends JpaRepository<SectionNameFootnoteForActSection, Integer> {


    @Query(value = "SELECT * FROM SectionNameFootnote t where footnoteNumber>=?1 and SectionId =?2", nativeQuery = true)
    List<SectionNameFootnoteForActSection> getFootNoatFrom(Integer fNumber, Integer actSectionNo);

    @Query(value = "SELECT * FROM SectionNameFootnote t where footnoteNumber=?1 and SectionId =?2", nativeQuery = true)
    SectionNameFootnoteForActSection getOneFootNoat(Integer fNumber, Integer actSectionNo);
    @Query(value = "SELECT max(id) FROM SectionNameFootnote", nativeQuery=true)
    Integer getLastId();
    @Query(value = "SELECT * FROM SectionDescriptionFootnote where  SectionId=?1 LIMIT 1", nativeQuery=true)
    SectionNameFootnoteForActSection findFirst(Integer sectionId);
    @Query(value = "SELECT COUNT(*) c FROM `SectionNameFootnote` WHERE SectionId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer sectionId);
    @Modifying
    @Query(value = "DELETE FROM `SectionNameFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND SectionId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer sectionId);
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

