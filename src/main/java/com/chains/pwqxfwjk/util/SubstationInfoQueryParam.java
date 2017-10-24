package com.chains.pwqxfwjk.util;

/**
 * 类名称:SubstationInfoQueryParam<br>
 * 功能描述:   变电站信息的查询参数                   <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月11日 下午4:42:25<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月11日 下午4:42:25<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class SubstationInfoQueryParam extends QueryParam {
	public Integer id;

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
