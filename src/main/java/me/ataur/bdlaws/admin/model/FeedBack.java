package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;

import me.ataur.bdlaws.admin.audit.FeedBackEntityListener;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="FeedBack")
@EqualsAndHashCode
@ToString
@EntityListeners(FeedBackEntityListener.class)
public class FeedBack implements Serializable{

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
	@Column(name="Name")
	@JsonView(DataTablesOutput.View.class)
	private String name;
	
	
	@Getter
	@Setter
	@Column(name="Designation")
	@JsonView(DataTablesOutput.View.class)
	private String designation;
	
	
	@Getter
	@Setter
	@Column(name="Email")
	@JsonView(DataTablesOutput.View.class)
	private String email;
	
	
	@Getter
	@Setter
	@Column(name="Phone")
	@JsonView(DataTablesOutput.View.class)
	private String phone;
	
	
	@Getter
	@Setter
	@Column(name="Message")
	@JsonView(DataTablesOutput.View.class)
	private String message;
	
	

    
    
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

    public FeedBack() {
        super();
    }


}