package me.ataur.bdlaws.admin.repository;


import me.ataur.bdlaws.admin.model.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Amran
 */
@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

	Page<Permission> findAllByOrderByIdAsc(Pageable page);

	List<Permission> findAllByUsername(String username);

	/*@Query(value="SELECT u.editChecks,u.username FROM Permission u WHERE u.editChecks like REGEXP_REPLACE('editChecks','[0-9]','') AND u.username=:username",nativeQuery = true)*/

	//@Query(value = "SELECT u.editChecks,u.username FROM Permission u WHERE u.username=:username and u.editChecks like //REGEXP_REPLACE(CONCAT('%',:editChecks,'%'),'[0-9]','')",nativeQuery = true)
	@Query("SELECT u.views,u.editChecks FROM Permission u WHERE u.editChecks LIKE CONCAT('%',:editChecks,'%') AND u.username=:username")
	List<Permission> getUsersByEditUrls(@Param("editChecks") String editChecks, @Param("username") String username);


	@Query("SELECT u.username,u.createViews FROM Permission u WHERE u.createViews LIKE CONCAT('%',:createViews,'%') AND u.username=:username")
	List<Permission> getUsersByCreateUrls(@Param("createViews") String createViews, @Param("username") String username);

	@Query("SELECT u.views,u.username FROM Permission u WHERE u.views LIKE CONCAT('%',:views,'%') AND u.username=:username")
	List<Permission> getUsersByViewUrls(@Param("views") String views, @Param("username") String username);



	@Query("SELECT u.createViews,u.username FROM Permission u WHERE u.createViews LIKE CONCAT('%',1,'%') AND u.username=:username")
	List<Permission> getUsersByCreate(@Param("username") String username);


	@Query(value = "SELECT * FROM Permission p order by id desc limit 1", nativeQuery=true)
	Permission getPermittedUser();

    Permission findAllById(int id);
}
