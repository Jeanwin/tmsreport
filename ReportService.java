package com.zte.system.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zte.system.report.util.NumberUtil;

@Service
public class ReportService implements ReportImpl {

	@Resource
	private ReportDao reportDao;

	/**
	 * 查询产品线报表
	 * 
	 * @param keywords
	 */
	@Override
	public List<Map<String, Object>> queryProductLines(Map<String, Object> keywords) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> depts = reportDao.getReportDepts();
		Float assignDaysTotal = 0f;
		Float projectDaysTotal = 0f;
		Float otherDaysTotal = 0f;
		Float freeDaysTotal = 0f;
		BigDecimal b11 = new BigDecimal(Float.toString(assignDaysTotal));
		BigDecimal b21 = new BigDecimal(Float.toString(projectDaysTotal));
		BigDecimal b31 = new BigDecimal(Float.toString(otherDaysTotal));
		for (Map<String, Object> item : depts) {
			long id = Long.valueOf(item.get("id").toString());
			String name = item.get("name").toString();
			keywords.put("product_line_id", id);
			keywords.put("project_undertaking_org", name);
			// 应工作人天 assignDays
			Float assignDays = reportDao.getAssignDays(keywords);
			assignDays = assignDays != null ? assignDays : 0f;
			// 项目投入人天 projectDays
			Float projectDays = reportDao.getProjectDays(keywords);
			projectDays = projectDays != null ? projectDays : 0f;
			// 其他投入人天(无项目编号) otherDays
			Float otherDays = reportDao.getOtherDays(keywords);
			otherDays = otherDays != null ? otherDays : 0f;
			// 不饱和人天 freeDays
			BigDecimal b1 = new BigDecimal(Float.toString(assignDays));
			BigDecimal b2 = new BigDecimal(Float.toString(projectDays));
			BigDecimal b3 = new BigDecimal(Float.toString(otherDays));
			Float freeDays = b1.subtract(b2).subtract(b3).floatValue();
			// Float freeDays = assignDays - projectDays - otherDays;
			// 项目投入人天比 projectDaysRate

			String projectDaysRate = assignDays != 0f ? NumberUtil.convert(Float.valueOf(projectDays) / assignDays) + "%" : "0.00%";
			// 其他投入人天 比 otherDaysRate
			String otherDaysRate = assignDays != 0f ? NumberUtil.convert(Float.valueOf(otherDays) / assignDays) + "%" : "0.00%";
			// 不饱和人天 比 freeDaysRate
			String freeDaysRate = assignDays != 0f ? NumberUtil.convert(1 - Float.valueOf(otherDays + projectDays) / assignDays) + "%" : "0.00%";
			b11 = b11.add(new BigDecimal(Float.valueOf(assignDays)));
			b21 = b21.add(new BigDecimal(Float.valueOf(projectDays)));
			b31 = b31.add(new BigDecimal(Float.valueOf(otherDays)));
			/*
			 * assignDaysTotal += assignDays; projectDaysTotal += projectDays;
			 * otherDaysTotal += otherDays;
			 */
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("deptId", id);
			data.put("deptName", name);
			data.put("assignDays", assignDays);
			data.put("projectDays", projectDays);
			data.put("otherDays", otherDays);
			data.put("freeDays", freeDays);
			data.put("projectDaysRate", projectDaysRate);
			data.put("otherDaysRate", otherDaysRate);
			data.put("freeDaysRate", freeDaysRate);
			result.add(data);
		}
		// 总计
		assignDaysTotal = b11.floatValue();
		projectDaysTotal = b21.floatValue();
		otherDaysTotal = b31.floatValue();
		BigDecimal b1 = new BigDecimal(Float.toString(assignDaysTotal));
		BigDecimal b2 = new BigDecimal(Float.toString(projectDaysTotal));
		BigDecimal b3 = new BigDecimal(Float.toString(otherDaysTotal));
		freeDaysTotal = b1.subtract(b2).subtract(b3).floatValue();
		// freeDaysTotal = assignDaysTotal - projectDaysTotal - otherDaysTotal;
		String projectDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(Float.valueOf(projectDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		String otherDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(Float.valueOf(otherDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		String freeDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(1 - Float.valueOf(otherDaysTotal + projectDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		Map<String, Object> total = new HashMap<String, Object>();
		total.put("deptId", -1);
		total.put("deptName", "总计");
		total.put("assignDays", assignDaysTotal);
		total.put("projectDays", projectDaysTotal);
		total.put("otherDays", otherDaysTotal);
		total.put("freeDays", freeDaysTotal);
		total.put("projectDaysRate", projectDaysTotalRate);
		total.put("otherDaysRate", otherDaysTotalRate);
		total.put("freeDaysRate", freeDaysTotalRate);
		result.add(total);
		return result;
	}

	public List<Map<String, Object>> queryLinesDetailOne(Map<String, Object> keywords) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> details = reportDao.getLinesDetailOne(keywords);
		Float assignDaysTotal = 0f;
		Float projectDaysTotal = 0f;
		Float otherDaysTotal = 0f;
		Float freeDaysTotal = 0f;
		BigDecimal b11 = new BigDecimal(Float.toString(assignDaysTotal));
		BigDecimal b21 = new BigDecimal(Float.toString(projectDaysTotal));
		BigDecimal b31 = new BigDecimal(Float.toString(otherDaysTotal));
		for (Map<String, Object> m : details) {
			Map<String, Object> da = new HashMap<String, Object>();
			String product_line_name = m.get("product_line_name").toString();
			String employee_name = m.get("employee_name").toString();
			String employee_num = m.get("employee_num").toString();
			String period_date = m.get("period_date").toString();
			Float assigndays = Float.valueOf((m.get("assigndays") != null ? m.get("assigndays").toString() : "0"));
			Float otherdays = Float.valueOf((m.get("otherdays") != null ? m.get("otherdays").toString() : "0"));
			Float projectdays = Float.valueOf((m.get("projectdays") != null ? m.get("projectdays").toString() : "0"));
			BigDecimal b1 = new BigDecimal(Float.toString(assigndays));
			BigDecimal b2 = new BigDecimal(Float.toString(projectdays));
			BigDecimal b3 = new BigDecimal(Float.toString(otherdays));
			Float freedays = b1.subtract(b2).subtract(b3).floatValue();
			String projectDaysRate = assigndays != 0f ? NumberUtil.convert(Float.valueOf(projectdays) / assigndays) + "%" : "0.00%";
			// 其他投入人天 比 otherDaysRate
			String otherDaysRate = assigndays != 0f ? NumberUtil.convert(Float.valueOf(otherdays) / assigndays) + "%" : "0.00%";
			// 不饱和人天 比 freeDaysRate
			String freeDaysRate = assigndays != 0f ? NumberUtil.convert(1 - Float.valueOf(otherdays + projectdays) / assigndays) + "%" : "0.00%";
			b11 = b11.add(new BigDecimal(Float.valueOf(assigndays)));
			b21 = b21.add(new BigDecimal(Float.valueOf(projectdays)));
			b31 = b31.add(new BigDecimal(Float.valueOf(otherdays)));
			da.put("product_line_id", keywords.get("deptId"));
			da.put("product_line_name", product_line_name);
			da.put("employee_name", employee_name);
			da.put("employee_num", employee_num);
			da.put("period_date", period_date);
			da.put("assigndays", assigndays);
			da.put("otherdays", otherdays);
			da.put("projectdays", projectdays);
			da.put("freedays", freedays);
			da.put("projectDaysRate", projectDaysRate);
			da.put("otherDaysRate", otherDaysRate);
			da.put("freeDaysRate", freeDaysRate);
			result.add(da);
		}
		// total
		assignDaysTotal = b11.floatValue();
		projectDaysTotal = b21.floatValue();
		otherDaysTotal = b31.floatValue();
		BigDecimal b1 = new BigDecimal(Float.toString(assignDaysTotal));
		BigDecimal b2 = new BigDecimal(Float.toString(projectDaysTotal));
		BigDecimal b3 = new BigDecimal(Float.toString(otherDaysTotal));
		freeDaysTotal = b1.subtract(b2).subtract(b3).floatValue();
		// freeDaysTotal = assignDaysTotal - projectDaysTotal - otherDaysTotal;
		String projectDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(Float.valueOf(projectDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		String otherDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(Float.valueOf(otherDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		String freeDaysTotalRate = assignDaysTotal != 0f ? NumberUtil.convert(1 - Float.valueOf(otherDaysTotal + projectDaysTotal) / assignDaysTotal) + "%" : "0.00%";
		Map<String, Object> total = new HashMap<String, Object>();
		total.put("product_line_id", keywords.get("deptId"));
		total.put("product_line_name", "总计");
		total.put("employee_name", "");
		total.put("employee_num", "");
		total.put("period_date", "");
		total.put("assigndays", assignDaysTotal);
		total.put("otherdays", otherDaysTotal);
		total.put("projectdays", projectDaysTotal);
		total.put("freedays", freeDaysTotal);
		total.put("projectDaysRate", projectDaysTotalRate);
		total.put("otherDaysRate", otherDaysTotalRate);
		total.put("freeDaysRate", freeDaysTotalRate);
		result.add(total);
		return result;
	}

	public List<Map<String, Object>> queryLinesDetailTwo(Map<String, Object> keywords) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> details = reportDao.getLinesDetailTwo(keywords);
		BigDecimal tim = new BigDecimal(0f);
		for (Map<String, Object> m : details) {
			Float times = Float.valueOf((m.get("work_days") != null ? m.get("work_days").toString() : "0"));
            m.put("work_days", times);
			tim = tim.add(new BigDecimal(times));
		}
		result.addAll(details);
		Map<String, Object> total = new HashMap<String, Object>();
		for (String k : details.get(0).keySet()) {
			total.put(k, "");
		}
		total.put("employee_name", "总计");
		total.put("work_days",tim.floatValue());
		result.add(total);
		return result;
	}
	
	public List<Map<String,Object>> queryLinesDetailThree(Map<String,Object> keywords){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> details = reportDao.getLinesDetailThree(keywords);
		BigDecimal tim = new BigDecimal(0f);
		for (Map<String, Object> m : details) {
			Float times = Float.valueOf((m.get("work_days") != null ? m.get("work_days").toString() : "0"));
            m.put("work_days", times);
			tim = tim.add(new BigDecimal(times));
		}
		result.addAll(details);
		Map<String, Object> total = new HashMap<String, Object>();
		for (String k : details.get(0).keySet()) {
			total.put(k, "");
		}
		total.put("employee_name", "总计");
		total.put("work_days",tim.floatValue());
		result.add(total);
		return result;
	}
	
	public List<Map<String,Object>> queryLinesDetailFour(Map<String,Object> keywords){
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> details = reportDao.getLinesDetailFour(keywords);
		BigDecimal tim = new BigDecimal(0f);
		for (Map<String, Object> m : details) {
			Float times = Float.valueOf((m.get("work_days") != null ? m.get("work_days").toString() : "0"));
            m.put("work_days", times);
			tim = tim.add(new BigDecimal("1").subtract(new BigDecimal(times)));
		}
		result.addAll(details);
		Map<String, Object> total = new HashMap<String, Object>();
		for (String k : details.get(0).keySet()) {
			total.put(k, "");
		}
		total.put("employee_name", "总计");
		total.put("work_days",tim.floatValue());
		result.add(total);
		return result;
	}
}
