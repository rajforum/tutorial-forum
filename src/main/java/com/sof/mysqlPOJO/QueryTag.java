package com.sof.mysqlPOJO;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "queryTopic")
public class QueryTag implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Integer id;
	@Column
	private String tagName;
	
	public QueryTag() {}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	
}
