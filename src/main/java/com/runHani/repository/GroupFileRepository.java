package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.GroupFileEntity;


public interface GroupFileRepository extends JpaRepository<GroupFileEntity, Integer>{

	GroupFileEntity findByGroup(GroupEntity groupEntity);

	GroupFileEntity findByAttachedFileNo(int fileNo);

	

	
}
