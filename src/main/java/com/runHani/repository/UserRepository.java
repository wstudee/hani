package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runHani.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>  {


	UserEntity findByEmailAndPassword(String email, String password);

	UserEntity findByEmail(String email);

	
}
