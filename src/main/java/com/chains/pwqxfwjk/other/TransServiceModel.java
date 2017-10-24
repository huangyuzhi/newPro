package com.chains.pwqxfwjk.other;

import java.util.List;

/**
 * 类名称:TransServiceModel<br>
 * 功能描述:  百度坐标转换服务返回数据的数据模型                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月11日 下午5:16:14<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月11日 下午5:16:14<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class TransServiceModel {
	private Integer status;
	private  List<BdCoord> result;
	/**
	 * @since status
	 * @return Integer
	 */
	
	public Integer getStatus() {
		return status;
	}
	/**
	 * @since status
	 * @param status
	 * @return void
	 */
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * @since result
	 * @return List<BdCoord>
	 */
	
	public List<BdCoord> getResult() {
		return result;
	}
	/**
	 * @since result
	 * @param result
	 * @return void
	 */
	
	public void setResult(List<BdCoord> result) {
		this.result = result;
	}
}
