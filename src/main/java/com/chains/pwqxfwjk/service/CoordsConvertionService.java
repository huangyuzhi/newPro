package com.chains.pwqxfwjk.service;

import com.chains.pwqxfwjk.model.GPSCoords;
import com.chains.pwqxfwjk.other.TransServiceModel;

import java.util.List;

/**
 * 百度坐标转换接口
 */
public interface CoordsConvertionService {
    TransServiceModel convert(GPSCoords coords);

    /**
     * 百度坐标转换服务一次最多只能转换100个坐标位置,
     * 并且数据的坐标值不能为空
     * @param coordsList 要转换坐标的数据列表
     * @return 百度坐标转换服务的数据模型
     */
    TransServiceModel convert(List< ? extends GPSCoords> coordsList);
}
