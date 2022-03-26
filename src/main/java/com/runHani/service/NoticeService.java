package com.runHani.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.NoticeRepository;
import com.runHani.util.FileUtils;


@Service
public class NoticeService  {
	
    private NoticeRepository noticeRepo;

    @Autowired
    public void setNoticeRepository(NoticeRepository noticeRepo) {
        this.noticeRepo = noticeRepo;
    }
	
	public List<NoticeEntity> selectNoticeList() {
		
		return (List<NoticeEntity>) noticeRepo.findAll();
	}

	public int postNotice(NoticeEntity notice) {
		
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

	public void postNotice(NoticeEntity notice, MultipartHttpServletRequest req) {

		
		
		List<NoticeFileEntity> fileList = FileUtils.parseFileinfo(req);			
			
		if(!CollectionUtils.isEmpty(fileList)) {
			notice.setFileList(fileList);
		}
		postNotice(notice);
	}


}
