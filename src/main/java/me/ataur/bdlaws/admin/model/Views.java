package me.ataur.bdlaws.admin.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Amran
 */
@Entity
public class Views {

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

	/*@Getter
	@Setter
	private String icon;*/

}
