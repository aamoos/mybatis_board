package com.board.common;

/* api url ���� */
public final class Url {
	public static final String TILES_ROOT = "/tiles/view";
	public static final String TILES_AJAX = "/tiles/ajax";
	
	/* �α��� */
	public static final class AUTH {
		
		/* �α��� url */
		public static final String LOGIN = "/auth/login";
		
		/* �α��� jsp */
		public static final String LOGIN_JSP = TILES_ROOT + "/auth/login";
		
		/* ȸ������ url */
		public static final String JOIN = "/auth/join";
		
		/* ȸ������ jsp */
		public static final String JOIN_JSP = TILES_ROOT + "/auth/join";
		
	}
	
}