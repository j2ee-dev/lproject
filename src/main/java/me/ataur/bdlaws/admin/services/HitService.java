package me.ataur.bdlaws.admin.services;

import java.util.List;
import java.util.Map;
/**
 * @author Amran
 */
public interface HitService {
   List<Map<String,Object>> report(String from,String to);

}
