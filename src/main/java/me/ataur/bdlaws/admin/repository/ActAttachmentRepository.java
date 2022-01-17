package me.ataur.bdlaws.admin.repository;


import me.ataur.bdlaws.admin.model.ActAttachment;
import me.ataur.bdlaws.admin.model.ActGazettedCopyForAct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/*IMPORT_MODEL*/

/**
 * Created by Md. Amran Hossain
 */
@Repository
public interface ActAttachmentRepository extends JpaRepository<me.ataur.bdlaws.admin.model.ActAttachment, Integer> {

    @Query(value = "SELECT * FROM ActAttachment t WHERE ActId=?1 order by id", nativeQuery=true)
    List<ActAttachment> findAllByActId(Integer actId);

}

