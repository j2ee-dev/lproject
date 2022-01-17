package me.ataur.bdlaws.admin.model;

/**
 * Created by imran
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;

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
@Table(name="SectionTableFootnote")
@EqualsAndHashCode
@ToString
public class SectionTableFootnoteForActSection implements Serializable{

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="SectionId", referencedColumnName="id")
    @JsonBackReference
    @JsonView(DataTablesOutput.View.class)
    private ActSection actSection;
	@Getter
	@Setter
	@Column(name="Footnote")
	@JsonView(DataTablesOutput.View.class)
	private String footnote;
	
	
	@Getter
	@Setter
	@Column(name="FootnoteNumber")
	@JsonView(DataTablesOutput.View.class)
	private Integer  footnoteNumber;
	
	
	@Getter
	@Setter
	@Column(name="FootnoteSign")
	@JsonView(DataTablesOutput.View.class)
	private String footnoteSign;
	
	
	@Getter
	@Setter
	@Column(name="status")
	@JsonView(DataTablesOutput.View.class)
	private Boolean status;
	
	

    /*SET_USER_ROLE*/
    
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

    public SectionTableFootnoteForActSection() {
        super();
    }


}