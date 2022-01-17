package me.ataur.bdlaws.admin.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private Integer id;


	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private String username;

	@Getter
	@Setter
	@JsonView(DataTablesOutput.View.class)
	private String password;

	@Getter
	@Setter
	@ManyToMany
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;




	@Getter
	@Setter
	@Column(name="FullName")
	@JsonView(DataTablesOutput.View.class)
	private String fullName;


	@Getter
	@Setter
	@Column(name="Designation")
	@JsonView(DataTablesOutput.View.class)
	private String designation;


	@Getter
	@Setter
	@Column(name="Phone",unique=true)
	@JsonView(DataTablesOutput.View.class)
	private String phone;


	@Getter
	@Setter
	@Column(name="Email",unique=true)
	@JsonView(DataTablesOutput.View.class)
	private String email;

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
}
