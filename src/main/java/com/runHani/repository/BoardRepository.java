package com.runHani.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.runHani.entity.BoardEntity;


public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{

	Page<BoardEntity> findByTitleContaining(String searchWord, Pageable pageable);

	Page<BoardEntity> findByTitleContainingOrContentsContaining(String searchWord, String searchWord2, Pageable pageable);

	Page<BoardEntity> findByContentsContaining(String searchWord, Pageable pageable);


	
}
