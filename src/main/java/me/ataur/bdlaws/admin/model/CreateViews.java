package me.ataur.bdlaws.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CreateViews")
public class CreateViews {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter
	private String url;

	@Getter
	@Setter
	private String title;


}
