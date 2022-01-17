package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.VolumeHistory;
import me.ataur.bdlaws.admin.model.Volume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * @author Amran
 */
@Repository
public interface VolumeHistoryRepository extends JpaRepository<VolumeHistory, Integer> {
	List<VolumeHistory> findAllByOrderByIdAsc();
	List<VolumeHistory> findAllByOrderByIdDesc();
	Page<VolumeHistory> findAllByOrderByIdAsc(Pageable page);
	Page<VolumeHistory> findAllByOrderByIdDesc(Pageable page);

/*	List<Volume> findByCreatedByOrderByIdAsc(String createdBy);
	List<Volume> findByCreatedByOrderByIdDesc(String createdBy);
	Page<Volume> findByCreatedByOrderByIdAsc(String createdBy, Pageable page);
	Page<Volume> findByCreatedByOrderByIdDesc(String createdBy, Pageable page);*/



}

