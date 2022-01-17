package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
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
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;
import sun.util.resources.LocaleData;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.*;


@Entity
@Table(name="Hit")
@EqualsAndHashCode
@ToString
public class Hit implements Serializable{

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
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="hitDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonView(DataTablesOutput.View.class)
	private Date hitDate;

	@Column(name = "hitNumbers")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private Integer hitNumbers;

	@Column(name = "refreshHits")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private Integer refreshHits;

	@Getter
	@Setter
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fromDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fromDate;

	@Getter
	@Setter
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="toDate")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date toDate;

	/*@Getter
	@Setter
	@Column(name="ip")
	@JsonView(DataTablesOutput.View.class)
	private String ip;
	
	
	@Getter
	@Setter
	@Column(name="browser")
	@JsonView(DataTablesOutput.View.class)
	private String browser;
	
	
	@Getter
	@Setter
	@Column(name="browserVersion")
	@JsonView(DataTablesOutput.View.class)
	private String browserVersion;
	
	
	@Getter
	@Setter
	@Column(name="OperatingSystem")
	@JsonView(DataTablesOutput.View.class)
	private String operatingSystem;
	
	
	@Getter
	@Setter
	@Column(name="date")
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@JsonView(DataTablesOutput.View.class)
	private Date date;
	
	
	@Getter
	@Setter
	@Column(name="resource")
	@JsonView(DataTablesOutput.View.class)
	private String resource;
	
	
	@Getter
	@Setter
	@Column(name="query")
	@JsonView(DataTablesOutput.View.class)
	private String query;
	
	
	@Getter
	@Setter
	@Column(name="type")
	@JsonView(DataTablesOutput.View.class)
	private String type;*/
    
    
 /*   @Getter
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
    private Date updatedAt;*/

    public Hit() {
        super();
    }


}