package com.chains.pwqxfwjk.model;

// 2016-1-26 9:43:58 3.2.2.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TroubleLine 
 *
 * @author 
 * @version	2016-1-26 9:43:58
 */
@Entity
@Table(name = "trouble_line")
public class TroubleLine implements java.io.Serializable {

	/**
	 * 用一句话描述这个变量表示什么
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer id;

	/** 
	 * 线路特征.
	 */
	private String lineQuality;

	public TroubleLine() {
	}

	public TroubleLine(String lineQuality) {
		this.lineQuality = lineQuality;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/** 
	 * 线路特征.
	 */

	@Column(name = "line_quality", length = 200)
	public String getLineQuality() {
		return this.lineQuality;
	}

	/** 
	 * 线路特征.
	 */
	public void setLineQuality(String lineQuality) {
		this.lineQuality = lineQuality;
	}

}
