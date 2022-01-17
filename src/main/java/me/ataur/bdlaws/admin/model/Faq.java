package me.ataur.bdlaws.admin.model;

/**
 * Created by imran
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;


import me.ataur.bdlaws.admin.audit.FaqEntityListener;
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
@Table(name="Faq")
@EqualsAndHashCode
@ToString
@EntityListeners(FaqEntityListener.class)
public class Faq implements Serializable{

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
	@Column(name="questionEnglish")
	@JsonView(DataTablesOutput.View.class)
	private String questionEnglish;
	
	
	@Getter
	@Setter
	@Column(name="questionBangla")
	@JsonView(DataTablesOutput.View.class)
	private String questionBangla;
	
	
	@Getter
	@Setter
	@Column(name="answerEnglish")
	@JsonView(DataTablesOutput.View.class)
	private String answerEnglish;
	
	
	@Getter
	@Setter
	@Column(name="answerBangla")
	@JsonView(DataTablesOutput.View.class)
	private String answerBangla;
	
	
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

    public Faq() {
        super();
    }


}