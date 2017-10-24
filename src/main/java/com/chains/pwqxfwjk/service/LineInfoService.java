package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.GPSCoords;
import com.chains.pwqxfwjk.model.LineInfoFor10kV;
import com.chains.pwqxfwjk.model.LineInfoFor35kV;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.util.LineInfoQueryParam;
import com.chains.util.QcRowBounds;

/**
 * 类名称:LineInfoService<br>
 * 功能描述:  线路信息的服务类                    <br>
 * 创建人:钟稳<br>
 * 创建时间:2015年11月5日 下午2:24:14<br>
 * @version 1.0.0
 */
/**
 * 类名称:LineInfoService<br>
 * 功能描述:                      <br>
 * 特殊说明:                      <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月20日 下午11:14:08<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月20日 下午11:14:08<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
/**
 * 类名称:LineInfoService<br>
 * 功能描述:                      <br>
 * 特殊说明:                      <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月20日 下午11:14:24<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月20日 下午11:14:24<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public interface LineInfoService {
	/**
	 * 方法名称: get10kV<br>
	 * 方法描述: 获取10kV线路数据  <br>
	 * @param queryParam
	 * @param rowBounds
	 * @return
	 * 返回类型:
	 * List<LineInfoFor10kV>
	 * @exception
	*/
	public List<LineInfoFor10kV> get10kV(LineInfoQueryParam queryParam, QcRowBounds rowBounds);
	
	/**
	 * 方法名称:addVirtualLine<br>
	 * 方法描述: 添加地线                    <br>
	 * @param list
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void addVirtualLine(List<LineInfoFor10kV> line);
	
	/**
	 * 方法名称:get35kV<br>
	 * 方法描述:  获取35kV线路的信息                   <br>
	 * @return
	 * 返回类型:
	 * List<LineInfoFor35kV>
	 * @exception
	*/
	public List<LineInfoFor35kV> get35kV(LineInfoQueryParam queryParam,  QcRowBounds rowBounds);
	
	public void insertTempTable(List<? extends GPSCoords> list, TransServiceModel model) throws Exception;
	
	/**
	 * 方法名称:updateLineInfo<br>
	 * 方法描述:  修改指定线路                   <br>
	 * @param lines
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void updateLineInfo(List<LineInfoFor10kV> lines);

	/**
	 * 方法名称:removeLineInfo<br>
	 * 方法描述: 根据线路名和支路名删除线路                    <br>
	 * @param lineName
	 * @param branchName
	 * 返回类型:
	 * void
	 * @exception
	*/
	void removeLineInfo(String lineName, String branchName);
}
