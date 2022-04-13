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

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.util.FileUtils;


@Service
public class BoardService  {
	
    private BoardRepository boardRepository;

    @Autowired
    public void setBoardRepository(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    
    private  BoardFileRepository boardFileRepository;
    
    @Autowired
    public void setBoardFileRepository( BoardFileRepository boardFileRepository) {
        this.boardFileRepository = boardFileRepository;
    }
    
	
	public Page<BoardEntity> selectBoardList(Pageable pageable) {
		
		
		return (Page<BoardEntity>)boardRepository.findAll(pageable);
	}
	@SuppressWarnings("unchecked")
	public List<BoardEntity> selectNoticdeList(Pageable pageable) {
		
		
		return (List<BoardEntity>)boardRepository.findAll(pageable);
	}

	public int postBoard(BoardEntity notice) {
		
		UserEntity user = new UserEntity();
		
		user.setEmail("admin");
		notice.setUpdateUser(user);
		notice.setRegUser(user);
		
		if(boardRepository.save(notice)!=null) {
			return 1;
		}else {
			return -1;
		}
		
	}

	public BoardEntity selectBoard(int notice_no) {
		
		return boardRepository.findById(notice_no).get();
	}

	public void deleteBoard(int noticeNo) throws Exception  {
		

		boardRepository.deleteById(noticeNo);
		
	}

	public void postBoard(BoardEntity notice, MultipartHttpServletRequest req) {
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		
		
		BoardFileEntity newFile = new BoardFileEntity(fileList.get(0));
		postBoard(notice);
		newFile.setBoardEntity(notice);
		boardFileRepository.save(newFile);
		
	}

	public Page<BoardEntity> selectBoardListByTitle(String searchWord, Pageable pageable) {
		return boardRepository.findByTitleContaining(searchWord,pageable);
	}

	public Page<BoardEntity> selectBoardListByTotal(String searchWord, Pageable pageable) {
		return boardRepository.findByTitleContainingOrContentsContaining(searchWord,searchWord ,pageable);
	}

	public Page<BoardEntity> selectBoardListByContents(String searchWord, Pageable pageable) {
		return boardRepository.findByContentsContaining(searchWord,pageable);
	}

	public Page<BoardEntity> getBoardList(SearchEntity searchEntity, Pageable pageable) {

		Page<BoardEntity> resultList =  null; 
		String searchCriteria = searchEntity.getSearchCriteria()==null ? "" : searchEntity.getSearchCriteria();
		String searchWord = searchEntity.getSearchWord()==null ? "" : searchEntity.getSearchWord();
		
		
		switch(searchCriteria) {
			case "title" : resultList =  selectBoardListByTitle(searchWord,pageable); break;
			case "contents" :resultList =  selectBoardListByContents(searchWord,pageable);  break;
			default : resultList =  selectBoardListByTotal(searchWord,pageable);
			
		}
		return resultList;
	}

	public void updateBoard(BoardEntity notice, MultipartHttpServletRequest req) {
		
		postBoard(notice,req);
	}


	public void deleteFile(Integer notice) {
		
		boardFileRepository.deleteByBoardEntity(boardRepository.findById(notice).get());
	}


	public void deleteFileByAttachedFileNo(Integer attNo) {
		boardFileRepository.deleteByAttachedFileNo(attNo);
		
	}


	public BoardFileEntity findByFileId(int fileNo) {
		// TODO Auto-generated method stub
		return boardFileRepository.findById(fileNo).get();
	}


	public BoardFileEntity selectBoardFile(int boardNo) {
		
		return  boardFileRepository.findByBoardEntity(boardRepository.findById(boardNo).get());
		
	}

}
