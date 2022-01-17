package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import me.ataur.bdlaws.admin.audit.ActScheduleForActEntityListener;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="ActRulesRegulation")
//@EqualsAndHashCode
//@ToString
//@EntityListeners(ActScheduleForActEntityListener.class)
public class ActRulesRegulation implements Serializable{

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
    @JoinColumn(name="ActId", referencedColumnName="id")
    @JsonBackReference
    @JsonView(DataTablesOutput.View.class)
    private Act act;

	@Getter
	@Setter
	@Column(name="title")
	@JsonView(DataTablesOutput.View.class)
	private String title;


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

    public ActRulesRegulation() {
        super();
    }


}