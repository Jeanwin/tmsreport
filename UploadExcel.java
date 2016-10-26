package com.zte.system.report.util;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class UploadExcel {
	/**
	 * 
	 * @param resourcePath
	 *            项目下相对路径 如:upload/images/
	 * @param req
	 * @param delName
	 * @return
	 */
	public static String uploadExcel(String resourcePath, MultipartHttpServletRequest req, String delName) {
		/*
		 * //绝对路径 String absPath =
		 * req.getSession().getServletContext().getRealPath(""); //项目名 String
		 * projectName =
		 * absPath.substring(absPath.lastIndexOf(File.separatorChar));
		 */
		String folder = resourcePath;
		if (StringUtils.isEmpty(resourcePath)) {
			resourcePath = ReadProperties.getByName("uploadPath")+File.separatorChar;
			folder = "";
		} else {
			folder = resourcePath;
			resourcePath =ReadProperties.getByName("uploadPath")+File.separatorChar + resourcePath;
		}
		MultipartFile imgFile = req.getFile("file");
		if (!StringUtils.isEmpty(imgFile.getOriginalFilename())) {
			String filName = getFile(imgFile, null, Arrays.asList(ReadProperties.getByName("excellExtensions").split(",")));
			if (StringUtils.isNotEmpty(filName) && StringUtils.isNotBlank(filName) && delName != null && !"".equals(delName)) {
				delName = resourcePath + delName;
				System.out.println("delname:" + delName);
				File file = new File(delName);
				if (file.exists()) {
					file.delete();
				}
			}
			return folder + filName;
		}
		return null;
	}

	private static String getFile(MultipartFile imgFile, String path, List<String> fileTypes) {
		String filename = imgFile.getOriginalFilename(); 
		String fileName = IdUtils.uuid();
		System.out.println("filename:"+filename);
		String ext = filename.split("\\.")[filename.split("\\.").length-1];
		fileName += ".";
		fileName += ext;
		String rePath = "";
		if (path == null) {
			path = ReadProperties.getByName("uploadPath")+File.separatorChar;
			rePath = getTypePath(ext);
			path += rePath;
		}
		ext = ext.toLowerCase();
		if (fileTypes.contains(ext)) { 
			try {
				File file = creatFolder(path, fileName);
				imgFile.transferTo(file); 
				return getTypePath(ext) + file.getName();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 分类存储路径
	 */
	private static String getTypePath(String ext) {
		String path = DateUtils.getFormat("yyyy") + File.separator + DateUtils.getFormat("yyyyMMdd") + File.separator;
		if (Arrays.asList(ReadProperties.getByName("excellExtensions").split(",")).contains(ext)) {
			return Arrays.asList(ReadProperties.getByName("typePaths").split(",")).get(1) + File.separator + path;
		} else if(Arrays.asList(ReadProperties.getByName("imageExtensions").split(",")).contains(ext)){
			return Arrays.asList(ReadProperties.getByName("typePaths").split(",")).get(0) + File.separator + path;
		}else {
			return Arrays.asList(ReadProperties.getByName("typePaths").split(",")).get(2) + File.separator + path;
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param path
	 *            绝对路径
	 * @param fileName
	 *            文件名
	 * @return
	 */
	private static File creatFolder(String path, String fileName) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(file, fileName);
		return file;
	}
}