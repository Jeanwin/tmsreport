package com.zte.system.report.util;

public class NumberUtil {
	public static String convert(double value) {
		return String.format("%.2f", value*100.00);
	}
}
