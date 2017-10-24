package com.chains.pwqxfwjk.util;


public class LineInfoQueryParam extends QueryParam {
	private String belongLine;

	private String belongBranch;
	
	private Integer id;
	/**
	 * @since belongLine
	 * @return String
	 */
	
	public String getBelongLine() {
		return belongLine;
	}

	/**
	 * @since belongLine
	 * @param belongLine
	 * @return void
	 */
	
	public void setBelongLine(String belongLine) {
		this.belongLine = belongLine;
	}

	/**
	 * @since belongBranch
	 * @return String
	 */
	
	public String getBelongBranch() {
		return belongBranch;
	}

	/**
	 * @since belongBranch
	 * @param belongBranch
	 * @return void
	 */
	
	public void setBelongBranch(String belongBranch) {
		this.belongBranch = belongBranch;
	}

	/**
	 * @since id
	 * @return Integer
	 */
	
	public Integer getId() {
		return id;
	}

	/**
	 * @since id
	 * @param id
	 * @return void
	 */
	
	public void setId(Integer id) {
		this.id = id;
	}
}
