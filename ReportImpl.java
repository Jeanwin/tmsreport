package com.zte.system.report;

import java.util.List;
import java.util.Map;

public interface ReportImpl {
	
	public List<Map<String,Object>> queryProductLines(Map<String, Object> keywords);
	
}
