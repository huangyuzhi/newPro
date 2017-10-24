package com.chains.pwqxfwjk.service;

import java.util.List;

import com.chains.pwqxfwjk.model.SubstationInfo;
import com.chains.pwqxfwjk.util.SubstationInfoQueryParam;
import com.chains.util.QcRowBounds;

public interface SubstationInfoService {
	/**
	 * 方法名称:getData<br>
	 * 方法描述: 获取变电站信息的方法。                    <br>
	 * @param queryParam	查询相关的参数
	 * @param rowBounds		数据限制
	 * @return
	 * 返回类型:
	 * List<SubstationInfo>
	 * @exception
	*/
	public List<SubstationInfo> getData(SubstationInfoQueryParam queryParam, QcRowBounds rowBounds);
}
