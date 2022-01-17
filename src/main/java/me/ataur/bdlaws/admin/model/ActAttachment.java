package me.ataur.bdlaws.admin.model;

/**
 * Created by imran hossain
 */


import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name="ActAttachment")
public class ActAttachment implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @Column(name="status")
    private Boolean status;

	@Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ActId", referencedColumnName="id")
    private Act act;


	@Getter
	@Setter
	@Column(name="attachment")
	private String attachment;

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


    public ActAttachment() {
        super();
    }


}