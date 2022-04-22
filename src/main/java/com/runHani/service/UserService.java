package com.runHani.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.UserRepository;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.util.FileUtils;


@Service
public class UserService  {
	
    private UserRepository userRepository;

    @Autowired
    public void setBoardRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	public boolean isUser(UserEntity user) {
		
		UserEntity checkUser = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
		
		if(checkUser == null){
			return false;
		}
		
		return true;
	}

}
