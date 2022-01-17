package me.ataur.bdlaws.website.model;

import lombok.Data;

/**
 * @author Amran
 */
@Data
public class AdvancedSearchForm {
    private Integer volume;
    private String year;
    private String actNo;
    private String partNo;
    private String chapterNo;
    private String sectionNo;
    public AdvancedSearchForm(){

    }
}