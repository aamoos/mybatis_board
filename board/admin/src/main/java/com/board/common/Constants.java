package com.board.common;

/** 서비스에 사용되는 공통변수 */

public final class Constants {
	
	//다국어 Cookie 이름
	public static final String APP_LOCALE_COOKIE = "APP_LOCALE";
	
	//프로젝트 패키지 이름
	public final static String APP_DEFAULT_PACKAGE_NAME = "com.board";
	
	//dao 패키지 경로
	public final static String MAPPER_PACKAGE = Constants.APP_DEFAULT_PACKAGE_NAME+".dao";
	
	//Tiles xml 경로
	public final static String[] TILES_LAYOUT_XML_PATH = {
			"WEB-INF/tiles.xml"
	};
	
	//Runtime에서 JSP의 refresh 적용 여부
	public final static boolean REFRESH_JSP_ON_RUNTIME = true;
	
	/** 정적 리소스 종류 */
	private final static String[] STATIC_RES = {
			 "/js"
			,"/css"
			,"/images"
			,"/favicon"
			,"/template"
			,"/font"
			,"/robots.txt"
			,"/favicon.ico"
	};
	
	/** 정적 리소스 매핑 URL 패턴 (위에꺼랑 순서 맞아야함) */
	public final static String[] STATIC_RESOURCES_URL_PATTERNS = {
			 STATIC_RES[0]+"/**"
			,STATIC_RES[1]+"/**"
			,STATIC_RES[2]+"/**"
			,STATIC_RES[3]+"/**"
			,STATIC_RES[4]+"/**"
			,STATIC_RES[5]+"/**"
			,STATIC_RES[6]
			,STATIC_RES[7]+"/**"
	};
	
	/** 정적 리소스 기본 페이지 classpath */
	private static final String STATIC_PATH = "classpath:/static";
	
	/** 정적 리스스 위치 */
	public final static String[] CLASSPATH_RESOURCE_LOCATIONS = {
			STATIC_PATH+STATIC_RES[0]+"/"
			,STATIC_PATH+STATIC_RES[1]+"/"
			,STATIC_PATH+STATIC_RES[2]+"/"
			,STATIC_PATH+STATIC_RES[3]+"/"
			,STATIC_PATH+STATIC_RES[4]+"/"
			,STATIC_PATH+STATIC_RES[5]+"/"
			,STATIC_PATH+STATIC_RES[6]
			,STATIC_PATH+STATIC_RES[7]+"/"
	};
	
	
	public static final int DEFAULT_PAGE_NUMBER = 1;
	public static final int DEFAULT_PAGE_SIZE = 10;
	
	// server health check url
	public static final String HEALTH_CHECK_URL = "/healthCheck";
	
	
}