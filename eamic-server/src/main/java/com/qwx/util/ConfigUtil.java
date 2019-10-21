package com.qwx.util;

import org.apache.log4j.Logger;

/**
 * 获取配置文件信息
 * @author kal
 */
public class ConfigUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ConfigUtil.class);
	
	//指定配置文件地址//resource目录下:
    private static PropertiesLoader propertiesLoader = new PropertiesLoader(
            "classpath:/config.properties");
 
 
    public static String getProperty(String key) {
        String property;
        try {
            property = propertiesLoader.getProperty(key);
        } catch (Exception e) {
        	logger.debug("Property key not exit: " + key);
            return null;
        }
        return property;
    }
 
    public static String getProperty(String key, String value) {
        if (propertiesLoader.getProperty(key) == null) {
            return value;
        }
        return propertiesLoader.getProperty(key);
    }

}
