package com.runHani.repository;

import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import com.runHani.entity.NoticeEntity;



public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

	ArrayList<NoticeEntity> findAll();
	
}
