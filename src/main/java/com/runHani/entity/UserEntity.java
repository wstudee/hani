package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_user_bas")
public class UserEntity {

	@Id
	@Column(name = "email", nullable = false, updatable = true, length = 20) 
	private String email             ;
	
	@Column(name = "password", nullable = false, updatable = true, length = 20) 
	private String password          ;
	
	@Column(name = "user_status", nullable = true, updatable = true, length = 20) 
	private String userStatus       ;
	
	@Column(name = "login_fail_cnt", nullable = true, updatable = true, length = 20) 
	private String loginFailCnt    ;
	
	@Column(name = "profile_pic_path", nullable = true, updatable = true, length = 20) 
	private String profilePicPath  ;
	
	@Column(name = "nickname", nullable = true, updatable = true, length = 20) 
	private String nickname          ;
	
	@Column(name = "update_user", nullable = true, updatable = true, length  = 150)
	private String updateUser  ;

	@Column(name = "update_date", nullable = true, updatable = true)
	private LocalDateTime updateDate = LocalDateTime.now() ;

	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public String getLoginFailCnt() {
		return loginFailCnt;
	}

	public void setLoginFailCnt(String loginFailCnt) {
		this.loginFailCnt = loginFailCnt;
	}

	public String getProfilePicPath() {
		return profilePicPath;
	}

	public void setProfilePicPath(String profilePicPath) {
		this.profilePicPath = profilePicPath;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}
	
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("email : " + this.email + " / ");
		str.append("password : " + this.password + " / ");
		str.append("userStatus : " + this.userStatus + " / ");
		str.append("loginFailCnt : " + this.loginFailCnt + " /\n ");
		str.append("profilePicPath : " + this.profilePicPath + " / ");
		str.append("nickname : " + this.nickname + " / ");
		str.append("updateUser : " + this.updateUser + " /\n ");
		str.append("updateDate : " + this.updateDate + " / ");
		str.append("regDate : " + this.regDate + " / ");
		
		
		return str.toString();
	}
	
	
}
