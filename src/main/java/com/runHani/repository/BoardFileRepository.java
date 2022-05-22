package com.runHani.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.transaction.annotation.Transactional;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.GroupEntity;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer>{
	 
	@Transactional
	void deleteByAttachedFileNo(Integer attachedFileNo);
	@Transactional
	void deleteByBoardEntity(BoardEntity boardEntity);
	
	BoardFileEntity findByBoardEntity(BoardEntity boardEntity);
	
	

	
}
