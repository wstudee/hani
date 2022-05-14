package com.runHani.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.runHani.entity.BoardEntity;


public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{


	
	@Modifying
    @Query("UPDATE BoardEntity b SET b.boardStatus = :status WHERE b.boardNo = :noticeNo")
	void saveBoardStatus(int noticeNo, String status);

	@Query("SELECT b  FROM BoardEntity b WHERE (b.title LIKE %:searchWord% OR  b.contents like %:searchWord2%) AND b.boardStatus = :status")
	Page<BoardEntity> findByTitleContainingOrContentsContainingAndBoardStatusIs(String searchWord, String searchWord2, String status, Pageable pageable);
	Page<BoardEntity> findByTitleContainingAndBoardStatus (String searchWord,String status, Pageable pageable);
	Page<BoardEntity> findByContentsContainingAndBoardStatus(String searchWord, String status, Pageable pageable);
	



	
}
