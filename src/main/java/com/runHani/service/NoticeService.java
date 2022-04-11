package com.runHani.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.NoticeEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.NoticeFileRepository;
import com.runHani.repository.NoticeRepository;
import com.runHani.util.FileUtils;


@Service
public class NoticeService  {
	
    private NoticeRepository noticeRepo;

    @Autowired
    public void setNoticeRepository(NoticeRepository noticeRepo) {
        this.noticeRepo = noticeRepo;
    }
    
    private  NoticeFileRepository noticeFileRepository;
    
    @Autowired
    public void setNoticeFileRepository( NoticeFileRepository noticeFileRepository) {
        this.noticeFileRepository = noticeFileRepository;
    }
    
	
	public Page<NoticeEntity> selectNoticeList(Pageable pageable) {
		
		
		return (Page<NoticeEntity>)noticeRepo.findAll(pageable);
	}
	@SuppressWarnings("unchecked")
	public List<NoticeEntity> selectNoticdeList(Pageable pageable) {
		
		
		return (List<NoticeEntity>)noticeRepo.findAll(pageable);
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
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		List<NoticeFileEntity> noticeFileList = new ArrayList<NoticeFileEntity>();			
		
		for(FileEntity file : fileList) {
			NoticeFileEntity newFile = new NoticeFileEntity(file);
			newFile.setNoticeEntity(notice);
			noticeFileList.add(newFile);
		}

		
		if(!CollectionUtils.isEmpty(noticeFileList)) {
			notice.setFileList(noticeFileList);
		}
		postNotice(notice);
	}

	public Page<NoticeEntity> selectNoticeListByTitle(String searchWord, Pageable pageable) {
		return noticeRepo.findByTitleContaining(searchWord,pageable);
	}

	public Page<NoticeEntity> selectNoticeListByTotal(String searchWord, Pageable pageable) {
		return noticeRepo.findByTitleContainingOrContentsContaining(searchWord,searchWord ,pageable);
	}

	public Page<NoticeEntity> selectNoticeListByContents(String searchWord, Pageable pageable) {
		return noticeRepo.findByContentsContaining(searchWord,pageable);
	}

	public Page<NoticeEntity> getNoticeList(SearchEntity searchEntity, Pageable pageable) {

		Page<NoticeEntity> resultList =  null; 
		String searchCriteria = searchEntity.getSearchCriteria()==null ? "" : searchEntity.getSearchCriteria();
		String searchWord = searchEntity.getSearchWord()==null ? "" : searchEntity.getSearchWord();
		
		
		switch(searchCriteria) {
			case "title" : resultList =  selectNoticeListByTitle(searchWord,pageable); break;
			case "contents" :resultList =  selectNoticeListByContents(searchWord,pageable);  break;
			default : resultList =  selectNoticeListByTotal(searchWord,pageable);
			
		}
		return resultList;
	}

	public void updateNotice(NoticeEntity notice, MultipartHttpServletRequest req) {
		
		postNotice(notice,req);
	}


	public void deleteFile(Integer notice) {
		
		noticeFileRepository.deleteByNoticeEntity(noticeRepo.findById(notice).get());
	}


	public void deleteFileByAttachedFileNo(Integer attNo) {
		noticeFileRepository.deleteByAttachedFileNo(attNo);
		
	}

}
