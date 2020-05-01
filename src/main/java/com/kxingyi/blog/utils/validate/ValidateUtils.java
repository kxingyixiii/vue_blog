package com.kxingyi.blog.utils.validate;


import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * @author User
 *数据验证工具类
 */
public class ValidateUtils {
	//字符串
	public static String CONTAINSPECIAL="[\\ \\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\+\\=\\[\\]\\{\\}\\|\\;\\:\\'\"\\,\\.\\/\\<\\>\\?\\\\]+";
	public static String CONTAINSPECIAL_IDS="[\\ \\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\-\\+\\=\\[\\]\\{\\}\\|\\;\\:\\'\"\\.\\/\\<\\>\\?\\\\]+";
	public static String CONTAINSPECIAL_IP="([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
	//数字
	public static String CONTAINSPECIAL_NUM="[0-9]+";	

	/**
	 * 整数  包括0
	 */
	private static final String NUMBER = "^-?[0-9]\\d*$";
	/** 整数 */
	private static final String V_INTEGER = "^-?[1-9]\\d*$";
	

	/** 正整数 */
	private static final String V_Z_INDEX = "^[1-9]\\d*$";

	/** 负整数 */
	private static final String V_NEGATIVE_INTEGER = "^-[1-9]\\d*$";

	/** 数字 */
	private static final String V_NUMBER = "^([+-]?)\\d*\\.?\\d+$";

	/** 正数 */
	private static final String V_POSITIVE_NUMBER = "^[1-9]\\d*$";
	
	/** 非负数 */
	private static final String V_NOT_NEGATIVE = "^[1-9]\\d*|0$";

	/** 负数 */
	private static final String V_NEGATINE_NUMBER = "^-[1-9]\\d*|0$";

	/** 浮点数 */
	private static final String V_FLOAT = "^([+-]?)\\d*\\.\\d+$";

	/** 正浮点数 */
	private static final String V_POSTTIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*$";

	/** 负浮点数 */
	private static final String V_NEGATIVE_FLOAT = "^-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*)$";

	/** 非负浮点数（正浮点数 + 0） */
	private static final String V_UNPOSITIVE_FLOAT = "^[1-9]\\d*.\\d*|0.\\d*[1-9]\\d*|0?.0+|0$";

	/** 非正浮点数（负浮点数 + 0） */
	private static final String V_UN_NEGATIVE_FLOAT = "^(-([1-9]\\d*.\\d*|0.\\d*[1-9]\\d*))|0?.0+|0$";
	
	/** mac地址 */
	private static final String V_MAC = "([A-Fa-f0-9]{2}-){5}[A-Fa-f0-9]{2}";

	/** 邮件 */
	private static final String V_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";

	/** 颜色 */
	private static final String V_COLOR = "^[a-fA-F0-9]{6}$";

	/** url */
//	private static final String V_URL = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$";
//	private static final String V_URL = "(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]";
	private static final String V_URL = "^((https|http|ftp|rtsp|mms)+://)(([0-9a-zA-Z_!~*'().&=+$%-]+: )?[0-9a-zA-Z_!~*'().&=+$%-]+@)?(([0-9]{1,3}.){3}[0-9]{1,3}|([0-9a-zA-Z_!~*'()-]+.)*([0-9a-zA-Z][0-9a-zA-Z-]{0,61})?[0-9a-zA-Z].[a-zA-Z]{2,6})(:[0-9]{1,6})?((/?)|(/[0-9a-zA-Z_!~*'().;?:@&=+$,%#-]+)+/?)$";

	/** 仅中文 */
	private static final String V_CHINESE = "^[\\u4E00-\\u9FA5\\uF900-\\uFA2D]+$";

	/** 仅ACSII字符 */
	private static final String V_ASCII = "^[\\x00-\\xFF]+$";

	/** 邮编 */
	private static final String V_ZIPCODE = "^\\d{6}$";

	/** 手机 */
	private static final String V_MOBILE = "^(1[34578]{1}\\d{9})$";

