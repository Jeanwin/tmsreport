package com.zte.system.report.util;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;


/**
 * TODO Comment of JProperties
 * 
 * @author jeawin
 * @version v1.0
 */
public class ReadProperties {
	/**
	 * 读取配置文件
	 * @return
	 */
	public static Properties loadProperties(String fileName){
		Properties prop = new Properties();  
		try {
			prop.load(new InputStreamReader(ReadProperties.class.getResourceAsStream(fileName), "UTF-8")); 
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return prop;
		
	}
    /**
     * 
     * @param name
     * @return
     */
    public static String getByName(String name){
    	return loadProperties("report.properties").getProperty(name);
    }
    public static void main(String[] args) {
		System.out.println(Thread.currentThread().getContextClassLoader().getResource("").toString());
	}
}