package com.zte.system.report.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExportPersonalHours {
	
	/**
	 * 导出人员工时投入报表
	 * @param request
	 * @param response
	 * @param dataSet
	 */
	public static void exportPersonalHours(HttpServletRequest request, HttpServletResponse response,List<Map<String,Object>> dataSet){
  		String title="人员工时投入报表";
  		Boolean[] amounts = {false, false, false, false, false, false, false, false};
		ExportExcel.export(title, "personalHoursTitles", "personalHoursFields", dataSet, amounts, request, response);
  	}
	
	public static void exportHoursDetailOne(HttpServletRequest request, HttpServletResponse response,List<Map<String,Object>> dataSet){
  		String title="人员工时投入报表(项目投入人天详情)";
  		Boolean[] amounts = {false, false, false, false, false, false, false, false};
		ExportExcel.export(title, "hoursDetailOneTitles", "hoursDetailOneFields", dataSet, amounts, request, response);
  	}
	
	public static void exportHoursDetailTwo(HttpServletRequest request, HttpServletResponse response,List<Map<String,Object>> dataSet){
  		String title="人员工时投入报表(其他投入人天详情)";
  		Boolean[] amounts = {false, false, false, false, false, false, false, false};
		ExportExcel.export(title, "hoursDetailTwoTitles", "hoursDetailTwoFields", dataSet, amounts, request, response);
  	}
	
	public static void exportHoursDetailThree(HttpServletRequest request, HttpServletResponse response,List<Map<String,Object>> dataSet){
		String title="人员工时投入报表(不饱和人天详情)";
  		Boolean[] amounts = {false, false, false, false, false, false, false, false};
		ExportExcel.export(title, "hoursDetailThreeTitles", "hoursDetailThreeFields", dataSet, amounts, request, response);
  	}
	
}
