package com.zte.system.report;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zte.system.report.util.ExportProductLines;
import com.zte.system.report.util.JsonUtil;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	private static Logger LOG = LoggerFactory.getLogger(ReportController.class);
	
	@Resource
	private ReportService reportService;
	/**
	 * 产品线人员投入页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/productLines")
	public String productLines(HttpServletRequest request, Model model) {
		return "system/report/productLines";
	}
	
	@RequestMapping("/queryProductLines")
	@ResponseBody
	public List<Map<String,Object>> queryProductLines(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> keywords = JsonUtil.jsonToObject(request, Map.class);
		LOG.info("queryProductLines request arguments:"+keywords.toString());
		return reportService.queryProductLines(keywords);
	}
	/**********************************************************************/
	/**
	 * 产品线人员投入>应工作人天详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/linesDetailOne")
	public String linesDetailOne(HttpServletRequest request, Model model) {
		return "system/report/linesDetailOne";
	}
	@RequestMapping("/queryLinesDetailOne")
	@ResponseBody
	public List<Map<String,Object>> queryLinesDetailOne(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> keywords = JsonUtil.jsonToObject(request, Map.class);
		LOG.info("queryLinesDetailOne request arguments:"+keywords.toString());
		return reportService.queryLinesDetailOne(keywords);
	}
	/**********************************************************************/
	/**
	 * 产品线人员投入>项目投入人天详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/linesDetailTwo")
	public String linesDetailTwo(HttpServletRequest request, Model model) {
		return "system/report/linesDetailTwo";
	}
	@RequestMapping("/queryLinesDetailTwo")
	@ResponseBody
	public List<Map<String,Object>> queryLinesDetailTwo(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> keywords = JsonUtil.jsonToObject(request, Map.class);
		LOG.info("queryLinesDetailTwo request arguments:"+keywords.toString());
		return reportService.queryLinesDetailTwo(keywords);
	}
	/**
	 * 产品线人员投入页面>其他投入人天详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/linesDetailThree")
	public String linesDetailThree(HttpServletRequest request, Model model) {
		return "system/report/linesDetailThree";
	}
	@RequestMapping("/queryLinesDetailThree")
	@ResponseBody
	public List<Map<String,Object>> queryLinesDetailThree(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> keywords = JsonUtil.jsonToObject(request, Map.class);
		LOG.info("queryLinesDetailThree request arguments:"+keywords.toString());
		return reportService.queryLinesDetailThree(keywords);
	}
	/**
	 * 产品线人员投入页面>不饱和人天详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/linesDetailFour")
	public String linesDetailFour(HttpServletRequest request, Model model) {
		return "system/report/linesDetailFour";
	}
	@RequestMapping("/queryLinesDetailFour")
	@ResponseBody
	public List<Map<String,Object>> queryLinesDetailFour(HttpServletRequest request, HttpServletResponse response) {
		@SuppressWarnings("unchecked")
		Map<String,Object> keywords = JsonUtil.jsonToObject(request, Map.class);
		LOG.info("queryLinesDetailFour request arguments:"+keywords.toString());
		return reportService.queryLinesDetailFour(keywords);
	}
	
	/**
	 * 人员工时投入页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/personalHours")
	public String personalHours(HttpServletRequest request, Model model) {
		return "system/report/personalHours";
	}
	/**
	 * 人员工时投入页面 > 项目投入人天详情 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/hoursDetailOne")
	public String hoursDetailOne(HttpServletRequest request, Model model) {
		return "system/report/hoursDetailOne";
	}
	/**
	 * 人员工时投入页面  > 其他投入人天详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/hoursDetailTwo")
	public String hoursDetailTwo(HttpServletRequest request, Model model) {
		return "system/report/hoursDetailTwo";
	}
	/**
	 * 人员工时投入页面 > 不饱和人天投入详情
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/hoursDetailThree")
	public String hoursDetailThree(HttpServletRequest request, Model model) {
		return "system/report/hoursDetailThree";
	}
	
	/**
	 * 人员成本分配页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/personCosts")
	public String personCosts(HttpServletRequest request, Model model) {
		return "system/report/personCosts";
	}
}
