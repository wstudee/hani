package com.runHani.repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
	
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.UserEntity;


public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{


	
	@Modifying
    @Query("UPDATE BoardEntity b SET b.boardStatus = :status WHERE b.boardNo = :noticeNo")
	void saveBoardStatus(int noticeNo, String status);

	@Query("SELECT b  FROM BoardEntity b WHERE (b.title LIKE %:searchWord% OR  b.contents like %:searchWord2%) AND b.boardStatus = :status")
	Page<BoardEntity> findByTitleContainingOrContentsContainingAndBoardStatusIs(String searchWord, String searchWord2, String status, Pageable pageable);
	Page<BoardEntity> findByTitleContainingAndBoardStatus (String searchWord,String status, Pageable pageable);
	Page<BoardEntity> findByContentsContainingAndBoardStatus(String searchWord, String status, Pageable pageable);

	List<BoardEntity> findByRegUserAndGroup(String string, int i);

	@Query(value = "select to_char(b.d,'yyyy-mm-dd') as date , count(a) as cnt from (select * from tb_board_bas where reg_user =:userEntity	and group_sn = :group and reg_date > to_date(:lastSunday,'yyyy-mm-dd') ) a "
			+ "right join test1 b on to_char(a.reg_date, 'yyyymmdd') =  to_char(b.d, 'yyyymmdd') group by to_char(b.d,'yyyy-mm-dd') order by date ", nativeQuery = true)
	List<List> findByRegUserAndGroupAndAfterRegDate(UserEntity userEntity, GroupEntity group, String lastSunday);
	



	
}
