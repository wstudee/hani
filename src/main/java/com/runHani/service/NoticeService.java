package com.runHani.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.runHani.entity.NoticeEntity;
import com.runHani.repository.NoticeRepository;
@Service
public class NoticeService {


	NoticeRepository noticeRepo;
	
	public ArrayList<NoticeEntity> selectNoticeList() {
		
		//return noticeRepo.findAll();
		return null;
	}

}
