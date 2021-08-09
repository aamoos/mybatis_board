package com.board.common;

/* api url 정의 */
public final class Url {
	public static final String TILES_ROOT = "/tiles/view";
	public static final String TILES_SINGLE = "/tiles/single";
	public static final String TILES_AJAX = "/tiles/ajax";
	
	/* 로그인 */
	public static final class AUTH {
		
		/* 로그인 url */
		public static final String LOGIN = "/auth/login";
		
		/* 로그인 jsp */
		public static final String LOGIN_JSP = TILES_SINGLE + "/auth/login";
		
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
		
		public static final String _MAIN_AJAX_ROOT_PATH = "/main/ajax";
		
		/* 메인 url */
		public static final String MAIN = "/";
		
		/* 메인 jsp */
		public static final String MAIN_JSP = TILES_ROOT + "/main/list";
		
		/* 메인 리스트 ajax */
		public static final String MAIN_LIST_AJAX = _MAIN_AJAX_ROOT_PATH + "/list-view";
		
		/* 메인 글쓰기 */
		public static final String MAIN_WRITE = "/board/write";
		
		/* 메인 글쓰기 jsp */
		public static final String MAIN_WRITE_JSP = TILES_ROOT + "/main/write";
		
		/* 메인 수정화면 */
		public static final String MAIN_UPDATE = "/board/update";
		
		/* 메인 글쓰기 jsp */
		public static final String MAIN_UPDATE_JSP = TILES_ROOT + "/main/update";
		
		
	}
	
}