package com.chains.pwqxfwjk.util;

import java.io.UnsupportedEncodingException;

/**
 * 类名称:WebUtil<br>
 * 功能描述:    web端的工具类                  <br>
 * <br>
 * 创建人:zw<br>
 * 创建时间:2015年12月1日 下午2:52:55<br>
 * 修改人:zw<br>
 * 修改时间:2015年12月1日 下午2:52:55<br>
 * 修改备注:               
 * 
 * @version 1.0.0
 */
public class WebUtil {
	/**
	 * 方法名称:transToUTF<br>
	 * 方法描述: 转换乱码。                   <br>
	 * @param str
	 * @param encodeCharSet
	 * @return
	 * 返回类型:
	 * String
	 * @exception
	*/
	public static String transToUTF(String str, String encodeCharSet, String toCharSet) {
		String result;
		try {
			result= new String(str.getBytes(encodeCharSet),toCharSet);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}
