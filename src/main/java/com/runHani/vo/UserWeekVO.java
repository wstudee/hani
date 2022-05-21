package com.runHani.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.runHani.entity.UserEntity;
import com.runHani.entity.UserProfileFileEntity;

import lombok.Data;


@Data
public class UserWeekVO 
{
	
	private String email;
	private String date;
	private String cnt;
	private List info;
	
	

}
