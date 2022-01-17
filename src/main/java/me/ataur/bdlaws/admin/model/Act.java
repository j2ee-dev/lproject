package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.*;
import me.ataur.bdlaws.admin.audit.ActEntityListener;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import java.util.*;


@Entity
@Table(name = "Act")
@EqualsAndHashCode
//@ToString(exclude = {"shortTitleFootnoteList","actRoleFootnoteList","preambleFootnoteList","actScheduleList"})
@EntityListeners(ActEntityListener.class)
public class Act implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private Integer id;

    @Getter
    @Setter
    @JoinColumn(name = "volume", referencedColumnName = "id")
    @OneToOne
    @JsonView(DataTablesOutput.View.class)
    private Volume volume;

    @Getter
    @Setter
    @Column(name = "shortTitle")
    @JsonView(DataTablesOutput.View.class)
    private String shortTitle;

    @Getter
    @Setter
    @Column(name = "shortTitleType")
    @JsonView(DataTablesOutput.View.class)
    private String shortTitleType;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ShortTitleFootnoteForAct> shortTitleFootnoteList;

    @Getter
    @Setter
    @Column(name = "number")
    @JsonView(DataTablesOutput.View.class)
    private String number;


    @Getter
    @Setter
    @Column(name = "year")
    @JsonView(DataTablesOutput.View.class)
    private String year;


    @Getter
    @Setter
    @Column(name = "page")
    @JsonView(DataTablesOutput.View.class)
    private String page;


    @Getter
    @Setter
    @Column(name = "publishDate")
    @JsonView(DataTablesOutput.View.class)
    private String publishDate;


    @Getter
    @Setter
    @Column(name = "actRole")
    @JsonView(DataTablesOutput.View.class)
    private String actRole;


    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ActRoleFootnoteForAct> actRoleFootnoteList;

    @Getter
    @Setter
    @Column(name = "preamble")
    @JsonView(DataTablesOutput.View.class)
    private String preamble;


    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @JsonManagedReference
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<PreambleFootnoteForAct> preambleFootnoteList;

    @Getter
    @Setter
    @Column(name = "preambleAttachment")
    @JsonView(DataTablesOutput.View.class)
    private String preambleAttachment;


    @Getter
    @Setter
    @Column(name = "displayPreambleText")
    @JsonView(DataTablesOutput.View.class)
    private Boolean displayPreambleText;


    @Getter
    @Setter
    @Column(name = "isOrdinance")
    @JsonView(DataTablesOutput.View.class)
    private Boolean isOrdinance;


    @Getter
    @Setter
    @Column(name = "isBanglaAct")
    @JsonView(DataTablesOutput.View.class)
    private Boolean isBanglaAct;


    @Getter
    @Setter
    @Column(name="Love")
    @JsonView(DataTablesOutput.View.class)
    private Integer love;

    @Getter
    @Setter
    @Column(name = "orderNo")
    @JsonView(DataTablesOutput.View.class)
    private Integer orderNo;


    @Getter
    @Setter
    @Column(name = "status")
    @JsonView(DataTablesOutput.View.class)
    private Boolean status;

    @Getter
    @Setter
    @Column(name = "MappingAct")
    @JsonView(DataTablesOutput.View.class)
    private Integer mappingAct;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonManagedReference
    //@Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ActScheduleForAct> actScheduleList;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonManagedReference
    //@Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ActRulesRegulation> actRulesRegulationList;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonManagedReference
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ActGazettedCopyForAct> actGazettedCopyList;

    @Getter
    @Setter
    @Column(name = "actAttachment")
    @JsonView(DataTablesOutput.View.class)


    private String actAttachment;

    @Getter
    @Setter
    @Fetch(FetchMode.SUBSELECT)
    //@JsonInclude(JsonInclude.Include.NON_EMPTY)
    //@JsonManagedReference
    //@Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "act", cascade = CascadeType.ALL)
    @JsonView(DataTablesOutput.View.class)
    private List<ActAttachment> actAttachmentList;

    @Getter
    @Setter
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "actId", cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonView(DataTablesOutput.View.class)
    private RepealedAct repealedAct;


    @Getter
    @Setter
    @Column(name = "CreatedBy")
    @JsonView(DataTablesOutput.View.class)
    private String createdBy;

    @Getter
    @Setter
    @Column(name = "CreatedAt")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonView(DataTablesOutput.View.class)
    private Date createdAt;

    @Getter
    @Setter
    @Column(name = "UpdatedBy")
    @JsonView(DataTablesOutput.View.class)
    private String updatedBy;

    @Getter
    @Setter
    @Column(name = "UpdatedAt")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonView(DataTablesOutput.View.class)
    private Date updatedAt;

    public Act() {
        super();
    }


}

