package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ChapterNameFootnoteForActChapter;
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
public interface ChapterNameFootnoteForActChapterRepository extends JpaRepository<ChapterNameFootnoteForActChapter, Integer> {


    @Query(value = "SELECT * FROM ChapterNameFootnote t where footnoteNumber>=?1 and ChapterId =?2", nativeQuery = true)
    List<ChapterNameFootnoteForActChapter> getFootNoatFrom(Integer fNumber, Integer actChapterNo);

    @Query(value = "SELECT * FROM ChapterNameFootnote t where footnoteNumber=?1 and ChapterId =?2", nativeQuery = true)
    ChapterNameFootnoteForActChapter getOneFootNoat(Integer fNumber, Integer actChapterNo);
    @Query(value = "SELECT max(id) FROM ChapterNameFootnote", nativeQuery=true)
    Integer getLastId();
    @Query(value = "SELECT COUNT(*) c FROM `ChapterNameFootnote` WHERE ChapterId=?1 GROUP BY FootnoteNumber HAVING c > 1 LIMIT 1", nativeQuery=true)
    Integer checkingDuplicate(Integer ChapterId);
    @Modifying
    @Query(value = "DELETE FROM `ChapterNameFootnote` WHERE (DATE_FORMAT(UpdatedAt, '%Y-%m-%d-%T')<DATE_FORMAT(STR_TO_DATE(?1, '%d-%m-%Y %r'),'%Y-%m-%d-%T') OR UpdatedAt IS NULL) AND ChapterId=?2", nativeQuery=true)
    void deleteDuplicateFootnote(String date,Integer ChapterId);
    @Query(value = "SELECT * FROM ChapterNameFootnote where  ChapterId=?1 LIMIT 1", nativeQuery=true)
    ChapterNameFootnoteForActChapter findFirst(Integer ChapterId);
    /*
	@Modifying(clearAutomatically = true)
    @Query("update RssFeedEntry feedEntry set feedEntry.read =:isRead where feedEntry.id =:entryId")
    void markEntryAsRead(@Param("entryId") Long rssFeedEntryId, @Param("isRead") boolean isRead);
    */


}

