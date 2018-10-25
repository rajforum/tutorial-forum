package com.sof.mysqlPOJO;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jayram-1018
 *
 */
@Entity
@Table(name = "userAccessMapping")
public class UserAccessMapping {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column
	private Integer userAccessMapId;
	
	@Column
	private Integer profile_stackId;
	
	@Column
	private String authToken;
	
	@Column
	private String authToken_creation;

	public Integer getProfile_stackId() {
		return profile_stackId;
	}

	public void setProfile_stackId(Integer profile_stackId) {
		this.profile_stackId = profile_stackId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getAuthToken_creation() {
		return authToken_creation;
	}

	public void setAuthToken_creation(String time) {
		this.authToken_creation = time;
	}

}
