package com.zte.system.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zte.system.report.util.ExportPersonalHours;
import com.zte.system.report.util.ExportProductLines;
import com.zte.system.report.util.JsonUtil;

@Controller
@RequestMapping("/report")
public class ExportController {
    
	private static Logger LOG = LoggerFactory.getLogger(ExportController.class);
	
	@Resource
	private ReportService reportService;
	/**
	 * 导出产品线人员投入excell
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/productLinesExport")
	public void productLinesExport(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> keywords = new HashMap<String,Object>();
        keywords.put("startDate", request.getParameter("startDate"));
		keywords.put("endDate", request.getParameter("endDate"));
		List<Map<String,Object>> dataSet = reportService.queryProductLines(keywords);
		ExportProductLines.exportProductLines(request, response, dataSet);
	}
	
	@RequestMapping("/exportLinesDetailOne")
	public void exportLinesDetailOne(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> keywords = new HashMap<String,Object>();
        keywords.put("deptId", request.getParameter("deptId"));
		keywords.put("startDate", request.getParameter("startDate"));
		keywords.put("endDate", request.getParameter("endDate"));
		List<Map<String,Object>> dataSet = reportService.queryLinesDetailOne(keywords);
		ExportProductLines.exportLinesDetailOne(request, response, dataSet);
	}
	
	@RequestMapping("/exportLinesDetailTwo")
	public void exportLinesDetailTwo(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> keywords = new HashMap<String,Object>();
        keywords.put("deptId", request.getParameter("deptId"));
		keywords.put("startDate", request.getParameter("startDate"));
		keywords.put("endDate", request.getParameter("endDate"));
		LOG.info("exportLinesDetailTwo request arguments:"+keywords.toString());
		List<Map<String,Object>> dataSet = reportService.queryLinesDetailTwo(keywords);
		ExportProductLines.exportLinesDetailTwo(request, response, dataSet);
	}
	
	@RequestMapping("/exportLinesDetailThree")
	public void exportLinesDetailThree(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> keywords = new HashMap<String,Object>();
        keywords.put("deptId", request.getParameter("deptId"));
		keywords.put("startDate", request.getParameter("startDate"));
		keywords.put("endDate", request.getParameter("endDate"));
		LOG.info("exportLinesDetailThree request arguments:"+keywords.toString());
		List<Map<String,Object>> dataSet = reportService.queryLinesDetailThree(keywords);
		ExportProductLines.exportLinesDetailThree(request, response, dataSet);
	}
	
	@RequestMapping("/exportLinesDetailFour")
	public void exportLinesDetailFour(HttpServletRequest request, HttpServletResponse response) {
		Map<String,Object> keywords = new HashMap<String,Object>();
        keywords.put("deptId", request.getParameter("deptId"));
		keywords.put("startDate", request.getParameter("startDate"));
		keywords.put("endDate", request.getParameter("endDate"));
		LOG.info("exportLinesDetailFour request arguments:"+keywords.toString());
		List<Map<String,Object>> dataSet = reportService.queryLinesDetailFour(keywords);
		ExportProductLines.exportLinesDetailFour(request, response, dataSet);
	}
	/****************************************************************************/
	
	/**
	 * 导出人员工时投入报表
	 * @param request
	 * @param response
	 */
	@RequestMapping("/personalHoursExport")
	public void personalHoursExport(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String,Object>> dataSet = new ArrayList<Map<String,Object>>();
		ExportPersonalHours.exportPersonalHours(request, response, dataSet);
	}
	
	@RequestMapping("/exportHoursDetailOne")
	public void exportHoursDetailOne(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String,Object>> dataSet = new ArrayList<Map<String,Object>>();
		ExportPersonalHours.exportHoursDetailOne(request, response, dataSet);
	}
	
	@RequestMapping("/exportHoursDetailTwo")
	public void exportHoursDetailTwo(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String,Object>> dataSet = new ArrayList<Map<String,Object>>();
		ExportPersonalHours.exportHoursDetailTwo(request, response, dataSet);
	}
	
	@RequestMapping("/exportHoursDetailThree")
	public void exportHoursDetailThree(HttpServletRequest request, HttpServletResponse response) {
		List<Map<String,Object>> dataSet = new ArrayList<Map<String,Object>>();
		ExportPersonalHours.exportHoursDetailThree(request, response, dataSet);
	}
	
}
