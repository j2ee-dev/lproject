package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.Message;
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
public interface MessageRepository extends JpaRepository<Message, Integer> {
	List<Message> findAllByOrderByIdAsc();
	List<Message> findAllByOrderByIdDesc();
	Page<Message> findAllByOrderByIdAsc(Pageable page);
	Page<Message> findAllByOrderByIdDesc(Pageable page);

	List<Message> findByCreatedByOrderByIdAsc(String createdBy);
	List<Message> findByCreatedByOrderByIdDesc(String createdBy);
	Page<Message> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<Message> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);

	Message findByCodeAndLocale(String code, String locale);

    Message findByLocaleAndCode(String locale, String code);


	
	/*EXTRA*/
			

}

