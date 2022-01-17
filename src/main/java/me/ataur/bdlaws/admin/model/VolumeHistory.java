package me.ataur.bdlaws.admin.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import me.ataur.bdlaws.admin.audit.Action;
import me.ataur.bdlaws.admin.model.Volume;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

/**
 * @author Imran Hossain
 */

@Entity
@Table(name = "logtable")
@EntityListeners(AuditingEntityListener.class)
public class VolumeHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private Integer id;

    @Enumerated(STRING)
	@Column(name = "action")
	@Getter
	@Setter
	@JsonFormat(shape=JsonFormat.Shape.STRING)
	@JsonView(DataTablesOutput.View.class)
	private Action actions;

	@Column(name = "tableName")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private String tableName;

	@Column(name = "Content_id")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private Integer file;


/*	@Column(name = "content")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private String fileContent;*/

	@CreatedBy
	@Column(name = "modifiedby")
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private String modifiedBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(name = "modificationDate")
	@JsonFormat(shape=JsonFormat.Shape.STRING ,pattern = "dd-MM-yyyy HH:mm:ss")
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	@JsonView(DataTablesOutput.View.class)
	private Date modifiedDate;



	public VolumeHistory() {
	}

	public VolumeHistory(Volume file, Action action, String cl) {
		Integer fileID=file.getId();
		this.file = fileID;
		this.actions = action;
		this.tableName=cl;
		//this.fileContent=file.toString();
		this.modifiedDate=new Date();
		this.modifiedBy=SecurityContextHolder.getContext().getAuthentication().getName();
		//this.modifiedBy=user;
	}



}