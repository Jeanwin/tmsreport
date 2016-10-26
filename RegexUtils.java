package com.zte.system.report.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	private static final String REG_EMAIL = "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$";
	private static final String REG_MAC = "^[A-F\\d]{2}[A-F\\d]{2}[A-F\\d]{2}[A-F\\d]{2}[A-F\\d]{2}[A-F\\d]{2}$";
	private static final String REG_IP = "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$";
	private static final String REG_PASSWORD = "^[a-zA-Z0-9]{6,16}$";
	private static final String REG_MOBILE = "^[1][3,4,5,8][0-9]{9}$";
	// 电话号码带区号
	private static final String REG_PHONEA = "^[0][1-9]{2,3}-[0-9]{5,10}$";
	// 电话号码不带区号
	private static final String REG_PHONEB = "^[1-9]{1}[0-9]{5,8}$";

	public static boolean isEmail(String email) {
		return regMatch(email, REG_EMAIL);
	}

	public static boolean isMAC(String mac) {
		return regMatch(mac, REG_MAC);
	}

	public static boolean isIP(String ip) {
		return regMatch(ip, REG_IP);
	}

	// 验证用户密码："^[a-zA-Z0-9]{6,16}$"正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线。
	public static boolean isPassword(String password) {
		return regMatch(password, REG_PASSWORD);
	}

	public static boolean isMobile(String mobile) {
		return regMatch(mobile, REG_MOBILE);
	}
	
	public static boolean isPhone(String phone) {
		return regMatch(phone, REG_PHONEA) || regMatch(phone, REG_PHONEB);
	}

	/**
	 * 正则匹配
	 * 
	 * @param value
	 * @param regExp
	 * @return
	 */
	public static boolean regMatch(String value, String regExp) {
		if (StringUtil.notNullOrBlankAll(new String[] { value, regExp })) {
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(value);
			return m.matches();
		}
		// 如果为空串则不匹配
		else {
			return false;
		}
	}
}