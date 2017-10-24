package com.chains.pwqxfwjk.model;
// Generated 2016-6-12 15:15:51 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "serviceman")
public class Serviceman implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String wqtMemberId;
	private String missionId;
	private String deptName;
	private String phoneNumber;
	private String post;
	private String terminalName;

	public Serviceman() {
	}

	public Serviceman(String missionId) {
		this.missionId = missionId;
	}
	
	public Serviceman(String wqtMemberId, String missionId, String deptName, String phoneNumber, String post,
			String terminalName) {
		this.wqtMemberId = wqtMemberId;
		this.missionId = missionId;
		this.deptName = deptName;
		this.phoneNumber = phoneNumber;
		this.post = post;
		this.terminalName = terminalName;
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

	@Column(name = "wqt_member_id", length = 50)
	public String getWqtMemberId() {
		return this.wqtMemberId;
	}

	public void setWqtMemberId(String wqtMemberId) {
		this.wqtMemberId = wqtMemberId;
	}

	@Column(name = "mission_id", length = 50)
	public String getMissionId() {
		return this.missionId;
	}

	public void setMissionId(String missionId) {
		this.missionId = missionId;
	}

	@Column(name = "dept_name", length = 20)
	public String getDeptName() {
		return this.deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@Column(name = "phone_number", length = 16)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "post", length = 5)
	public String getPost() {
		return this.post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	@Column(name = "terminal_name", length = 30)
	public String getTerminalName() {
		return this.terminalName;
	}

	public void setTerminalName(String terminalName) {
		this.terminalName = terminalName;
	}

}
