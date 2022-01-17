package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PartNoFootnoteForActPart;
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
public interface PartNoFootnoteForActPartRepository extends JpaRepository<PartNoFootnoteForActPart, Integer> {


    @Query(value = "SELECT * FROM PartNoFootnote t where footnoteNumber>=?1 and PartId =?2", nativeQuery = true)
    List<PartNoFootnoteForActPart> getFootNoatFrom(Integer fNumber, Integer actPartNo);

    @Query(value = "SELECT * FROM PartNoFootnote t where footnoteNumber=?1 and PartId =?2", nativeQuery = true)
    PartNoFootnoteForActPart getOneFootNoat(Integer fNumber, Integer actPartNo);
    @Query(value = "SELECT max(id) FROM PartNoFootnote", nativeQuery=true)
    Integer getLastId();
    @Query(value = "SELECT COUNT(*) c FROM `PartNoFootnote` WHERE PartId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer PartId);
    @Modifying
    @Query(value = "DELETE FROM `PartNoFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND PartId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer PartId);
    @Query(value = "SELECT * FROM PartNoFootnote where  PartId=?1 LIMIT 1", nativeQuery=true)
    PartNoFootnoteForActPart findFirst(Integer PartId);

    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

