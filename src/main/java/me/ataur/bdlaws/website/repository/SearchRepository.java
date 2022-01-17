package me.ataur.bdlaws.website.repository;

import me.ataur.bdlaws.website.model.Search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Amran
 */
public interface SearchRepository extends JpaRepository<Search,String> {
    Page<Search> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String q, String q1, Pageable pageable);
}
