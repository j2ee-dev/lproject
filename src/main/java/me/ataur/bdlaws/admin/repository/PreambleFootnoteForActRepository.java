package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.PreambleFootnoteForAct;
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
public interface PreambleFootnoteForActRepository extends JpaRepository<PreambleFootnoteForAct, Integer> {


    @Query(value = "SELECT * FROM PreambleFootnote t where footnoteNumber>=?1 and ActId =?2", nativeQuery = true)
    List<PreambleFootnoteForAct> getFootNoatFrom(Integer fNumber, Integer actNo);

    @Query(value = "SELECT * FROM PreambleFootnote t where footnoteNumber=?1 and ActId =?2", nativeQuery = true)
    PreambleFootnoteForAct getOneFootNoat(Integer fNumber, Integer actNo);

    @Query(value = "SELECT max(id) FROM PreambleFootnote", nativeQuery=true)
    Integer getLastId();

    @Query(value = "SELECT COUNT(*) c FROM `PreambleFootnote` WHERE ActId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer actId);
    @Modifying
    @Query(value = "DELETE FROM `PreambleFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND ActId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer actId);
    @Query(value = "SELECT * FROM PreambleFootnote where  ActId=?1 LIMIT 1", nativeQuery=true)
    PreambleFootnoteForAct findFirst(Integer actId);
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

