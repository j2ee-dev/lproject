package me.ataur.bdlaws.website.controller;
import me.ataur.bdlaws.admin.model.Hit;
import me.ataur.bdlaws.admin.repository.HitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Amran
 */
public class MyWebBaseController {
    private static final Logger baseLogger = LoggerFactory.getLogger(MyWebBaseController.class);
    @Autowired
    HitRepository hitRepository;

    public void  hitLogger(HttpServletRequest request){
        try{
            Hit hit = new Hit();


            String ipAddress=null;
            //String getWay = request.getHeader("VIA");   // Gateway
            ipAddress = request.getHeader("X-FORWARDED-FOR");   // proxy
            if(ipAddress==null)
            {
                ipAddress = request.getRemoteAddr();
            }

          /*  hit.setIp(ipAddress);
              UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));

            hit.setBrowser(userAgent.getBrowser().getName());
            hit.setBrowserVersion(userAgent.getBrowserVersion().getVersion());
            hit.setOperatingSystem(userAgent.getOperatingSystem().getName());
            hit.setResource(request.getRequestURI());
            hit.setQuery(request.getQueryString());
            hit.setType(request.getMethod());*/
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String localdate=sdf.format(new Date());
          if(hitRepository.findHitByDate(localdate)==null && request.getRequestURI().equals("/"))
          {
              hit.setHitDate(new Date());
              hit.setHitNumbers(1);
              hit.setRefreshHits(0);
              hitRepository.save(hit);
          }
          else if(!hitRepository.findHitByDate(localdate).equals(null) && request.getRequestURI().equals("/"))
          {
              Hit hitOld=hitRepository.findHitByDate(localdate);
              hitOld.setHitNumbers(hitOld.getHitNumbers()+1);
              hitRepository.save(hitOld);
          }
          else if(!hitRepository.findHitByDate(localdate).equals(null) && !request.getRequestURI().equals("/"))
          {
              Hit hitOld=hitRepository.findHitByDate(localdate);
              hitOld.setRefreshHits(hitOld.getRefreshHits()+1);
              hitRepository.save(hitOld);
          }

        }catch (Exception e){

        }
    }
}
