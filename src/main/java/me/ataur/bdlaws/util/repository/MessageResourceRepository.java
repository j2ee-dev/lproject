package me.ataur.bdlaws.util.repository;

import me.ataur.bdlaws.util.model.MessageResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Amran
 */
@Repository
public interface MessageResourceRepository extends JpaRepository<MessageResource, Integer> {
    MessageResource findByCodeAndLocale(String code,String locale);
}

