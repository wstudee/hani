package com.runHani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runHani.entity.GroupEntity;
import com.runHani.entity.UserEntity;
import com.runHani.entity.UserGroupEntity;


public interface UserGroupRepository extends JpaRepository<UserGroupEntity, Integer>{

	List<UserGroupEntity> findByGroupSn(GroupEntity group);
	 
	
	  
	
}
