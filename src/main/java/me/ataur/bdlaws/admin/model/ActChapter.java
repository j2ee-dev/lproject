package me.ataur.bdlaws.admin.model;

/**
 * Created by imran
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;

import me.ataur.bdlaws.admin.audit.ActChapterEntityListener;
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
@Table(name="ActChapter")
//@EqualsAndHashCode
//@ToString(exclude = {"chapterNoFootnoteList","chapterNameFootnoteList"})
@EntityListeners(ActChapterEntityListener.class)
public class ActChapter implements Serializable{

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
	@JoinColumn(name="ActId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private Act actId;
	
	@Getter
    @Setter
	@JoinColumn(name="PartId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private ActPart partId;
	
	@Getter
	@Setter
	@Column(name="chapterNo")
	@JsonView(DataTablesOutput.View.class)
	private String chapterNo;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actChapter",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<ChapterNoFootnoteForActChapter> chapterNoFootnoteList;
					
	@Getter
	@Setter
	@Column(name="chapterName")
	@JsonView(DataTablesOutput.View.class)
	private String chapterName;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actChapter",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<ChapterNameFootnoteForActChapter> chapterNameFootnoteList;
					
	@Getter
	@Setter
	@Column(name="chapterOrder")
	@JsonView(DataTablesOutput.View.class)
	private Integer  chapterOrder;
	
	
	@Getter
	@Setter
	@Column(name="Attachment")
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

    public ActChapter() {
        super();
    }


}