package com.runHani.util;

import org.springframework.security.core.context.SecurityContextHolder;

import com.runHani.entity.UserEntity;
import com.runHani.vo.UserSessionVO;

public class SessionUtil {

	
	public static UserSessionVO getUserSession() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserSessionVO result = null;
		
		if(principal != null) {
			result =  (UserSessionVO) principal;
		}
		return result;
	}
	
	public static UserEntity getUserEntity() {
		UserEntity result = null;
		UserSessionVO  sessionU = getUserSession();
		if(sessionU != null) {
			result =  sessionU.getUser();
		}
		return result;
	}
	
}
