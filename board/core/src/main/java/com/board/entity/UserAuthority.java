package com.board.entity;

import org.springframework.security.core.GrantedAuthority;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAuthority implements GrantedAuthority {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String authority;
	private String authorityNm;

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
}