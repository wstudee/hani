package com.runHani.vo;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.runHani.entity.UserEntity;
import com.runHani.entity.UserProfileFileEntity;

import lombok.Data;


@Data
public class UserSessionVO implements UserDetails

{
	
	private String email             ;
	
	private String password          ;
	
	private String userStatus       ;
	
	private String loginFailCnt    ;
	
	private UserProfileFileEntity profilePicPath  ;
	
	private String nickname          ;
	
	private Boolean enable          ;
	
	private UserEntity user  ;
	
	public UserSessionVO() {

	}
	
	public UserSessionVO(UserEntity user) {
		this.email = user.getEmail();
		this.password = user.getPassword();
		this.userStatus = user.getUserStatus();
		this.loginFailCnt = user.getLoginFailCnt();
		this.profilePicPath = user.getProfilePicPath();
		this.nickname = user.getNickname();
		this.enable = user.getEnable();
		this.user = user;
		
		
	}

	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return null;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return enable;
	}

	@Override
	public boolean isAccountNonLocked() {
		return enable;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return enable;
	}

	@Override
	public boolean isEnabled() {
		return enable;
	}

}
