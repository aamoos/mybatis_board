package com.board.common;

/* api url 정의 */
public final class Url {
	public static final String TILES_ROOT = "/tiles/view";
	public static final String TILES_AJAX = "/tiles/ajax";
	
	/* 로그인 */
	public static final class AUTH {
		
		/* 로그인 url */
		public static final String LOGIN = "/auth/login";
		
		/* 로그인 jsp */
		public static final String LOGIN_JSP = TILES_ROOT + "/auth/login";
		
		/* 회원가입 url */
		public static final String JOIN = "/auth/join";
		
		/* 회원가입 jsp */
		public static final String JOIN_JSP = TILES_ROOT + "/auth/join";
	
		/* 사용자 등록 */
		public static final String INSERT_USER = "/auth/insertUser";
		
		/* 로그인 인증 요청 */
		public static final String LOGIN_PROC = "/auth/login-proc";
		
		/* 로그아웃 요청 */
		public static final String LOGOUT_PROC = "/auth/logout-proc";
		
	}
	
	/* 메인 화면 */
	public static final class MAIN {
		/* 로그인 url */
		public static final String MAIN = "/";
		
		/* 로그인 jsp */
		public static final String MAIN_JSP = TILES_ROOT + "/main/list";
	}
	
}