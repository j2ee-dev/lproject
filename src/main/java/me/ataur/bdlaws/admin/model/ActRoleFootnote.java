package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.*;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import javax.persistence.*;
import java.util.*;


@Entity
@Table(name="ActRoleFootnote")
//@EqualsAndHashCode
//@ToString
public class ActRoleFootnote implements Serializable{

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
	@JoinColumn(name="ActId", referencedColumnName="id")
	@OneToOne
	@JsonView(DataTablesOutput.View.class)
	private Act actId;
	
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

    public ActRoleFootnote() {
        super();
    }


}