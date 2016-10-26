package com.zte.system.report.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class ImportExcel {
	
    private static Logger LOG = LoggerFactory.getLogger(ImportExcel.class);
	/**
	 * 读取excel文件
	 * @param filePath
	 * @param fileds
	 * @return
	 */
	public static List<Map<String, Object>> readExcel(String filePath, String[] fileds) {
		List<Map<String, Object>> data = new ArrayList<Map<String,Object>>();
		Sheet sheet;
		Workbook book;
		Cell cell;
		Map<String,Object> element;
		try {
			book = Workbook.getWorkbook(new File(filePath));
			sheet = book.getSheet(0);
			int colNum = sheet.getColumns();
			int rowNum = sheet.getRows();
			LOG.info(colNum+":"+rowNum);
			// 获取表格标题
			for (int j = 0; j < colNum; j++){
				cell = sheet.getCell(j,0);
				LOG.info("标题"+j+":" + cell.getContents());
			}
			// 读取数据行
			for (int i = 1; i < rowNum; i++){
				element = new HashMap<String,Object>();
				for (int k = 0; k < colNum; k++){
					cell = sheet.getCell(k,i);
					LOG.info(i+"*"+k+":" + cell.getContents());
					element.put(fileds[k], cell.getContents());
				}
				data.add(element);
			}
			book.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return data;
		}
	}

	public static void main(String[] args) {
		String[] fields = new String[]{"122","456"};
		readExcel("D:\\uploadPath\\excels\\2016\\20161014\\9d30ab1a-7e42-4bbc-89e5-2e4f8fe8c17e.xls",fields);
	}	
}