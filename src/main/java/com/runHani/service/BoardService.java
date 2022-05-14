package com.runHani.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.FileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.repository.BoardFileRepository;
import com.runHani.repository.BoardRepository;
import com.runHani.repository.UserRepository;
import com.runHani.util.FileUtils;
import com.runHani.vo.UserSessionVO;


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
    
	
    private  UserRepository userRepository;
    
    @Autowired
    public void setUserRepository( UserRepository userRepository) {
    	this.userRepository = userRepository;
    }
    
    
	public Page<BoardEntity> selectBoardList(Pageable pageable) {
		
		
		return (Page<BoardEntity>)boardRepository.findByBoardStatus(pageable,"I");
	}
	@SuppressWarnings("unchecked")
	public List<BoardEntity> selectNoticdeList(Pageable pageable) {
		
		
		return (List<BoardEntity>)boardRepository.findAll(pageable);
	}

	public int postBoard(BoardEntity notice) {
		
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserSessionVO sessionUser = (UserSessionVO) principal;
		
		UserEntity user = sessionUser.getUser();
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
	
	@Transactional
	public void deleteBoard(int noticeNo) throws Exception  {
		
		boardRepository.saveBoardStatus(noticeNo, "Y");
		
	}

	public void postBoard(BoardEntity notice, MultipartHttpServletRequest req) {
		
		List<FileEntity> fileList = FileUtils.parseFileinfo(req);			
		
		if(fileList.size() > 0) {
			BoardFileEntity newFile = new BoardFileEntity(fileList.get(0));
			boardFileRepository.save(newFile);
			newFile.setBoardEntity(notice);
		}
		postBoard(notice);
		
	}

	public Page<BoardEntity> selectBoardListByTitle(String searchWord, Pageable pageable) {
		return boardRepository.findByTitleContaining(searchWord,pageable);
	}

	public Page<BoardEntity> selectBoardListByTotal(String searchWord, Pageable pageable) {
		return boardRepository.findByTitleContainingOrContentsContainingAndBoardStatusIs(searchWord, searchWord ,"I" , pageable);
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

	public ArrayList<HashMap<String,Object>> boardEntitySetFile(List<BoardEntity> content) {
		ArrayList<HashMap<String,Object>> result = new ArrayList<HashMap<String,Object>>();
		
		
		for(BoardEntity board : content) {
		
		HashMap map = new HashMap();	
		
		map.put("board",board);
		map.put("file",boardFileRepository.findByBoardEntity(board));
		
		result.add(map);
		}
		 return result;
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
