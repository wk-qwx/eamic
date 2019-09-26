package com.qwx.util;
import java.io.InputStream;
import java.util.Properties;
public class PropertyUtil {
	public static final Properties PROP = new Properties();

	/**
	 * 读取配置文件的内容（key，value）
	 * 
	 * @param key
	 * @return key对应的value
	 */
	public static String get(String key) {
		if (PROP.isEmpty()) {
			try {
				InputStream in = PropertyUtil.class.getResourceAsStream("/config.properties");
				PROP.load(in);
				in.close();
			} catch (Exception e) {}
		}
		return PROP.getProperty(key);
	}
}
