package com.chains.pwqxfwjk.util;

/**
 * 类名称:QueryParam<br>
 * 功能描述:  查询参数的基类，包含了常用的查询字段                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月11日 下午4:43:33<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月11日 下午4:43:33<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class QueryParam {
	private boolean sort;

	private String sortedBy;
	
	private String order;
	
	private String selectField;
	
	private String groupBy;

	/**
	 * @since sort
	 * @return boolean
	 */
	
	public boolean isSort() {
		return sort;
	}

	/**
	 * @since sort
	 * @param sort
	 * @return void
	 */
	
	public void setSort(boolean sort) {
		this.sort = sort;
	}

	/**
	 * @since sortedBy
	 * @return String
	 */
	
	public String getSortedBy() {
		return sortedBy;
	}

	/**
	 * @since sortedBy
	 * @param sortedBy
	 * @return void
	 */
	
	public void setSortedBy(String sortedBy) {
		this.sortedBy = sortedBy;
	}

	/**
	 * @since selectField
	 * @return String
	 */
	
	public String getSelectField() {
		return selectField;
	}

	/**
	 * @since selectField
	 * @param selectField
	 * @return void
	 */
	
	public void setSelectField(String selectField) {
		if(selectField != null && !"".equals(selectField)) {
			this.selectField = selectField;
		}
	}

	/**
	 * @since groupBy
	 * @return String
	 */
	
	public String getGroupBy() {
		return groupBy;
	}

	/**
	 * @since groupBy
	 * @param groupBy
	 * @return void
	 */
	
	public void setGroupBy(String groupBy) {
		this.groupBy = groupBy;
	}

	/**
	 * @since order
	 * @return String
	 */
	
	public String getOrder() {
		return order;
	}

	/**
	 * @since order
	 * @param order
	 * @return void
	 */
	
	public void setOrder(String order) {
		this.order = order;
	}
}
