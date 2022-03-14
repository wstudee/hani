package com.runHani.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runHani.entity.UserEntity;
import com.runHani.repository.UserRepository;

@Service
public class CmmService {

	@Autowired
	private UserRepository userRepo;
	
	public void signup(UserEntity userVO) {
		System.out.println("========service===========");
		System.out.println(userVO.getEmail());
		
		userRepo.save(userVO);
	}

}
