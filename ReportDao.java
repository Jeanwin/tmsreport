package com.zte.system.report;

import java.util.List;
import java.util.Map;

import com.zte.system.tools.dao.SqlHandler;

public interface ReportDao  extends SqlHandler<Map<String,Object> ,Long>  {

	public List<Map<String, Object>> getReportDepts();

	public Float getAssignDays(Map<String, Object> keywords);

	public Float getProjectDays(Map<String, Object> keywords);

	public Float getOtherDays(Map<String, Object> keywords);

	public List<Map<String, Object>> getLinesDetailOne(Map<String, Object> keywords);
	
	public List<Map<String,Object>> getLinesDetailTwo(Map<String,Object> keywords);
	
	public List<Map<String,Object>> getLinesDetailThree(Map<String,Object> keywords);
	
	public List<Map<String,Object>> getLinesDetailFour(Map<String,Object> keywords);
 	
}
