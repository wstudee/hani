package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.GroupEntity;


public interface GroupRepository extends JpaRepository<GroupEntity, Integer>{
	 
	

	
}
