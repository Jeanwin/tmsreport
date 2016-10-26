package com.zte.system.report.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	private static ObjectMapper  jsonMapper = new ObjectMapper(); 

	public static String toJson(Object object) {
		String json = "";
		try {
			json = jsonMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	public static <T> T jsonToObject(Object json, Class<T> clazz) {
		if (json instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest) json;
			try {
				String str = new BufferedReader(new InputStreamReader(req.getInputStream(), "utf-8")).readLine();
				return jsonMapper.readValue(str, clazz);
			} catch (Exception e) {
				return null;
			}
		}
		try {
			return jsonMapper.readValue(String.valueOf(json), clazz);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}