package com.board.entity;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfo implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//사용자 id
	private String userId;
	
	//사용자 이름
	private String userName;
	
	//패스워드
	private String password;
	
	//이메일
	private String email;
	
	//핸드폰 반호
	private String handPhoneNo;
	
	//사용여부
	private String useYn;

	//권한 list
	public List<UserAuthority> authorities;
	
	//권한
	public String authority;
	
	//권한 이름
	public String authorityNm;
	
	public String auth;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}