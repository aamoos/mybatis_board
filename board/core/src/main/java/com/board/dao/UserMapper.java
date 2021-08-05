package com.board.dao;

import com.board.entity.UserInfo;
import com.board.entity.UserAuthority;

public interface UserMapper {

	//사용자 정보 조회
	UserInfo getUserInfo(String userId);
	
	//사용자 권한 조회
	UserAuthority getUserAuthorities(String userId);
	
	//사용자 등록
	public int insertUser(UserInfo userinfo);
	
	//사용자 권한 등록
	public int insertUserAuth(UserInfo userinfo);
	
	//사용자 중복체크
	int duplicateUserCheck(String userId);
	
}