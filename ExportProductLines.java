package com.zte.system.report.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportProductLines {
	/**
	 * 导出产品线人员投入报表
	 * 
	 * @param request
	 * @param response
	 * @param dataSet
	 */
	public static void exportProductLines(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> dataSet) {
		String title = "产品线人员投入报表";
		Boolean[] amounts = { false, false, false, false, false, false, false, false };
		ExportExcel.export(title, "productLinesTitles", "productLinesFields", dataSet, amounts, request, response);
	}

	public static void exportLinesDetailOne(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> dataSet) {
		String title = "产品线人员投入报表(应工作人天详情)";
		Boolean[] amounts = { false, false, false, false, false, false, false, false };
		ExportExcel.export(title, "linesDetailOneTitles", "linesDetailOneFields", dataSet, amounts, request, response);
	}

	public static void exportLinesDetailTwo(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> dataSet) {
		String title = "产品线人员投入报表(项目投入人天详情)";
		Boolean[] amounts = { false, false, false, false, false, false, false, false };
		ExportExcel.export(title, "linesDetailTwoTitles", "linesDetailTwoFields", dataSet, amounts, request, response);
	}

	public static void exportLinesDetailThree(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> dataSet) {
		String title = "产品线人员投入报表(其他投入人天详情)";
		Boolean[] amounts = { false, false, false, false, false, false, false, false };
		ExportExcel.export(title, "linesDetailThreeTitles", "linesDetailThreeFields", dataSet, amounts, request, response);
	}

	public static void exportLinesDetailFour(HttpServletRequest request, HttpServletResponse response, List<Map<String, Object>> dataSet) {
		Boolean[] amounts = { false, false, false, false, false, false, false, false };
		String title = "产品线人员投入报表(不饱和人天详情)";
		ExportExcel.export(title, "linesDetailFourTitles", "linesDetailFourFields", dataSet, amounts, request, response);
	}

}
