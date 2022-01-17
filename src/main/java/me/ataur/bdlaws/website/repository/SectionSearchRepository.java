package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.Search;
import me.ataur.bdlaws.website.model.SectionSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Amran
 */
public interface SectionSearchRepository extends JpaRepository<SectionSearch,String> {
    Page<SectionSearch> findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(String q1, String q2, Pageable pageable);
}
