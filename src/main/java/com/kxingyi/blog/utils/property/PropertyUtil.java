/**
 * PropertyUtil.java
 * @since: 2012-8-3
 * Copyright (c) 2012, aostarit All Rights Reserved.
 */
package com.kxingyi.blog.utils.property;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.text.MessageFormat;
import java.util.*;

/**
 * 读/写属性文件的工具类.
 * 
 * @author: 王勇
 * @date 2012-8-3
 * @version <br>
 *          DESCRIPTION : 可以使用此工具的getMsg(*) / setMsg(**)方法读、写properties文件. <br>
 * <br>
 *          使用步骤: <br>
 *          1.首先,获得PropertyUtil实例. 使用 静态法 newInstance(**) 获得 <br>
 *          如: PropertyUtil propUtil =
 *          PropertyUtil.newInstance("property_name"); // 其中参数"property_name"
 *          为property的文件名.注意 不包含扩展名. <br>
 * <br>
 *          2.使用PropertyUtil实例的(getMsg(String code) 或 getMsg(String code,
 *          Object[] objs))方法取值即可. setMsg(String key, String value) 用来写. <br>
 *          如: propUtil.get("name"); // 获得name对应的值 <br>
 * <br>
 *          <b>特点: 无论有多少properties文件只需这一个类足矣! <br>
 *          注意: properties的文件名字不可重复.</b>
 */
@Slf4j
public class PropertyUtil {

	public static final int DEFAULT_PATH = 0; // 默认路径
	public static final int RELATIVE_PATH = 1; // 相对路径
	public static final int ABSOLUTE_PATH = 2; // 绝对路径

	private static Object clockObj = PropertyUtil.class;

	// 存放参数的property文件的默认位置
	private static final String PROPERTY_FILE_PATH = "/";

	// 存放每个properties文件对应的PropertyUtil
	private static Map<String, PropertyUtil> propertyUtilMap = new HashMap<String, PropertyUtil>();

	// 记录Timer是否已经启动. true :已经启动；false:未启动
	@SuppressWarnings("unused")
	private static boolean timerIsStart = false;

	private String filePath = null;

	private static Properties properties = null;

	// 记录文件修改时间
	private long modifyTime = 0;

	// 临时判断创建是否成功
	private static boolean success = false;

