package me.ataur.bdlaws.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Amran
 */
@Entity
@Table(name = "EditViews")
public class EditViews {

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
