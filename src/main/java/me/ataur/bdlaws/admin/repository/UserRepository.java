package me.ataur.bdlaws.admin.repository;


/*IMPORT_MODEL*/
import me.ataur.bdlaws.admin.model.User;
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
public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAllByOrderByIdAsc();
	List<User> findAllByOrderByIdDesc();
	Page<User> findAllByOrderByIdAsc(Pageable page);
	Page<User> findAllByOrderByIdDesc(Pageable page);

	/*List<User> findByCreatedByOrderByIdAsc(String createdBy);
	List<User> findByCreatedByOrderByIdDesc(String createdBy);
	Page<User> findByCreatedByOrderByIdAsc(String createdBy,Pageable page);
	Page<User> findByCreatedByOrderByIdDesc(String createdBy,Pageable page);*/


	//User findByPasswordRecoveryCode(String passwordRecoveryCode);
	List<User> findAllByOrderByUsernameAsc();
	User findByUsername(String username);
	//User findByPhone(String phone);
	//User findByEmail(String email);
	
	/*EXTRA*/
	@Query(value = "SELECT * FROM user t order by id desc limit 1", nativeQuery=true)
	User getNewUser();
			

}

