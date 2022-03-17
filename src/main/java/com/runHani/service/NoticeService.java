package com.runHani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.NoticeRepository;


@Service
public class NoticeService  {
	
    private NoticeRepository noticeRepo;

    @Autowired
    public void setPersonRepository(NoticeRepository noticeRepo) {
        this.noticeRepo = noticeRepo;
    }
	
	public List<NoticeEntity> selectNoticeList() {
		
		return (List<NoticeEntity>) noticeRepo.findAll();
	}

	public int postNoitce(NoticeEntity notice) {
		
		UserEntity user = new UserEntity();
		
		user.setEmail("admin");
		notice.setUpdateUser(user);
		notice.setRegUser(user);
		
		if(noticeRepo.save(notice)!=null) {
			return 1;
		}else {
			return -1;
		}
		
	}

	public NoticeEntity selectNotice(int notice_no) {

		return noticeRepo.findById(notice_no).get();
		
	}

	public void deleteNotice(int noticeNo) throws Exception  {
		

		noticeRepo.deleteById(noticeNo);
		
	}

}