	/** ip地址 */
	// private static final String V_IP4 =
	// "^(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)\\.(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)$";

	/** 非空 */
	private static final String V_NOTEMPTY = "^\\S+$";

	/** 图片 */
	private static final String V_PICTURE = "(.*)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

	/** 压缩文件 */
	private static final String V_RAR = "(.*)\\.(rar|zip|7zip|tgz)$";

	/** 日期 */
	private static final String V_DATE = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$";

	/** 日期 */
	private static final String V_DATE_TIME = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-)) (20|21|22|23|[0-1]?\\d):[0-5]?\\d:[0-5]?\\d$";
	
	/** QQ号码 */
	private static final String V_QQ_NUMBER = "^[1-9]*[1-9][0-9]*$";

	/** 电话号码的函数(包括验证国内区号,国际区号,分机号) */
	private static final String V_TEL = "^(([0\\+]\\d{2,3}-)?(0\\d{2,3})-)?(\\d{7,8})(-(\\d{3,}))?$";
	
	/** 传真 */
	private static final String V_FAX = "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$";
//	private static final String V_FAX = "^(\\d{3,4}-)?\\d{7,8}$";
	
			
	/** 用来用户注册。匹配由数字、26个英文字母或者下划线组成的字符串 */
	private static final String V_USERNAME = "^\\w+$";

	/** 字母 */
	private static final String V_LETTER = "^[A-Za-z]+$";

	/** 大写字母 */
	private static final String V_LETTER_U = "^[A-Z]+$";

	/** 小写字母 */
	private static final String V_LETTER_I = "^[a-z]+$";

	/** 身份证 */
	private static final String V_IDCARD = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$";

	/** 验证密码(数字和英文同时存在) */
	private static final String V_AUTH_KEY_REG = "[A-Za-z0-9]+";

	/** 验证密码长度(6-18位) */
	private static final String V_AUTH_KEY_LENGTH = "^\\d{6,18}$";
	

	/** 验证两位数 */
	private static final String V_TWO＿POINT = "^[0-9]+(.[0-9]{2})?$";

	/** 验证一个月的31天 */
	private static final String V_31DAYS = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

	/** 包含特殊字符 **/
	private static final String VC_SPECIAL_CHAR = "\\W";

	/** 包含数字 **/
	private static final String VC_NUMBER = "[0-9]+";

	/** 包含小写字母 **/
	private static final String VC_LOWER_LETTER = "[a-z]+";

	/** 包含大写字母 **/
	private static final String VC_UPPER_LETTER = "[A-Z]+";
	
	/** 包含字母 */
	private static final String VC_LETTER = "[A-Za-z]+";
	/** 数据库字段名称 */
	private static final String DATA_FIELD ="[A-Za-z][a-zA-Z_]*[a-zA-Z]*$";
	
	private static final String V_LOGIN_NAME = "^([\\u4E00-\\uFA29]|[\\uE7C7-\\uE7F3]|[a-zA-Z0-9])*$";
	
	/** 验证名称   js:chsEngNum*/
	private static final String COMMON_NAME =  "^([\\u4E00-\\uFA29]|[\\uE7C7-\\uE7F3]|[a-zA-Z0-9])*$";
	
	/** 验证编码   js:noHtmlTag*/
	private static final String COMMON_CODE =  ".*[*?|:<>].*$";
	
	/** 验证编码   js:notExistChinese*/
	private static final String COMMON_CODE_NO_CHINESE =  ".*[\\u4E00-\\u9FA5].*$";
	
	/** 验证编码   js:english*/
	private static final String ENGLISH_COMMON_CODE = "^[A-Za-z]+$";
	
	/** 验证登录名   js:engNum*/
	private static final String RIGHT_LOGIN_NAME = "^[0-9a-zA-Z_]*$";
	
	/** 验证登录名已英文字母开头   js:englishStart*/
	private static final String START_LOGIN_NAME = "^[a-zA-Z][a-zA-Z0-9_]*";
	/** 国际手机号 */
	private static final String INTERNATION_TEL="^[0-9+]*$";
	
	/** 验证ID 加逗号*/
	private static final String ID_COMMA ="^([0-9a-zA-Z]|,)*$";
	/** 非法字符*/
	private static final String ILLEGAL_STR= ".*[~·`@#\\$%\\^&\\*\\ ?？!!￥（）()、\\\\/:：；'].*$";
	
	private static volatile List<String> patternList = new ArrayList<String>();
   //输入验证
	public static boolean inputCheck(String input, String regex) {
		Pattern p = Pattern.compile(regex);
		return p.matcher(input).find();
	}
	
	//输入验证
	public static boolean inputCheck(String input) {
		Pattern p = Pattern.compile(ValidateUtils.CONTAINSPECIAL);
		return p.matcher(input).find();
	}
	
	// 输入长度验证
	public static boolean inputCheckLeng(String input, int length) {
		if(StringUtils.isNotEmpty(input)&&input.length()>length)
		{
		  return true;	
		}
		return false;
	}
	
	/**
	 * 校验规则： 1、将前面的身份证号码17位数分别乘以不同的系数。第i位对应的数为[2^(18-i)]mod11。从第一位到第十七位的系数分别为：7
	 * 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 ； 2、将这17位数字和系数相乘的结果相加；
	 * 3、用加出来和除以11，看余数是多少？； 4、余数只可能有0 1 2 3 4 5 6 7 8 9
	 * 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2；
	 * 
	 * @return 返回false说明，身份证号码不符合规则，返回true说明身份证号码符合规则
	 * @throws Exception
	 */
	public static boolean checkCardId(String cid) throws Exception {
		boolean flag = false;
		if (cid == null || cid.trim().length() == 0) {
			return false;
		}
		int len = cid.length();
		if (len != 15 && len != 18) {
			return false;
		}
		int kx = 0;
		for (int i = 0; i < len - 1; i++) {
			int x = 0;
			String s = String.valueOf(cid.charAt(i));
			if (s.matches("\\d+")) {
				x = Integer.parseInt(s);
				int k = 1;
				for (int j = 1; j < 18 - i; j++) {
					k *= 2;
				}
				kx += k * x;
			} else {
				return false;
			}
		}
		int mod = kx % 11;
		int[] mods = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		Character[] checkMods = { '1', '0', 'X', '9', '8', '7', '6', '5', '4',
				'3', '2' };
		for (int i = 0; i < 11; i++) {
			if (mod == mods[i]) {
				Character lastCode = cid.charAt(len - 1);
				if (checkMods[i].equals(Character.toUpperCase(lastCode))) {
					flag = true;
				}
			}
		}
		return flag;
	}
	//手机号验证
	private boolean isChinaPhoneLegal(String str){
		try {
			String regExp = "^(13[0-9]|15[012356789]|17[3678]|18[0-9]|14[57])[0-9]{8}$"; 
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(str);
			return m.matches();
		} catch (Exception e) {
			
		}
		return false;
	}
	//邮箱验证
	public static boolean isChekEmail(String str) {
		 boolean flag = false;
	        try{
	                String check = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?$";
	                Pattern regex = Pattern.compile(check);
	                Matcher matcher = regex.matcher(str.toLowerCase());
	                flag = matcher.matches();
	            }catch(Exception e){
	                flag = false;
	            }
		return flag;
		
	}
	/**
	 * 验证邮件地址
	 * @param value
	 * @return
	 */
	public static boolean checkMail(String value){
		return match(V_EMAIL, value);
	}
	
	//登录名
	public static boolean isChekLoginCode(String str) {
		 boolean flag = false;
	        try{
	        	Pattern p = Pattern.compile("[^a-zA-Z0-9_]+");
	        	flag=p.matcher(str).find();
	            }catch(Exception e){
	                flag = false;
	            }
		return flag;
		
	}
	/**
	 * 验证传真
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Fax(String value) {
	    return !match(V_FAX, value);
	}
	
	/**
	 * 验证是不是日期，到日
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Date(String value) {
		return !match(V_DATE, value);
	}
	
	/**
	 * 验证是不是日期，到时间
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean DateTime(String value) {
		return !match(V_DATE_TIME, value);
	}

	/**
	 * 验证登录名英文和数字
	 * @param value
	 * @return
	 */
	public static boolean checkEngNum(String value) {
		return match(RIGHT_LOGIN_NAME, value);
	}
	
	/**
	 * 验证登录名 英文开头
	 * @param value
	 * @return
	 */
	public static boolean checkEnglishStart(String value) {
		return match(START_LOGIN_NAME, value);
	}
	/**
	 * xss和sql关键字过滤
	 * 判断是否为合法参数
	 * 
	 * @param pInput
	 * @return
	 */
	public static boolean isContainXssPatamters(String paramter) {
		if (paramter == null || paramter == "") {
			return false;
		}
		paramter = paramter.toLowerCase();
		boolean flag = false;
		if(patternList.isEmpty()){
			StringBuilder patternStr = new StringBuilder();
			patternStr.append(".*script.*").append(",");
			patternStr.append(".*<script>.*").append(",");
			patternStr.append("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"").append(",");
			patternStr.append("</script>").append(",");
			patternStr.append("<script(.*?)>").append(",");
			patternStr.append("eval\\((.*?)\\)").append(",");
			patternStr.append("e­xpression\\((.*?)\\)").append(",");
			patternStr.append("javascript:").append(",");
			patternStr.append("vbscript:").append(",");
			patternStr.append("onload(.*?)=").append(",");
			patternStr.append("alert\\((.*?)\\)").append(",");
			patternStr.append(".*select .*").append(",");
			patternStr.append(".*insert .*").append(",");
			patternStr.append(".*update .*").append(",");
			patternStr.append(".*delete .*").append(",");
			patternStr.append(".*or .*").append(",");
			patternStr.append(".*and .*").append(",");
			patternStr.append(".*valueof.*").append(",");
			patternStr.append(".*alert.*").append(",");
			patternStr.append(".*onerror.*");
			String [] patterArray = patternStr.toString().split(",");
			for (int i = 0; i < patterArray.length; i++) {
				patternList.add(patterArray[i]);
			}
		}
		for (int i = 0; i < patternList.size(); i++) {
			boolean p = Pattern.compile(patternList.get(i),
					Pattern.CASE_INSENSITIVE).matcher(paramter).matches();
			if(p){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	

	public static boolean validateIp(String ip1, String ip2) {

			String[] ipArray = ip1.split("\\.",-1);
			String[] valueArray = ip2.split("\\.",-1);
			if (ipArray.length == 4 && valueArray.length == 4) {
				if (Integer.parseInt(valueArray[0]) > Integer.parseInt(ipArray[0])) {
					return true;
				} else if (valueArray[0].equals(ipArray[0])) {
					if (Integer.parseInt(valueArray[1]) > Integer.parseInt(ipArray[1])) {
						return true;
					} else if (valueArray[1].equals(ipArray[1])) {
						if (Integer.parseInt(valueArray[2]) > Integer.parseInt(ipArray[2])) {
							return true;
						} else if (valueArray[2].equals(ipArray[2])) {
							if (Integer.parseInt(valueArray[3]) >= Integer.parseInt(ipArray[3])) {
								return true;
							}
						}
					}
				}
			}
		
		return false;
	}
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
	
	/**
	 * 验证是否数字 
	 * @param value
	 * @return
	 */
	public static boolean checkNumber(String value) {
		return match(NUMBER, value);
	}
	
	/**
	 * 验证是不是整数
	 * 
	 * @param value
	 *            要验证的字符串 要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Integer(String value) {
		return match(V_INTEGER, value);
	}

	/**
	 * 验证是不是正整数
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Z_index(String value) {
		return match(V_Z_INDEX, value);
	}

	/**
	 * 验证是不是负整数
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Negative_integer(String value) {
		return match(V_NEGATIVE_INTEGER, value);
	}
	/**
	 * 验证密码的长度(6~18位)
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Number_length(String value) {
		return match(V_AUTH_KEY_LENGTH, value);
	}

	/**
	 * 验证密码(数字和英文同时存在)
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Password_reg(String value) {
		return match(V_AUTH_KEY_REG, value);
	}
	/**
	 * 判断当前字符串是否为合法ip地址（ip4、ip6）
	 * 
	 * @param value
	 *            ip地址（ip4、ip6）
	 * @return Boolean true通过 false 受限制
	 */
	public static boolean isIp(String value) {
		// 声明正则表达式变量
		String regEx = null;
		// 如果为空，那么返回false
		if (value == null) {
			return false;
		}
		// 如果是ip6
		if (value.contains(":")) {
			regEx = "^([\\da-fA-F]{1,4}:){6}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^::([\\da-fA-F]{1,4}:){0,4}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:):([\\da-fA-F]{1,4}:){0,3}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){2}:([\\da-fA-F]{1,4}:){0,2}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){3}:([\\da-fA-F]{1,4}:){0,1}((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){4}:((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}"
					+ "(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$|^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}$|^:((:[\\da-fA-F]{1,4}){1,6}|:)$|^[\\da-fA-F]{1,4}:"
					+ "((:[\\da-fA-F]{1,4}){1,5}|:)$|^([\\da-fA-F]{1,4}:){2}((:[\\da-fA-F]{1,4}){1,4}|:)$|^([\\da-fA-F]{1,4}:){3}"
					+ "((:[\\da-fA-F]{1,4}){1,3}|:)$|^([\\da-fA-F]{1,4}:){4}((:[\\da-fA-F]{1,4}){1,2}|:)$|^([\\da-fA-F]{1,4}:){5}:"
					+ "([\\da-fA-F]{1,4})?$|^([\\da-fA-F]{1,4}:){6}:$";
		}
		// 如果是ip4
		else {
			regEx = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
		}
		return match(regEx, value);
	}
	
	/**
	 * 判断当前字符串是否包含非法字符
	 * 
	 * @param ip
	 *            ip地址（ip4、ip6）
	 * @return Boolean true通过 false 受限制
	 */
	public static boolean isIllegalStr(String value) {
		return match(ILLEGAL_STR, value);
	}
	
	/**
	 * 验证是不是手机号码
	 * 
	 * @param ip
	 *            ip地址（ip4、ip6）
	 * @return Boolean true通过 false 受限制
	 */
	public static boolean Mobile(String value) {
		return match(V_MOBILE, value);
	}
	
	
	/**
	 * 验证是不是字母
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Letter(String value) {
		return match(V_LETTER, value);
	}

	/**
	 * 验证是不是小写字母
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Letter_i(String value) {
		return match(V_LETTER_I, value);
	}

	/**
	 * 验证是不是大写字母
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Letter_u(String value) {
		return match(V_LETTER_U, value);
	}
	
	/**
	 * 验证邮编
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean Zipcode(String value) {
		return match(V_ZIPCODE, value);
	}
	
	/**
	 * 验证是不是非负数
	 * 
	 * @param value
	 *            要验证的字符串
	 * @return 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 */
	public static boolean notNegativeNumber(String value) {
	    return match(V_NOT_NEGATIVE, value);
	}
	
	/**
	 * 验证是不是url
	 * 如果是符合格式的字符串,返回 <b>true </b>,否则为 <b>false </b>
	 * @param value
	 * @return
	 */
	public static boolean isUrl(String value){
		return match(V_URL,value);
		
	}
	
	
	
	/**
	 * 验证name
	 * @param value
	 * @return
	 */
	public static boolean checkChsEngNum(String value) {
		return match(COMMON_NAME, value);
	}
	
}
