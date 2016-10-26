package com.zte.system.report.util;
public class StringUtil {
	/**
	 * 
	 * @param properties
	 * @return
	 */
	public static boolean notNullOrBlankAll(String[] values) {
		for (int i = 0; i < values.length; i++) {
			boolean flag = notNullOrBlank(values[i]);
			if (!flag) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 判断字段是否为null或空串
	 * 
	 * @param value
	 * @return
	 */
	public static boolean notNullOrBlank(String value) {
		if (value != null && !"".equals(value)) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		System.out.println(notNullOrBlankAll(new String[] { "1232", "13434", null }));
	}
}