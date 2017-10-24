package com.chains.pwqxfwjk.other;

import java.util.List;

import com.chains.pwqxfwjk.model.GPSCoords;

/**
 * 类名称:BdConvesionStrategy<br>
 * 功能描述:  函数对象，策略设计模式                    <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年11月12日 下午4:02:39<br>
 * 修改人:zw<br>
 * 修改时间:2015年11月12日 下午4:02:39<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public interface BdConvesionStrategy<T> {
	
	public List<T>  bdConvesion(List<? extends GPSCoords> list, TransServiceModel model);
}
