package com.sof.mysqlPOJO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "userProfile")
public class UserProfile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer stackId;
	@Column
	private String name;
	@Column
	private byte [] image;
	@Column
	private String country;

	public enum Sex {male, female};
	@Enumerated(EnumType.STRING)
	@Column
	private Sex sex;
	@Column
	private Date dob;
	

	public UserProfile() {}




	public Integer getStackId () {
		return stackId;
	}

	public void setStackId (Integer stackId) {
		this.stackId = stackId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}


	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}


	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
