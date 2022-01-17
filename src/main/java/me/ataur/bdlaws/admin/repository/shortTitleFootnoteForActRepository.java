package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ShortTitleFootnoteForAct;
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
public interface shortTitleFootnoteForActRepository extends JpaRepository<ShortTitleFootnoteForAct, Integer> {


    @Query(value = "SELECT * FROM ShortTitleFootnote t where footnoteNumber>=?1 and ActId =?2", nativeQuery = true)
    List<ShortTitleFootnoteForAct> getFootNoatFrom(Integer fNumber, Integer actNo);

    @Query(value = "SELECT * FROM ShortTitleFootnote t where footnoteNumber=?1 and ActId =?2", nativeQuery = true)
    ShortTitleFootnoteForAct getOneFootNoat(Integer fNumber, Integer actNo);
    @Query(value = "SELECT max(id) FROM ShortTitleFootnote", nativeQuery=true)
    Integer getLastId();
    @Query(value = "SELECT COUNT(*) c FROM `ShortTitleFootnote` WHERE ActId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer actId);
    @Modifying
    @Query(value = "DELETE FROM `ShortTitleFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND ActId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer actId);
    @Query(value = "SELECT * FROM ShortTitleFootnote where  ActId=?1 LIMIT 1", nativeQuery=true)
    ShortTitleFootnoteForAct findFirst(Integer actId);

    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

