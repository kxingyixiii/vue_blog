package com.kxingyi.blog.utils;

import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class DataUtil {
	
	private final static int DEFAULT_LEN = 6;

	/**
	 * unicode 转字符串
	 */
	public static String unicode2String(String unicode) {
	    StringBuffer string = new StringBuffer();
	    String[] hex = unicode.split("\\\\u");
	    for (int i = 1; i < hex.length; i++) {
	        // 转换出每一个代码点
	        int data = Integer.parseInt(hex[i], 16);
	        // 追加成string
	        string.append((char) data);
	    }
	    return string.toString();
	}
	
	/**
	 * 字符串 转unicode
	 */
	public static String string2Unicode(String string) {
	    StringBuffer unicode = new StringBuffer();
	    for (int i = 0; i < string.length(); i++) {
	        // 取出每一个字符
	        char c = string.charAt(i);
	        // 转换为unicode
	        unicode.append("\\u" + Integer.toHexString(c));
	    }
	    return unicode.toString();
	}

	public static String decodeUnicode(String s) {
		Pattern p = compile("\\\\u([0-9a-zA-Z]{4})");
		Matcher m = p.matcher(s);
		StringBuffer sb;
		sb = new StringBuffer(s.length());
		while (m.find()) {
			m.appendReplacement(sb,
			Character.toString((char) Integer.parseInt(m.group(1), 16)));
		}
		m.appendTail(sb);
		return sb.toString();
	}
	
	/**
	  * @Title: encodeBase64
	  * @Description: 将字符串s编码
	  * @author lchen
	  * @since 2016-12-13 下午6:36:39
	  * @param s
	  * @return
	 */
	public static String encodeBase64(String s) {
		if (null == s) {
			return null;
		}
		return (new BASE64Encoder()).encode(s.getBytes());
	}
	
	/**
	  * @Title: decodeBase64
	  * @Description: 将字符串s解码
	  * @author lchen
	  * @since 2016-12-13 下午6:37:29
	  * @param s
	  * @return
	 */
	public static String decodeBase64(String s) {
		if (null == s) {
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			return new String(decoder.decodeBuffer(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String generateRandom() {
		return generateRandom(DEFAULT_LEN);
	}
	
	public static String generateRandom(int len) {
		String vcode = "";
		if (len > 0) {
	        for (int i = 0; i < len; i++) {
	            vcode = vcode + (int)(Math.random() * 9);
	        }
		}
		return vcode;
	}
	
	public static int parseInt(Object str) {
		try {
			return Integer.parseInt(String.valueOf(str));
		} catch (Exception ex) {
			return 0;
		}
	}
	
	public static double parseDouble(Object str) {
		try {
			return Double.parseDouble(String.valueOf(str));
		} catch (Exception ex) {
			return 0;
		}
	}
	
	public static boolean parseBoolean(Object str) {
		try {
			return Boolean.parseBoolean(String.valueOf(str));
		} catch (Exception ex) {
			return false;
		}
	}
	
	public static long parseLong(Object str) {
		try {
			return Long.parseLong(String.valueOf(str));
		} catch (Exception ex) {
			return 0;
		}
	}
	
	public static boolean checkPhone(String phone) {
		try {
			if (StringUtils.isEmpty(phone)) {
				return false;
			}
			if (phone.matches("^1[3-9]\\d{9}$")) {
				return true;
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}
}
