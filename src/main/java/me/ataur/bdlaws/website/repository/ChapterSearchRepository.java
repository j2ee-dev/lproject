package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.ChapterSearch;
import me.ataur.bdlaws.website.model.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Amran
 */
public interface ChapterSearchRepository extends JpaRepository<ChapterSearch,String> {
    Page<ChapterSearch> findByTitleContainingIgnoreCaseOrTitleContainingIgnoreCase(String q1, String q2, Pageable pageable);
}
