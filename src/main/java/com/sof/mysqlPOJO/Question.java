package com.sof.mysqlPOJO;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "question")
public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String title;
	@Column
	private String bodyText;
	@Column
	private Integer authorId;
	@Column
	private Integer vote;
	
	public Question() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

	public String getBodyText() {
		return bodyText;
	}
	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}
	

	public Integer getAuthorId() {
		return authorId;
	}
	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}
	

	public Integer getVote() {
		return vote;
	}
	public void setVote(Integer vote) {
		this.vote = vote;
	}
	
	
}
