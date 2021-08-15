package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.board.dao.MainMapper;

import lombok.extern.slf4j.Slf4j;

@Service("mainService")
@Slf4j
public class MainService {

	@Autowired MainMapper mainMapper;
	
	//게시판 조회
	public Page<Map<String, Object>> selectBoardList(Map<String, Object> params, Pageable pageable, int size){
		return new PageImpl<>(mainMapper.selectBoardList(params, pageable, size), pageable, mainMapper.selectBoardListCnt(params));
	}
	
	//게시판 삭제
	public void deleteBoard(List<String> boardIdxArray) {
		for(int i=0; i<boardIdxArray.size(); i++) {
			mainMapper.deleteBoard(boardIdxArray.get(i));
		}
	}
	
	//게시판 조회수 없데이트
	public int updateViewCount(int boardIdx) {
		//조회수 업데이트
		int viewCount = (int) mainMapper.selectBoard(boardIdx).get("viewCount");
		log.info("viewCount={}", viewCount);
		Map<String, Object> params = new HashMap<String, Object>();
		viewCount++;
		params.put("boardIdx", boardIdx);
		params.put("viewCount", viewCount);
		
 		//조회수 업데이트
		return mainMapper.updateViewCount(params);
	}
	
}
