package com.chains.pwqxfwjk.util;


public class PersonPositionQueryParam {
	private String memberName;
	private String phone;
	private Integer currentPage;
	private Integer pageSize;
	private Integer pages;

	/**
	 * @since memberName
	 * @return String
	 */
	
	public String getMemberName() {
		return memberName;
	}



	/**
	 * @since memberName
	 * @param memberName
	 * @return void
	 */
	
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}



	/**
	 * @since phone
	 * @return String
	 */
	
	public String getPhone() {
		return phone;
	}



	/**
	 * @since phone
	 * @param phone
	 * @return void
	 */
	
	public void setPhone(String phone) {
		this.phone = phone;
	}



	/**
	 * @since currentPage
	 * @return Integer
	 */
	
	public Integer getCurrentPage() {
		return currentPage;
	}



	/**
	 * @since currentPage
	 * @param currentPage
	 * @return void
	 */
	
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}



	/**
	 * @since pageSize
	 * @return Integer
	 */
	
	public Integer getPageSize() {
		return pageSize;
	}



	/**
	 * @since pageSize
	 * @param pageSize
	 * @return void
	 */
	
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}



	/**
	 * @since pages
	 * @return Integer
	 */
	
	public Integer getPages() {
		return pages;
	}

	/**
	 * @since pages
	 * @param pages
	 * @return void
	 */
	
	public void setPages(Integer pages) {
		this.pages = pages;
	}
}
