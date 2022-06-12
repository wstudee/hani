package com.runHani.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.runHani.entity.GroupEntity;
import com.runHani.entity.UserEntity;


public interface GroupRepository extends JpaRepository<GroupEntity, Integer>{
	 
	  @Query("select g from GroupEntity g inner join g.memberList m where m.user =  :user")
	  List<GroupEntity> selectMyGroup(UserEntity user , Pageable pageable );

	  @Query("select count(g.sn) from GroupEntity g inner join g.memberList m where m.user =  :user")
	  int selectMyGroupCnt(UserEntity user);

	Page<GroupEntity> findByGroupStatus(Pageable pageable, String string);

	  
	
}
