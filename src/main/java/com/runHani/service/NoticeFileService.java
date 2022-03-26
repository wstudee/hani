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
import com.runHani.repository.NoticeFileRepository;
import com.runHani.repository.NoticeRepository;
import com.runHani.util.FileUtils;


@Service
public class NoticeFileService  {
	
    private NoticeFileRepository noticeFileRepo;

    @Autowired
    public void setNoticeFileRepository(NoticeFileRepository noticeFileRepo) {
        this.noticeFileRepo = noticeFileRepo;
    }

	public NoticeFileEntity findById(int noticeNo) {
		return noticeFileRepo.findById(noticeNo).get();
	}

	



}
