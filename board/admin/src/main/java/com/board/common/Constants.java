package com.board.common;

/** ���񽺿� ���Ǵ� ���뺯�� */

public final class Constants {
	
	//������Ʈ ��Ű�� �̸�
	public final static String APP_DEFAULT_PACKAGE_NAME = "com.board";
	
	//dao ��Ű�� ���
	public final static String MAPPER_PACKAGE = Constants.APP_DEFAULT_PACKAGE_NAME+".dao";
	
	//Tiles xml ���
	public final static String[] TILES_LAYOUT_XML_PATH = {
			"WEB-INF/tiles.xml"
	};
	
	//Runtime���� JSP�� refresh ���� ����
	public final static boolean REFRESH_JSP_ON_RUNTIME = true;
}