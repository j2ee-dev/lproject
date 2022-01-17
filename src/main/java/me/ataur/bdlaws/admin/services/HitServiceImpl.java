package me.ataur.bdlaws.admin.services;

import me.ataur.bdlaws.admin.model.Hit;
import me.ataur.bdlaws.admin.repository.HitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author Amran
 */
@Service
public class HitServiceImpl implements HitService {
 @Autowired
 HitRepository hitRepository;
   @Override
   public List<Map<String, Object>> report(String from,String to) {
      List<Map<String, Object>> result=new ArrayList<Map<String,Object>>();
      for (Hit hit:hitRepository.getAllBetweenDatePdf(from,to)) {
         Map<String,Object> item=new HashMap<String,Object>();
         item.put("id",hit.getId());
         //item.put("hitDate",hit.getHitDate());
         item.put("hitNumbers",hit.getHitNumbers());
         item.put("refreshHits",hit.getRefreshHits());
          System.out.println("Hit print"+hit);
         result.add(item);
      }
      return result;
   }
}
