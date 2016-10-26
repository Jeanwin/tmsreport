package com.zte.system.report.util;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {
	
    /**
     * 导出excel	
     * @param title
     * @param headersName
     * @param filedsName
     * @param dataSet
     * @param amounts
     * @param request
     * @param response
     */
	public static void export(String title,String headersName,String filedsName,List<Map<String,Object>> dataSet,Boolean[] amounts,HttpServletRequest request, HttpServletResponse response){
	    String[] headers = ReadProperties.getByName(headersName).split(",");
		String[] fields = ReadProperties.getByName(filedsName).split(",");
		List<Object[]> datas = new ArrayList<Object[]>();
		for(Map<String,Object> da : dataSet)
		{
			Object[] obj=new Object[fields.length];
			for(int i = 0; i<fields.length; i++){
				obj[i] = da.get(fields[i]);
			}
			datas.add(obj);
		}
		try {
			ExportExcel.exportEexcel(response, request,title, headers,datas, amounts);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 导出excell
	 * @param title
	 * @param headers
	 * @param datas
	 * @param amounts
	 * @throws Exception
	 */
	@SuppressWarnings("static-access")
	public static void exportEexcel(HttpServletResponse response, HttpServletRequest request, String title, String[] headers, List<Object[]> datas, Boolean[] amounts) throws Exception {
		HSSFWorkbook workbook=ExportXLSUtil.exportExcel(title, headers, datas, amounts);
		if(workbook==null)
		{
			return;
		}
		OutputStream out = response.getOutputStream();
		try {
			response.setContentType("application/vnd.ms-excel;charset=UTF-8"); 
			response.setCharacterEncoding("utf-8");
			if(StringUtils.isNotBlank(title)){  
				int viewType = getBrowingType(request);
				if(1==viewType) {
					title = URLEncoder.encode(title, "UTF-8");
				}else{
					title = new String(title.getBytes("UTF-8"), "ISO-8859-1");
				}
			    response.setHeader("Content-disposition","attachment; filename="+title+".xls");  
			}else{  
			    response.setHeader("Content-disposition","attachment; filename=SheetExcel.xls");  
			}  
            workbook.write(out);
			response.flushBuffer();
			response.setStatus(response.SC_OK); 
		} catch (Exception e) {
		} 
		finally 
		{
		
			out.close();
		}
	}
	
    public static Integer getBrowingType(HttpServletRequest request) {
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE")) {
            return 1;
        } else if (null != agent && -1 != agent.indexOf("Firefox")) {
            return 2;
        } else if (null != agent && -1 != agent.indexOf("Safari")) {
            return 3;
        } else {
            return 4;
        }
    }

}