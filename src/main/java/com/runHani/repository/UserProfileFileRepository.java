package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.runHani.entity.UserProfileFileEntity;


public interface UserProfileFileRepository extends JpaRepository<UserProfileFileEntity, Long>  {


	
}
