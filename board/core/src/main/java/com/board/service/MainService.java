package com.board.service;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.board.dao.MainMapper;

@Service("mainService")
public class MainService {

	@Autowired MainMapper mainMapper;
	
	public Page<Map<String, Object>> selectBoardList(Map<String, Object> params, Pageable pageable, int size){
		return new PageImpl<>(mainMapper.selectBoardList(params, pageable, size), pageable, mainMapper.selectBoardListCnt(params));
	}
	
}
