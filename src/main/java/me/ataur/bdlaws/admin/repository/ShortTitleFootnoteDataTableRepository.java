package me.ataur.bdlaws.admin.repository;

import me.ataur.bdlaws.admin.model.ShortTitleFootnote;
/*IMPORT_MODEL*/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
/**
 * @author Amran
 */
@Repository
public interface ShortTitleFootnoteDataTableRepository extends DataTablesRepository<ShortTitleFootnote, Integer>{
}

