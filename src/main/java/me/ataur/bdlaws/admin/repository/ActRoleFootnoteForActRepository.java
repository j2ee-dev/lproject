package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActRoleFootnoteForAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * Created by imran
 */
@Repository
public interface ActRoleFootnoteForActRepository extends JpaRepository<ActRoleFootnoteForAct, Integer> {


    @Query(value = "SELECT * FROM ActRoleFootnote t where footnoteNumber>=?1 and ActId =?2", nativeQuery = true)
    List<ActRoleFootnoteForAct> getFootNoatFrom(Integer fNumber, Integer actNo);

    @Query(value = "SELECT * FROM ActRoleFootnote t where footnoteNumber=?1 and ActId =?2", nativeQuery = true)
    ActRoleFootnoteForAct getOneFootNoat(Integer fNumber, Integer actNo);

    @Query(value = "SELECT max(id) FROM ActRoleFootnote", nativeQuery=true)
    Integer getLastId();

    @Query(value = "SELECT COUNT(*) c FROM `ActRoleFootnote` WHERE ActId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer actId);
    @Modifying
    @Query(value = "DELETE FROM `ActRoleFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND ActId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer actId);
    @Query(value = "SELECT * FROM ActRoleFootnote where  ActId=?1 LIMIT 1", nativeQuery=true)
    ActRoleFootnoteForAct findFirst(Integer actId);
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

