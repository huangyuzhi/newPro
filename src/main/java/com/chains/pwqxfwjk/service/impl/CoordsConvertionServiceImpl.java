package com.chains.pwqxfwjk.service.impl;

import com.alibaba.fastjson.JSON;
import com.chains.pwqxfwjk.model.GPSCoords;
import com.chains.pwqxfwjk.other.TransServiceModel;
import com.chains.pwqxfwjk.service.CoordsConvertionService;
import com.chains.pwqxfwjk.util.remote.Https;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("coordsConvertionServiceImpl")
public class CoordsConvertionServiceImpl implements CoordsConvertionService {

    @Override
    public TransServiceModel convert(GPSCoords coords) {
        Map<String, String> params = queryParams();
        params.put("coords", coords.getLongitude() + "," + coords.getLatitude());
        String responseData = Https.post("http://api.map.baidu.com/geoconv/v1/", params);
        return JSON.parseObject(responseData, TransServiceModel.class);
    }

    @Override
    public TransServiceModel convert(List<? extends GPSCoords> coordsList) {
        Assert.isTrue(coordsList.size() <= 100, "一次最多只能转化100个坐标");
        Map<String, String> params = queryParams();
        String coordsParams = "";
        for (GPSCoords gpsCoords:
                coordsList
             ) {
                Assert.isTrue(gpsCoords.getLatitude() != null || gpsCoords.getLongitude() != null,
                        "坐标经纬度的值不能为空");
                coordsParams += gpsCoords.getLongitude() + "," + gpsCoords.getLatitude() + ";";
        }
        coordsParams = coordsParams.substring(0, coordsParams.length() - 1);
        params.put("coords", coordsParams);
        String responseData = Https.post("http://api.map.baidu.com/geoconv/v1/", params);
        return JSON.parseObject(responseData, TransServiceModel.class);
    }

    private Map<String, String> queryParams() {
        Map<String, String> params = new HashMap<>();
        params.put("from", "1");
        params.put("to", "5");
        params.put("ak", "dnTeK9nGVeFhbvvWZ2eTKHjI");
        return params;
    }
}
