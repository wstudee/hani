package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runHani.entity.UserAuthEntity;
import com.runHani.entity.UserEntity;

public interface UserAuthRepository extends JpaRepository<UserAuthEntity, Long>  {



	
}
