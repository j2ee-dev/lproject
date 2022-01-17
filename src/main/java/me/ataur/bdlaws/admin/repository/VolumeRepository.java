package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Volume;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author Amran
 */
@Repository
public interface VolumeRepository extends JpaRepository<Volume, Integer> {
	List<Volume> findAllByOrderByIdAsc();
	List<Volume> findAllByOrderByIdDesc();
	Page<Volume> findAllByOrderByIdAsc(Pageable page);
	Page<Volume> findAllByOrderByIdDesc(Pageable page);

	List<Volume> findByCreatedByOrderByIdAsc(String createdBy);
	List<Volume> findByCreatedByOrderByIdDesc(String createdBy);
	Page<Volume> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<Volume> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);


	List<Volume> findAllByOrderByVolumeNameAsc();
	Volume findByVolumeNumber(Integer volumeNumber);

	List<Volume> findByStatusTrueOrderByIdAsc();

    Volume findByVolumeNumberAndStatusIsTrue(Integer volumneNumber);

	@Query(value = "SELECT * FROM Volume t order by id desc limit 1", nativeQuery=true)
	Volume getNewVolume();

}

