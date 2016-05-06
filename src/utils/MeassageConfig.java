package org.jymf.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class MeassageConfig {
	private static Properties pro = null;
	private static String MESSAGE_PROPERTIES = "message.properties";
	private final static String CONDING = "UTF-8";
	
	static {
		pro = new Properties();
		InputStream inStream = MeassageConfig.class.getClassLoader()
		.getResourceAsStream(MESSAGE_PROPERTIES);
		try {
			pro.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过key获取信息
	 * @param key
	 * @return
	 */
	public static String getMessage(String key) {
		String value = (String) pro.get(key);
		try {
			value = value == null ? "" : new String(value
					.getBytes(CONDING), CONDING);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		return value;
	}
	
}
