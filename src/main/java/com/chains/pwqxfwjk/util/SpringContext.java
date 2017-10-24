package com.chains.pwqxfwjk.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringContext {
	private static final String DEFAULT_CONFIG_FILE =  "TestApplicationContext.xml";
	
	private static String configFile;
	
	private SpringContext() {}
	
	public static final ApplicationContext context() {
		return context(null);
	}
	
	public static final ApplicationContext context(String configFileLocation) {
		configFile = configFileLocation == null ? DEFAULT_CONFIG_FILE : configFileLocation;
		return LazyHolder.singleton;
	}
	
	private static class LazyHolder {
		static final  ApplicationContext singleton = new ClassPathXmlApplicationContext(configFile);
	}
}
