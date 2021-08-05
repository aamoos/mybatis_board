package com.board.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.board.dao.UserMapper;
import com.board.entity.UserInfo;

@Service
public class LoginService {
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public Map<String, Object> checkLoginInsert(UserInfo userInfo) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		
		//0이면 중복되는 아이디 없음
		int duplicateCheck = userMapper.duplicateUserCheck(userInfo.getUserId());
		
		//신규등록
		if(duplicateCheck == 0) {
			//패스워드 bcrypt 암호화
			String password = passwordEncoder.encode(userInfo.getPassword());
			userInfo.setPassword(password);
			
			//사용자 등록
			userMapper.insertUser(userInfo);
			result.put("resultCode", "00");
			result.put("resultMsg", "정상적으로 회원이 등록되었습니다.");
			
			//사용자 권한 등록
			userMapper.insertUserAuth(userInfo);
		}
		
		//중복된 아이디가 있으므로 에러
		else {
			result.put("resultCode", "99");
			result.put("resultMsg", "중복된 아이디가 있습니다. 아이디를 다시 입력해주세요.");
		}
		
	return result;
	}
	
}