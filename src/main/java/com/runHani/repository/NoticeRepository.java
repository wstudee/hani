package com.runHani.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.runHani.entity.NoticeEntity;


public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>{

	Page<NoticeEntity> findByTitleContaining(String searchWord, Pageable pageable);

	
}
