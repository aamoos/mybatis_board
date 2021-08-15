package com.board.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import lombok.extern.slf4j.Slf4j;

/**
 * 메뉴 관리 유틸리티
 * @version 1.0.2
 */

@Slf4j
public class MenuUtil {
	private static Map<String, String> urlMap = getUrlMap(Url.class.getClasses());

	private static Map<String, String> getMap(String prefix, Class<?> target) {
		Map<String, String> map = new HashMap<String, String>();
		for (Field fields : target.getFields()) {
			try {
				Object object = fields.get(target);
				if (object instanceof String) {
					String name = fields.getName();
					if (!"NAME".equals(name) && !name.contains("_JSP") && !name.contains("PERMISSION")) {
						map.put(prefix + "." + name, (String) object);
					}
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
		}	
		return map;
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, String> getUrlMap(Class[] classes) {
		Map<String, String> urlMap = new HashMap<String, String>();
		for (Class<?> clazz : classes) {
			urlMap.putAll(getMap(clazz.getSimpleName(), clazz));
		}
		return urlMap;
   }

	public static String getUrl(String key, String val1, String val2) {
		if (!urlMap.containsKey(key)) {
			throw new RuntimeException(key + "에 해당하는 URL이 없습니다.");
		}
		String url = urlMap.get(key);
		if (val1 != null) {
			url = url.replaceFirst("\\{[^\\}]*\\}", val1);
		}
		if (val2 != null) {
			url = url.replaceFirst("\\{[^\\}]*\\}", val2);
		}
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		return request.getContextPath() + url;
	}

	public static String getUrl(String key, String val1) {
		return getUrl(key, val1, null);
	}

	public static String getUrl(String key) {
		return getUrl(key, null, null);
	}

	//Tiles Path
	public static String replaceValue(String url, String val1, String val2) {
		if (val1 != null)
			url = url.replaceFirst("\\{[^\\}]*\\}", val1);
		if (val2 != null)
			url = url.replaceFirst("\\{[^\\}]*\\}", val2);
		return url;
	}

	public static String replaceValue(String url, String val1) {
		return replaceValue(url, val1, null);
	}
}

