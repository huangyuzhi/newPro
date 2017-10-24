package com.chains.pwqxfwjk.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ConvertBean {
	/**
	 * 将指定的bean对象转换为一个map
	 * 
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map convertBean(Object bean) {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(type);
	
			PropertyDescriptor[] propertyDescriptors = beanInfo
					.getPropertyDescriptors();
			for (int i = 0; i < propertyDescriptors.length; i++) {
				PropertyDescriptor descriptor = propertyDescriptors[i];
				String propertyName = descriptor.getName();
				if (!propertyName.equals("class")) {
					Method readMethod = descriptor.getReadMethod();
					Object result = readMethod.invoke(bean, new Object[0]);
					if (result != null) {
						returnMap.put(propertyName, result);
					} else {
						returnMap.put(propertyName, "");
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return returnMap;
	}

	/**
	 * 方法名称:convertFrom<br>
	 * 方法描述: 将map转换为对象                    <br>
	 * @param map
	 * @param clz
	 * @return
	 * 返回类型:
	 * T
	 * @exception
	*/
	public static <T>T convertFrom(Map<String, ? extends Object> map, Class<T> clz) {
		T resultBean;
		try {
			resultBean = clz.newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(clz);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for(Map.Entry<String, ? extends Object> entry : map.entrySet()) {
				String key = entry.getKey();
				for(int i = 0; i < propertyDescriptors.length; i++) {
					PropertyDescriptor descriptor = propertyDescriptors[i];
					if(descriptor.getName().equals(key)) {
						Method method = descriptor.getWriteMethod();
						method.invoke(resultBean, entry.getValue());
					}
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return resultBean;
	}
}
