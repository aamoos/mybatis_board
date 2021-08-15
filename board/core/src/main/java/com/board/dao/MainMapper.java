package com.board.dao;


import java.util.List;
import java.util.Map;
import org.springframework.data.domain.Pageable;
import org.apache.ibatis.annotations.Param;

public interface MainMapper {

	//글쓰기 리스트 조회
	public List<Map<String, Object>> selectBoardList(
			 @Param(value = "params") Map<String, Object> params
			,@Param(value = "pageable") Pageable pageable
			,@Param(value = "size") int size);
	
	//글쓰기 리스트 count
	int selectBoardListCnt(@Param(value = "params") Map<String, Object> params);
	
	//글쓰기 등록
	public int insertBoard(Map<String, Object> params);
	
	//글 수정
	public int updateBoard(Map<String, Object> params);
	
	//글쓰기 상세조회
	public Map<String, Object> selectBoard(int boardIdx);
	
	//게시판 파일리스트 조회
	public List<Map<String, Object>> selectBoardFile(int boardIdx);
	
	//조회수 업데이트
	public int updateViewCount(Map<String, Object> params);
	
	//겍시판 삭제
	public int deleteBoard(String boardIdx);

	//글쓰기 파일 등록
	public int insertBoardFile(Map<String, Object> params);
	
}
