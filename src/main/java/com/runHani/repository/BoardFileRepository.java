package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;


public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Integer>{
	 
	@Transactional
	void deleteByAttachedFileNo(Integer attachedFileNo);
	@Transactional
	void deleteByBoardEntity(BoardEntity boardEntity);

	
}
