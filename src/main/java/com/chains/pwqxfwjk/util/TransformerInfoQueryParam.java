package com.chains.pwqxfwjk.util;

import java.util.HashMap;
import java.util.Map;


public class TransformerInfoQueryParam extends QueryParam{
	private Integer id;

	private String customerNumber;

	private String transformerName;

	private String lineName;
	
	private Map<String, Object> params = new HashMap<>();
	
	
	private Double minBdCoordsx;
	
	private Double minBdCoordsy;
	
	private Double maxBdCoordsx;
	
	private Double maxBdCoordsy;
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
	
	/**
	 * @since customerNumber
	 * @return String
	 */
	
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * @since customerNumber
	 * @param customerNumber
	 * @return void
	 */
	
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * @since lineName
	 * @return String
	 */
	
	public String getLineName() {
		return lineName;
	}

	/**
	 * @since lineName
	 * @param lineName
	 * @return void
	 */
	
	public void setLineName(String lineName) {
		this.lineName = lineName;
	}

	/**
	 * 方法名称:genenrateHql<br>
	 * 方法描述: 根据设置的查询条件生成查询参数                   <br>
	 * @return
	 * 返回类型:
	 * String
	 * @exception
	*/
	public String genenrateHql() {
		String selectField = getSelectField() != null ? getSelectField() : "t ";
		String hql = "select " +  selectField + "from TransformerInfo t where 1 = 1 ";
		hql = whereClause(hql);
		
		if(isSort() && getSortedBy() != null && !"".equals(getSortedBy().trim())) {
			hql += "order by " + getSortedBy();
		}
		return hql;
	}


	public String genenrateCount() {
		String selectClause = "select count(t) from TransformerInfo t where 1 = 1 ";
		return whereClause(selectClause);
	}

	private String whereClause(String hql) {
		if(getId() != null ) {
			hql += " and id = :id ";
			params.put("id", getId());
		}

		if(getLineName() != null && getLineName().trim().length() > 0) {
			hql += "and belongLine = :lineName ";
			params.put("lineName", getLineName());
		}

		if(getTransformerName() != null && getTransformerName().trim().length() > 0) {
			hql += " and transformerName like :transformerName ";
			params.put("transformerName", "%" + getTransformerName() + "%");
		}

		if(getMinBdCoordsx() != null && getMinBdCoordsy() != null) {
			hql += "and bdCoorsX >= :minBdCoordsx and bdCoorsY >= :minBdCoordsy ";
			params.put("minBdCoordsx", getMinBdCoordsx());
			params.put("minBdCoordsy", getMinBdCoordsy());
		}

		if(getMaxBdCoordsx() != null && getMaxBdCoordsy() != null) {
			hql += "and bdCoorsX <= :maxBdCoordsx and bdCoorsY <= :maxBdCoordsy ";
			params.put("maxBdCoordsx", getMaxBdCoordsx());
			params.put("maxBdCoordsy", getMaxBdCoordsy());
		}
		return hql;
	}
	/**
	 * @since params
	 * @return Map<String,Object>
	 */
	public Map<String, Object> getParams() {
		return params;
	}

	/**
	 * @since minBdCoordsx
	 * @return Double
	 */
	
	public Double getMinBdCoordsx() {
		return minBdCoordsx;
	}

	/**
	 * @since minBdCoordsx
	 * @param minBdCoordsx
	 * @return void
	 */
	
	public void setMinBdCoordsx(Double minBdCoordsx) {
		this.minBdCoordsx = minBdCoordsx;
	}

	/**
	 * @since minBdCoordsy
	 * @return Double
	 */
	
	public Double getMinBdCoordsy() {
		return minBdCoordsy;
	}

	/**
	 * @since minBdCoordsy
	 * @param minBdCoordsy
	 * @return void
	 */
	
	public void setMinBdCoordsy(Double minBdCoordsy) {
		this.minBdCoordsy = minBdCoordsy;
	}

	/**
	 * @since maxBdCoordsx
	 * @return Double
	 */
	
	public Double getMaxBdCoordsx() {
		return maxBdCoordsx;
	}

	/**
	 * @since maxBdCoordsx
	 * @param maxBdCoordsx
	 * @return void
	 */
	
	public void setMaxBdCoordsx(Double maxBdCoordsx) {
		this.maxBdCoordsx = maxBdCoordsx;
	}

	/**
	 * @since maxBdCoordsy
	 * @return Double
	 */
	
	public Double getMaxBdCoordsy() {
		return maxBdCoordsy;
	}

	/**
	 * @since maxBdCoordsy
	 * @param maxBdCoordsy
	 * @return void
	 */
	
	public void setMaxBdCoordsy(Double maxBdCoordsy) {
		this.maxBdCoordsy = maxBdCoordsy;
	}

	public String getTransformerName() {
		return transformerName;
	}

	public void setTransformerName(String transformerName) {
		this.transformerName = transformerName;
	}
}
