package com.sof.mysqlPOJO;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;


@Entity
@XmlRootElement
@Table(name = "userCredential")
public class UserCredential implements Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
	private Integer userId;
    @Column
	private String contact_no;
    @Column
	private String email;
    @Column
	private String password;
   
	public enum Role {admin, user};
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;

	
	public UserCredential() {}
	public Integer getUserID() {
		return userId;
	}

	public void setUserID(Integer userID) {
		this.userId = userId;
	}


	public String getContact_No() {
		return contact_no;
	}

	public void setContact_No(String contact_No) {
		this.contact_no = contact_no;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

/*    @Override
    public String toString() {
        return "UserCredential{" +
                "UserID=" + UserID +
                ", Contact_No='" + Contact_No + '\'' +
                ", Email='" + Email + '\'' +
                ", Role=" + Role +
                '}';
    }*/
}
