package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.TroubleLine;

public interface TroubleLineService {
	/**
	 * 方法名称:addTroubleLine<br>
	 * 方法描述: 添加故障线路                    <br>
	 * @param troubleLine
	 * 返回类型:
	 * void
	 * @exception
	*/
	public Integer addTroubleLine(TroubleLine troubleLine);
	
	/**
	 * 方法名称:getTroubleLines<br>
	 * 方法描述:  获得所有故障线路                   <br>
	 * @return
	 * 返回类型:
	 * List<TroubleLine>
	 * @exception
	*/
	public List<TroubleLine> getTroubleLines();
	
	/**
	 * 方法名称:delTroubleLine<br>
	 * 方法描述:  根据id删除故障线路                   <br>
	 * 特殊说明:                     <br>
	 * @param ids
	 * 返回类型:
	 * void
	 * @exception
	*/
	public void delTroubleLine(List<Integer> uniqueKeys);
	
	public void delTroubleLine(String lineQuality);
}
