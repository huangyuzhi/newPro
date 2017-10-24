package support;

import com.alibaba.fastjson.JSON;

public class JSONHelper {
	
	public static String parseObject(Object obj) {
		return JSON.toJSONStringWithDateFormat(obj, "YYYY-MM-dd HH:mm:ss");
	}
	
}
