package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.AdvancedSearch;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author Amran
 */
@Repository
public interface AdvancedSearchRepository extends JpaRepository<AdvancedSearch,String>, JpaSpecificationExecutor<AdvancedSearch> {


}
