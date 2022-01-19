package com.runHani.entity;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_user_bas")
public class UserEntity {

	@Id
	@Column(name = "email", nullable = false, updatable = true, length = 20) 
	private String email             ;
	
	@Column(name = "password", nullable = false, updatable = true, length = 20) 
	private String password          ;
	
	@Column(name = "user_status", nullable = false, updatable = true, length = 20) 
	private String userStatus       ;
	
	@Column(name = "login_fail_cnt", nullable = false, updatable = true, length = 20) 
	private String loginFailCnt    ;
	
	@Column(name = "profile_pic_path", nullable = false, updatable = true, length = 20) 
	private String profilePicPath  ;
	
	@Column(name = "nickname", nullable = false, updatable = true, length = 20) 
	private String nickname          ;
	
	@Column(name = "update_user", nullable = false, updatable = true, length  = 150)
	private String updateUser  ;

	@Column(name = "update_date", nullable = false, updatable = true)
	private LocalDateTime updateDate = LocalDateTime.now() ;

	@Column(name = "reg_date", nullable = false, updatable = true)
	private LocalDateTime regDate     = LocalDateTime.now() ;
	
	
	
}
