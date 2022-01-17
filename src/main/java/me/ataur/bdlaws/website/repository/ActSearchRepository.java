package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.ActSearch;
import me.ataur.bdlaws.website.model.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Amran
 */
public interface ActSearchRepository extends JpaRepository<ActSearch,String>{
    Page<ActSearch> findByTitleContainingIgnoreCase(String q, Pageable pageable);

    Page<ActSearch> findByActId_numberContainingIgnoreCase(String q, Pageable pageable);

    Page<ActSearch> findByActId_numberContainingIgnoreCaseOrActId_numberContainingIgnoreCase(String q1, String q11, Pageable pageable);

    Page<ActSearch> findByActId_yearContainingIgnoreCaseOrActId_yearContainingIgnoreCase(String q1, String q11, Pageable pageable);

    Page<ActSearch> findByActId_shortTitleContainingIgnoreCase(String q, Pageable pageable);

    Page<ActSearch> findByActId_numberIgnoreCaseOrActId_numberIgnoreCase(String q1, String q11, Pageable pageable);
}
