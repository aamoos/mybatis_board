package com.board.dao;

import java.util.Map;

/* file dao */
public interface FileMapper {
	
	/** 파일 등록 */
	int insertFile(Map<String, Object> file);
	
	/** 파일 조회 */
	Map<String, Object> getFileInfo(String fileId);
	
	/** 해당 파일 삭제처리 */
	int deleteFile(String fileId);
}