	/**
	 * 实例化PropertyUtil对象.
	 * 
	 * @param propertyName
	 *            属性文件的名字. 注意不包含扩展名.且属性文件默认在 %classpath%下 （即:工程的src下）.
	 * @param path
	 *            文件路径,不包含文件名
	 * @param bln
	 *            DEFAULT_PATH=0:默认路径;
	 *            RELATIVE_PATH=1:path为相对路径,以%classpath%/为头. 如:
	 *            "com/frame/properties" ; ABSOLUTE_PATH=2:path为绝对路径,如:
	 *            "e:/file"
	 */
	private PropertyUtil(final String propertyName, final String path, final int bln) {
		InputStream instream=null;
		try {
			success = false;
			if (DEFAULT_PATH == bln) {
				filePath = this.getClassPath() + propertyName + ".properties";
			} else if (RELATIVE_PATH == bln) {
				filePath = this.getClassPath() + path + "/" + propertyName + ".properties";
			} else if (ABSOLUTE_PATH == bln) {
				filePath = path + "/" + propertyName + ".properties";
			}

			if (null != filePath && !"".equals(filePath)) {
				File file = new File(filePath);
				if (file.exists()) {
					instream = new FileInputStream(filePath);
					this.properties = new Properties();

					properties.load(instream);
				
					success = true;
				} else {  //在默认路径，相对路径，绝对路径都找不到的时候，执行以下代码
					instream = PropertyUtil.class.getClassLoader().getResourceAsStream((propertyName + ".properties")); 
					if (instream != null) {
						this.properties = new Properties();
						properties.load(instream);
						success = true;
					} else {
						log.error("属性文件不存在! filePath = " + filePath);
//						log.error("属性文件没有找到！");// 路径类型在规定范围之外! 规定范围: 0、1、2
					}
				}
			} else {
				log.info("属性文件路径为空!");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally{
			if(instream !=null){
				safeCloseInputStream(instream);
			}
		}
	}

	/**
	 * 获得ClassPath的路径.
	 * 
	 * @return String 路径字符串
	 */
	private String getClassPath() {
		URL url = this.getClass().getResource(PROPERTY_FILE_PATH);
		String path = null;
		try {
			path = URLDecoder.decode(url.getPath(), "UTF-8");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return path;
	}

	/**
	 * 获得指定文件的PropertyUtil.
	 * 
	 * @param propertyName
	 *            属性文件的名字. 注意不包含扩展名. 且属性文件默认在 %classpath%/下（即:工程的src下）.
	 * @return PropertyUtil
	 */
	public static PropertyUtil newInstance(final String propertyName) {
		return initPropertyUtil(propertyName, "", 0);
	}

	public static void setProperties(Properties properties) {
		PropertyUtil.properties = properties;
	}

	/**
	 * 获得指定文件的PropertyUtil. 可以指定文件所在路径
	 * 
	 * @param propertyName
	 *            属性文件的名字. 注意不包含扩展名. 且属性文件默认在 %classpath%/下（即:工程的src下）.
	 * @param path
	 *            文件路径,不包含文件名
	 * @param bln
	 *            DEFAULT_PATH=0:默认路径;
	 *            RELATIVE_PATH=1:path为相对路径,以%classpath%/为头. 如:
	 *            "com/frame/properties" ; ABSOLUTE_PATH=2:path为绝对路径,如:"e:/file"
	 * @return PropertyUtil
	 */
	public static PropertyUtil newInstance(final String propertyName, final String path, final int bln) {
		return initPropertyUtil(propertyName, path, bln);
	}

	/**
	 * 初始化.
	 * 
	 * @param propertyName
	 * @param path
	 * @param bln
	 * @return PropertyUtil
	 */
	private static PropertyUtil initPropertyUtil(final String propertyName, final String path, final int bln) {
		synchronized (clockObj) {
			PropertyUtil propertyUtil = (PropertyUtil) propertyUtilMap.get(propertyName);
			if (null == propertyUtil) {
				propertyUtil = new PropertyUtil(propertyName, path, bln);
				if (success) {
					propertyUtilMap.put(propertyName, propertyUtil);
				}
			}

			 //启动Timer
			 if (!PropertyUtil.timerIsStart) {
			 new Timer().schedule(new CheckFileChange(), 1000,
			 3000);
			 timerIsStart = true;
			 }
			return propertyUtil;
		}
	}

	/**
	 * 获得key对应的value值.
	 * 
	 * @param key
	 *            消息的key
	 * @return String 消息的key所对应的value
	 */
	public static String getMsg(final String key) {
		if (null != properties) {
			return properties.getProperty(key);
		} else {
			return null;
		}
	}

	/**
	 * 获得key对应的value值. 并具有格式化的功能
	 * 
	 * @param key
	 *            消息的key
	 * @param objs
	 *            欲插入value中的参数
	 * @return String 消息的key所对应的value
	 */
	public String getMsg(final String key, final Object[] objs) {
		if (null == properties || null == properties.getProperty(key)) {
			return null;
		} else {
			return MessageFormat.format(properties.getProperty(key), objs);
		}
	}

	/**
	 * 更新（或插入）一对properties信息(主键及其键值) 如果该主键已经存在，更新该主键的值； 如果该主键不存在，则插件一对键值。
	 * 
	 * @param key
	 *            键名
	 * @param value
	 *            键值
	 */
	public void setMsg(final String key, final String value) {
		OutputStream out=null ;
		try {
			out = new FileOutputStream(filePath);
			properties.setProperty(key, value);
			properties.store(out, null);
		} catch (IOException e) {
			log.info("occur error when upate the property! filePath = " + filePath);
		}finally{
			if(out !=null){
				safeCloseOutStream(out);
			}
		}
	}

	/**
	 *安全关闭输出流处理
	 */
	public  void safeCloseOutStream(OutputStream out){
		if(out !=null){
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				log.info("OutputStream Close failed");
			}
		}
	}
	/**
	 *安全关闭输出流处理
	 */
	public  void safeCloseInputStream(InputStream input){
		if(input !=null){
			try {
				input.close();
			} catch (IOException e) {
				log.info("InputStream Close failed");
			}
		}
	}
	/**
	 * 获得属性文件的路径.
	 * 
	 * @return String 属性文件路径
	 */
	protected String getFilePath() {
		return filePath;
	}

	/**
	 * @return the propertyUtilMap
	 */
	protected static Map<String, PropertyUtil> getPropertyUtilMap() {
		return propertyUtilMap;
	}

	/**
	 * @return the modifyTime
	 */
	protected long getModifyTime() {
		return modifyTime;
	}

	/**
	 * 
	 * @return Properties
	 * @param s
	 */
	public Properties getProperties(String s) {
		return properties;
	}
}

/**
 * 检查文件是否修改过，如果修改过就重新加载.
 * 
 * @author Bruce
 * 
 */
@Slf4j
class CheckFileChange extends TimerTask {
	public CheckFileChange() {

	}

	@SuppressWarnings("static-access")
	@Override
	public void run() {
		InputStream instream=null;
		try {
			@SuppressWarnings("rawtypes")
			Map properties = PropertyUtil.getPropertyUtilMap();
			if (null != properties) {
				for (Object name : properties.keySet()) {
					PropertyUtil util = (PropertyUtil) properties.get(name);
					File file = new File(util.getFilePath());
					// 变更时间
					long updateTime = file.lastModified();
					if (util.getModifyTime() != updateTime) {
						instream = new FileInputStream(file);
						util.getProperties("fdfs.pool.test-on-borrow").load(instream);
					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}finally{
			if(instream !=null){
				try {
					instream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					log.info("close InputStream failed");
					log.error(e.getMessage());
				}
			}
		}
	}
}
