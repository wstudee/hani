package com.runHani.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserAuthEntity;
import com.runHani.entity.UserEntity;
import com.runHani.entity.UserProfileFileEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.UserProfileFileRepository;
import com.runHani.repository.UserRepository;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.util.FileUtils;
import com.runHani.vo.UserSessionVO;


@Service
public class UserService  {
	
    private UserRepository userRepository;

    @Autowired
    public void setBoardRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    private UserProfileFileRepository userFileRepository;
    
    @Autowired
    public void setBoardRepository(UserProfileFileRepository userFileRepository) {
    	this.userFileRepository = userFileRepository;
    }
    
    
    @Autowired
    private PasswordEncoder passwordEncoder;

	public boolean isUser(UserEntity user) {
		
		UserEntity checkUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(checkUser == null){
			return false;
		}
		
		return true;
	}
	public boolean emailDuplicateCheck(String email) {

		UserEntity checkUser = userRepository.findByEmail(email);
		
		if(checkUser == null){
			return false;
		}
		
		
		return true;
	}
	public boolean saveUser(UserEntity user, MultipartHttpServletRequest req) {

		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setEnable(true);
        
        UserAuthEntity userAuth = new UserAuthEntity();
        userAuth.setUser(user);
        userAuth.setAuthority("USER");
        
        
    	List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
    	UserProfileFileEntity newFile = null;
		if(fileList.size() > 0) {
			newFile = new UserProfileFileEntity(fileList.get(0));
			userFileRepository.save(newFile);
			user.setProfilePicPath(userFileRepository.save(newFile));
		}
        
		
		UserEntity saveUser = userRepository.save(user);
		if(newFile!=null) {
			newFile.setUser(saveUser);
			userFileRepository.save(newFile);
		}
		
        
		if(saveUser == null){
			return false;
		}
		
		return true;
	}
	public UserProfileFileEntity findByFileId(int fileNo) {

		
		return userFileRepository.findById(fileNo).get();
	}
	public boolean updateUser(UserEntity newUserInfo, MultipartHttpServletRequest req) {
		
		
		UserEntity user = userRepository.findById(newUserInfo.getEmail()).get();

		
		//save nickname
		String nickName = newUserInfo.getNickname();
		user.setNickname(nickName);
    	
		
		//save parseFileinfo
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
    	UserProfileFileEntity newFile = null;
    	UserProfileFileEntity saveFile = null;
    	if(fileList.size() > 0) {
			newFile = new UserProfileFileEntity(fileList.get(0));
			userFileRepository.save(newFile);
			saveFile = userFileRepository.save(newFile);
		}else {
			saveFile = newUserInfo.getProfilePicPath();
		}
    	user.setProfilePicPath(saveFile);
    	
    	
    	
    	//main updateUser
		UserEntity saveUser = userRepository.save(user);
		
		//file new save
		if(newFile!=null) {
			newFile.setUser(saveUser);
			userFileRepository.save(newFile);
		}

		
		//update session
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(saveUser!=null) {
			if(o instanceof UserSessionVO) {
				UserSessionVO suser = (UserSessionVO)o;
				suser.setNickname(nickName);
				suser.setProfilePicPath(saveFile);
			}
		}

		
		
		return true;
        		
	}

}
