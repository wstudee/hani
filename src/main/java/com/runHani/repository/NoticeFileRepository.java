package com.runHani.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.NoticeFileEntity;


public interface NoticeFileRepository extends JpaRepository<NoticeFileEntity, Integer>{
	 
	@Transactional
	void deleteByAttachedFileNo(Integer attachedFileNo);
	@Transactional
	void deleteByNoticeEntity(NoticeEntity noticeEntity);

	
}
