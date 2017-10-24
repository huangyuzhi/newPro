package com.chains.pwqxfwjk.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名称:PersonInfoCollection<br>
 * 功能描述: 包含多个人员信息，以及人员个数                     <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月26日 上午10:26:54<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月26日 上午10:26:54<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class PersonInfoCollection {
	private Integer currentPage;
	private Integer pageSize;
	private Integer pages;
	private List<PersonInfo> result = new ArrayList<PersonInfo>();
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
	/**
	 * @since result
	 * @return List<PersonInfo>
	 */
	
	public List<PersonInfo> getResult() {
		return result;
	}
	/**
	 * @since result
	 * @param result
	 * @return void
	 */
	
	public void setResult(List<PersonInfo> result) {
		this.result = result;
	}
}
