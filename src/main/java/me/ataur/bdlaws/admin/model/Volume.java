package me.ataur.bdlaws.admin.model;

/**
 * Created by imran
 */
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import com.fasterxml.jackson.annotation.*;
import me.ataur.bdlaws.admin.audit.VolumeEntityListener;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Entity
@Table(name="Volume")
//@EqualsAndHashCode
//@ToString
@EntityListeners(VolumeEntityListener.class)
public class Volume implements Serializable{

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
	@Column(name="volumeNumber",unique=true)
	@JsonView(DataTablesOutput.View.class)
	private Integer  volumeNumber;
	
	
	@Getter
	@Setter
	@Column(name="volumeName")
	@JsonView(DataTablesOutput.View.class)
	private String volumeName;
	
	
	@Getter
	@Setter
	@Column(name="volumeHead")
	@JsonView(DataTablesOutput.View.class)
	private String volumeHead;
	
	
	@Getter
	@Setter
	@Column(name="description")
	@JsonView(DataTablesOutput.View.class)
	private String description;

	
	@Getter
	@Setter
	@Column(name="volumeYear")
	@JsonView(DataTablesOutput.View.class)
	private String volumeYear;
	
	
	@Getter
	@Setter
	@Column(name="Status")
	@JsonView(DataTablesOutput.View.class)
	private Boolean status;
	
	
	@Getter
	@Setter
	@Column(name="isBangla")
	@JsonView(DataTablesOutput.View.class)
	private Boolean isBangla;

    
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

    public Volume() {
        super();
    }


}