package me.ataur.bdlaws.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Amran
 */
/*
 *
 * <ata:footnote text="${act.shortTitle}" footnotes="${act.shortTitleFootnoteList}" footnoteNo="${footnoteNo}" count="count" />
 *
 */
public class Footnote extends SimpleTagSupport {
    private String output;
    private String text;
    private Integer footnoteNo;
    private String count;
    private String footnoteTotal;
    private String locale;
    private List<Object> footnotes;
    private static final Logger logger = LoggerFactory.getLogger(Footnote.class);
    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        if(footnotes.size() == 0 || text==null || text.trim().length()==0){
            out.println(text);
            getJspContext().setAttribute(count, footnoteNo);
            getJspContext().setAttribute(footnoteTotal, "");
            return;
        }

        //logger.info("Yes! We got "+footnotes.size()+" footnotes");


        String output = text;
        String footnoteString = "";
        String prenode = "";
        String fnote = "";
        Set<String> footnoteSigns = new HashSet<>();
        Map<Integer,String> footnoteGroups = new HashMap<>();
        Map<Integer,String> footnoteSignGroups = new HashMap<>();
        Map<Integer,Integer> footnoteNumberGroups = new HashMap<>();
        try {
            //Get the writer object for output.


            for (Object myFootnote: footnotes
                    ) {

                Field footnote = myFootnote.getClass().getDeclaredField("footnote");
                footnote.setAccessible(true);
                String footnoteValue =(String) footnote.get(myFootnote);


                Field footnoteSign = myFootnote.getClass().getDeclaredField("footnoteSign");
                footnoteSign.setAccessible(true);
                String footnoteSignValue =(String) footnoteSign.get(myFootnote);

                Field footnoteNumber = myFootnote.getClass().getDeclaredField("footnoteNumber");
                footnoteNumber.setAccessible(true);
                Integer footnoteNumberValue =(Integer) footnoteNumber.get(myFootnote);

                Field status = myFootnote.getClass().getDeclaredField("status");
                status.setAccessible(true);
                Boolean statusValue =(Boolean) status.get(myFootnote);


                if(statusValue==true){
                    if(footnoteSignValue==null){
                        if(footnoteNumberValue==1){
                            //prenode = "<span class='footnote' data-toggle='tooltip' title='"+footnoteValue+"'><h5 class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></h5></span>";
                            /*prenode = "<span class='footnote test-tooltip'  title='"+footnoteValue+"'><h5 class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></h5></span>";*/
                            prenode = "<span class='footnote'   title='"+footnoteValue+"'><span class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></span></span>";
                            //prenode = "<span class='footnote'   title='"+footnoteValue+"'><h5 class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></h5></span>";
                            footnoteString += "<li class='footnoteList'><h6 class='word-formet'> <sup class='"+locale+"'>"+(footnoteNo)+"</sup> </h6> "+footnoteValue+"</li>";
                            footnoteNo +=1;
                        }
                    }else{
                        footnoteSigns.add(Pattern.quote(footnoteSignValue));
                        footnoteGroups.put(footnoteNumberValue,footnoteValue);
                        footnoteSignGroups.put(footnoteNumberValue,footnoteSignValue);
                        footnoteNumberGroups.put(footnoteNumberValue,footnoteNumberValue);
                    }
                }
            }

            //logger.info(footnoteSignGroups.toString());
            String regex = "("+StringUtils.join(footnoteSigns, "|")+")";
            //logger.error("regex = "+ regex);
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(text);

            Integer i=1;
            Integer fnoteLength = 0;

            if(prenode!=""){
                i++;
            }
            while (matcher.find()) {

//                System.out.printf("\"%s\" starting at " +
//                                "index %d & ending index %d.%n",
//                        matcher.group(), matcher.start(), matcher.end());
//
//                System.out.println("Footnote Sign Matcher = "+matcher.group());
//                System.out.println("Footnote Sign Group = "+footnoteSignGroups.get(i));

                if(footnoteGroups.containsKey(i) && matcher.group().equals(footnoteSignGroups.get(i))){
                    if(footnoteSignGroups.get(i).equals("[")){

                        //fnote = "<span class='footnote' data-toggle='tooltip' title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></h5></span>";
                        /*fnote = "<span class='footnote test-tooltip' title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></h5></span>";*/
                        fnote = "<span class='footnote'  title='"+footnoteGroups.get(i)+"'><span class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></span></span>";
                       // fnote = "<span class='footnote'  title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></h5></span>";
                        //logger.info(fnoteLength+ "=fnoteLength");
                        Integer position = fnoteLength+matcher.start();

                        output = output.substring(0, position) + fnote + output.substring(position, output.length());

                        fnoteLength+= fnote.length();

                        footnoteString += "<li class='footnoteList'><h6 class='word-formet'> <sup class='"+locale+"'>"+(footnoteNo)+"</sup> </h6>"+footnoteGroups.get(i)+"</li>";

                        footnoteNo +=1;

                    }else {
                          /* if( footnoteSignGroups.get(i).equals("♠")||footnoteSignGroups.get(i).equals("♣")||footnoteSignGroups.get(i).equals("♦"))*/
                        //fnote = "<span class='footnote' data-toggle='tooltip' href='#' title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'>"+(footnoteNo)+"</sup></h5></span>";
                        /*fnote = "<span class='footnote test-tooltip' href='#' title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></h5></span>";*/
                         fnote = "<span class='footnote' href='#' title='"+footnoteGroups.get(i)+"'><span class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></span></span>";
                       // fnote = "<span class='footnote' href='#' title='"+footnoteGroups.get(i)+"'><h5 class='word-formet'><sup class='"+locale+"'><a href='"+footnoteNumberGroups.get(i)+"' >"+(footnoteNo)+"</a></sup></h5></span>";
                        Integer position = fnoteLength+matcher.start();

                        //output = output.substring(0, position) + fnote + output.substring(position+footnoteSignGroups.get(i).length(), output.length());
                        //System.out.println(position+" "+footnoteSignGroups.get(i).length()+" "+output.length());
                        System.out.println(position+1+" "+output.length());

                        output = output.substring(0, position) + fnote + output.substring(position, output.length());

                        fnoteLength+= fnote.length();

                        footnoteString += "<li class='footnoteList'><h6 class='word-formet'> <sup class='"+locale+"'>"+(footnoteNo)+"</sup> </h6>"+footnoteGroups.get(i)+"</li>";

                        footnoteNo +=1;
                    }
                }
                i++;
            }


            output = prenode+output;
            //logger.info(output);
            out.println(output);
            //getJspContext().setAttribute(this.output,output );
            getJspContext().setAttribute(count, footnoteNo);
            getJspContext().setAttribute(footnoteTotal, footnoteString);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        //logger.error("***********************************************");
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getFootnoteNo() {
        return footnoteNo;
    }

    public void setFootnoteNo(Integer footnoteNo) {
        this.footnoteNo = footnoteNo;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getFootnoteTotal() {
        return footnoteTotal;
    }

    public void setFootnoteTotal(String footnoteTotal) {
        this.footnoteTotal = footnoteTotal;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }


    public List<Object> getFootnotes() {
        return footnotes;
    }

    public void setFootnotes(List<Object> footnotes) {
        this.footnotes = footnotes;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
        System.out.println(output);
    }
}
