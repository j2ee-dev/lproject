package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PartNameFootnoteForActPart;
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
public interface PartNameFootnoteForActPartRepository extends JpaRepository<PartNameFootnoteForActPart, Integer> {


    @Query(value = "SELECT * FROM PartNameFootnote t where footnoteNumber>=?1 and PartId =?2", nativeQuery = true)
    List<PartNameFootnoteForActPart> getFootNoatFrom(Integer fNumber, Integer actPartNo);

    @Query(value = "SELECT * FROM PartNameFootnote t where footnoteNumber=?1 and PartId =?2", nativeQuery = true)
    PartNameFootnoteForActPart getOneFootNoat(Integer fNumber, Integer actPartNo);
    @Query(value = "SELECT COUNT(*) c FROM `PartNameFootnote` WHERE PartId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer PartId);
    @Modifying
    @Query(value = "DELETE FROM `PartNameFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND PartId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer PartId);
    @Query(value = "SELECT * FROM PartNameFootnote where  PartId=?1 LIMIT 1", nativeQuery=true)
    PartNameFootnoteForActPart findFirst(Integer PartId);
    @Query(value = "SELECT max(id) FROM PartNameFootnote", nativeQuery=true)
    Integer getLastId();
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

