package com.zte.system.report;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.zte.system.report.util.ImportExcel;
import com.zte.system.report.util.JsonMsg;
import com.zte.system.report.util.JsonUtil;
import com.zte.system.report.util.ReadProperties;
import com.zte.system.report.util.UploadExcel;

@Controller
@RequestMapping("/report")
public class ImportController {
	
	private static Logger LOG = LoggerFactory.getLogger(ImportController.class);
	
	/**
	 * 导入excell
	 * @param req
	 * @param res
	 */
	@RequestMapping(value = "/importPersonProductLines", method = RequestMethod.POST)
	public void importPersonProductLines(MultipartHttpServletRequest req, HttpServletResponse res) {
		JsonMsg msg = new JsonMsg("0","importPersonProductLines","导入失败");
		String path = UploadExcel.uploadExcel(null, req, null);
		path = ReadProperties.getByName("uploadPath")+File.separator+path;
		File file = new File(path);
		if(file.exists()){
			String[] fileds = new String[]{"12323","3434545","34354"}; 
			ImportExcel.readExcel(path,fileds);
		}
		LOG.info("upload file path :"+path);
		try {
			msg.setId("1");
			msg.setContent("导入成功");
			res.getWriter().write(JsonUtil.toJson(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
