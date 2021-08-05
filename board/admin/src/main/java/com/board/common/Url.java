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
		
	}
	
}