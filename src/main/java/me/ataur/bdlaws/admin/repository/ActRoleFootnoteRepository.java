package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ActRoleFootnote;
/*IMPORT_MODEL*/
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
public interface ActRoleFootnoteRepository extends JpaRepository<ActRoleFootnote, Integer> {
	List<ActRoleFootnote> findAllByOrderByIdAsc();
	List<ActRoleFootnote> findAllByOrderByIdDesc();
	Page<ActRoleFootnote> findAllByOrderByIdAsc(Pageable page);
	Page<ActRoleFootnote> findAllByOrderByIdDesc(Pageable page);

	List<ActRoleFootnote> findByCreatedByOrderByIdAsc(String createdBy);
	List<ActRoleFootnote> findByCreatedByOrderByIdDesc(String createdBy);
	Page<ActRoleFootnote> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<ActRoleFootnote> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	@Query(value = "SELECT * FROM ActRoleFootnote t WHERE ActId=?1 AND FootNoteNumber>=?2", nativeQuery = true)
    List<ActRoleFootnote> getFootnoteWithGrateaterOrder(Integer id, Integer footnoteNumber);


	
	/*EXTRA*/
			

}

