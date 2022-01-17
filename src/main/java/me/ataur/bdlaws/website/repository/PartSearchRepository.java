package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.ChapterSearch;
import me.ataur.bdlaws.website.model.PartSearch;
import me.ataur.bdlaws.website.model.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Amran
 */
public interface PartSearchRepository extends JpaRepository<PartSearch,String> {

    Page<PartSearch> findByTitleContainingIgnoreCase(String q, Pageable pageable);

    Page<PartSearch> findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(String q1, String q2, Pageable pageable);
}
