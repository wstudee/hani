package com.runHani.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runHani.entity.NoticeEntity;
import com.runHani.repository.NoticeRepository;


@Service
public class NoticeService  {
	
    private NoticeRepository noticeRepo;

    @Autowired
    public void setPersonRepository(NoticeRepository noticeRepo) {
        this.noticeRepo = noticeRepo;
    }
	
	public List<NoticeEntity> selectNoticeList() {
		System.err.println("--");
		System.err.println("err----------------------------"+(noticeRepo == null));
		System.err.println("err----------------------------"+ noticeRepo.toString());
		
		
		return (List<NoticeEntity>) noticeRepo.findAll();
	}

}
