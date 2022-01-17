package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Act;
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
public interface ActRepository extends JpaRepository<Act, Integer> {
    List<Act> findAllByOrderByIdAsc();

    List<Act> findAllByOrderByIdDesc();

    Page<Act> findAllByOrderByIdAsc(Pageable page);

    Page<Act> findAllByOrderByIdDesc(Pageable page);

    List<Act> findByCreatedByOrderByIdAsc(String createdBy);

    List<Act> findByCreatedByOrderByIdDesc(String createdBy);

    Page<Act> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);

    Page<Act> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);

    List<Act> findAllByOrderByShortTitleAsc();
    @Query(value = "SELECT * FROM Act WHERE shortTitle LIKE ?1% ORDER BY year ASC", nativeQuery = true)
    List<Act> findAllByStartingAlphabet(String str);

    @Query(value = "SELECT id as id, shortTitle as name FROM Act WHERE volume=?1 ORDER BY shortTitle ASC", nativeQuery = true)
    List<Object[]> getActDropDownListByvolume(Integer volume);

    List<Act> findByVolumeOrderByIdAsc(Volume volume);

    @Query(value = "SELECT * FROM Act WHERE volume=?1 AND status=1 ORDER BY year,orderNo  ASC", nativeQuery = true)
    List<Act> findByVolumeOrderByOrderNoAsc(Volume volume);

    @Query("SELECT a.id, a.shortTitle FROM Act a WHERE a.status=true")
    public List<Object[]> findActShortTitle();


    //@Query(value = "SELECT a.* FROM Act a INNER JOIN Volume on a.volume=Volume.id where Volume.status=1 and a.status=1 ORDER BY a.year ASC, a.id ASC", nativeQuery = true)
    /*@Query(value = "SELECT a.* FROM Act a INNER JOIN Volume on a.volume=Volume.id where Volume.status=1 and a.status=1 ORDER BY a.year ASC, a.orderNo ASC,a.id ASC", nativeQuery = true)*/
    @Query(value = "SELECT * FROM Act,Volume where Act.volume=Volume.id and Volume.status=1 and Act.status=1 ORDER BY Act.year ASC,Act.orderNo ASC,Act.id ASC", nativeQuery = true)
    List<Act> getAllActsByActiveVolume();
    //List<Act> findByStatusIsTrueOrderByYearAsc();


    List<Act> findByStatusIsTrueOrderByIdDesc();


    List<Act> findByStatusIsTrueOrderByIdDescOrderNoAsc();

    List<Act> findByStatusIsTrueOrderByOrderNoAsc();

    List<Act> findByStatusIsTrueOrderByIdAscOrderNoAsc();

    List<Act> findByStatusIsTrueOrderByShortTitleAsc();

    Act findByIdAndVolume_statusIsTrueAndStatusIsTrue(Integer id);


    @Query(value = "SELECT * FROM Act t order by id desc limit 1", nativeQuery = true)
    Act getNewAct();

	/*EXTRA*/


}

