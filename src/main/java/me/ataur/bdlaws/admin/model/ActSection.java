package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;

import me.ataur.bdlaws.admin.audit.ActSectionEntityListener;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;


@Entity
@Table(name="ActSection")
//@EqualsAndHashCode
//@ToString(exclude = {"sectionNameFootnoteList","sectionHeadFootnoteList","sectionDescriptionFootnoteList","sectionTableFootnoteList"})
@EntityListeners(ActSectionEntityListener.class)
public class ActSection implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    @Setter
    @JsonView(DataTablesOutput.View.class)
    private Integer id;

    
	@Getter
    @Setter
	@JoinColumn(name="volume", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private Volume volume;
	
	@Getter
    @Setter
	@JoinColumn(name="actId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private Act actId;
	
	@Getter
    @Setter
	@JoinColumn(name="partId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private ActPart partId;
	
	@Getter
    @Setter
	@JoinColumn(name="chapterId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private ActChapter chapterId;


	@Getter
	@Setter
	@Column(name="sectionNo")
	@JsonView(DataTablesOutput.View.class)
	private String sectionNo;
	
	
	@Getter
	@Setter
	@Column(name="sectionName")
	@JsonView(DataTablesOutput.View.class)
	private String sectionName;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actSection",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<SectionNameFootnoteForActSection> sectionNameFootnoteList;

	@Getter
	@Setter
	@Column(name="sectionHead")
	@JsonView(DataTablesOutput.View.class)
	private String sectionHead;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actSection",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<SectionHeadFootnoteForActSection> sectionHeadFootnoteList;
					
	@Getter
	@Setter
	@Column(name="sectionDescription")
	@JsonView(DataTablesOutput.View.class)
	private String sectionDescription;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actSection",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<SectionDescriptionFootnoteForActSection> sectionDescriptionFootnoteList;
					
	@Getter
	@Setter
	@Column(name="sectionTable")
	@JsonView(DataTablesOutput.View.class)
	private String sectionTable;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actSection",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<SectionTableFootnoteForActSection> sectionTableFootnoteList;
					
	@Getter
	@Setter
	@Column(name="updateNote")
	@JsonView(DataTablesOutput.View.class)
	private String updateNote;
	
	
	@Getter
	@Setter
	@Column(name="attachment")
	@JsonView(DataTablesOutput.View.class)
	private String attachment;
	
	
	@Getter
	@Setter
	@Column(name="status")
	@JsonView(DataTablesOutput.View.class)
	private Boolean status;
	
	

    
    
    @Getter
    @Setter
    @Column(name="CreatedBy")
    @JsonView(DataTablesOutput.View.class)
    private String  createdBy;
            
    @Getter
    @Setter
    @Column(name="CreatedAt")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonView(DataTablesOutput.View.class)
    private Date createdAt;
            
    @Getter
    @Setter
    @Column(name="UpdatedBy")
    @JsonView(DataTablesOutput.View.class)
    private String  updatedBy;
            
    @Getter
    @Setter
    @Column(name="UpdatedAt")
    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonView(DataTablesOutput.View.class)
    private Date updatedAt;

    public ActSection() {
        super();
    }


}