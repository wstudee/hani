package com.runHani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.runHani.entity.GroupEntity;


public interface GroupRepository extends JpaRepository<GroupEntity, Integer>{
	 
	  @Query("select g from GroupEntity g")// join g.memeberList where g.id =
	  List<GroupEntity> selectMyGroup(String email);

	  
	
}
