package com.chains.pwqxfwjk.service;

import java.io.Serializable;
import java.util.List;

import com.chains.pwqxfwjk.model.TransformerInfo;
import com.chains.pwqxfwjk.util.TransformerInfoQueryParam;
import com.chains.util.QcRowBounds;

public interface TransformerInfoService {
	/**
	 * 方法名称:getData<br>
	 * 方法描述:                     <br>
	 * @return
	 * 返回类型:
	 * List<TransformerInfo>
	*/
	public List<TransformerInfo> getData(TransformerInfoQueryParam queryParam, QcRowBounds rowBounds);
	
	/**
	 * 方法名称:getTransformerByUser<br>
	 * 方法描述: 根据用户号获取变压器信息                    <br>
	 * @return
	 * 返回类型:
	 * TransformerInfo
	*/
	public TransformerInfo getTransformerByUser(String customerNumber);
	
	/**
	 * 方法名称:getTransformerByLine<br>
	 * 方法描述:  获得指定线路关联的变压器                   <br>
	 * @param lineName
	 * @return
	 * 返回类型:
	 * List<TransformerInfo>
	 * @exception
	*/
	List<TransformerInfo> getTransformerByLine(String lineName);

	Long count(TransformerInfoQueryParam queryParam);

	/**
	 * 方法名称:getTransformerByCustomerName<br>
	 * 方法描述:     获得指定客户名关联的变压器                       <br>
	 * @param customerName
	 * @return
	 * 返回类型:
	 * List<TransformerInfo>
	*/
	public List<TransformerInfo> getTransformerByCustomerName(String customerName);
	
	public List<TransformerInfo> findByExample(TransformerInfo transformerInfo);

	public void changeFault(Integer id, boolean isFault);
	
	public void addList(List<TransformerInfo> customers);

	public void del(Serializable id);

	/**
	 * 转化变压器的坐标
	 */
	void updateBdCoords(TransformerInfo transformerInfo);
}
