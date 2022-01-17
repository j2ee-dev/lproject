package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;

import me.ataur.bdlaws.admin.audit.ActPartEntityListener;
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
@Table(name="ActPart")
//@EqualsAndHashCode
//@ToString(exclude = {"partNoFootnoteList","partNameFootnoteList"})
@EntityListeners(ActPartEntityListener.class)
public class ActPart implements Serializable{

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
	@Column(name="partNo")
	@JsonView(DataTablesOutput.View.class)
	private String partNo;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actPart",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<PartNoFootnoteForActPart> partNoFootnoteList;
					
	@Getter
	@Setter
	@Column(name="partName")
	@JsonView(DataTablesOutput.View.class)
	private String partName;
	
	
	@Getter
	@Setter
	@Fetch(FetchMode.SUBSELECT)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@JsonManagedReference
    @Valid
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "actPart",cascade = CascadeType.ALL)
	@JsonView(DataTablesOutput.View.class)
	private List<PartNameFootnoteForActPart> partNameFootnoteList;
					
	@Getter
	@Setter
	@Column(name="partOrder")
	@JsonView(DataTablesOutput.View.class)
	private Integer  partOrder;
	
	
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

    public ActPart() {
        super();
    }


}