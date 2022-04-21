package com.runHani.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.BoardEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.service.BoardService;
import com.runHani.util.HaniUtil;

@Controller
@RequestMapping("board")
public class BoardController {

	private String baseJSPpath = "board";

	@Autowired
	private BoardService boardService;

	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "boardNo",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(baseJSPpath + "/list");

		Page<BoardEntity> resultList = boardService.getBoardList(searchEntity, pageable);  
		List<BoardEntity> boardList = resultList.getContent();
		HashMap<String, Integer> paging = HaniUtil.calculatePaging(resultList);
		ArrayList<HashMap<String,Object>> list = boardService.boardEntitySetFile(boardList);
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list", list);
		mav.addObject("page", paging);
		mav.addObject("totalCnt", resultList.getTotalElements());
		
		HashMap map = list.get(0);
		System.err.println(map.get("board"));
		
		
		return mav;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView registerPage(ModelAndView mav) {
		mav.setViewName(baseJSPpath + "/register");

		return mav;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(BoardEntity board, MultipartHttpServletRequest req) {

		
		boardService.postBoard(board,req);
		

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/{boardNo}", method = RequestMethod.GET)
	public ModelAndView getBoard(@PathVariable int boardNo) {

		ModelAndView mav = new ModelAndView(baseJSPpath + "/detail");
		BoardEntity board =  boardService.selectBoard(boardNo);
		BoardFileEntity boardFile =  boardService.selectBoardFile(boardNo);
		

		mav.addObject("board", board);
		mav.addObject("file", boardFile);
		return mav;
	}
	
	@RequestMapping(value = "/editor/{boardNo}", method = RequestMethod.POST)
	public ModelAndView postBoard(@PathVariable int boardNo) {
		
		ModelAndView mav = new ModelAndView(baseJSPpath + "/modify");
		mav.addObject("board", (BoardEntity) boardService.selectBoard(boardNo));
		BoardFileEntity boardFile =  boardService.selectBoardFile(boardNo);
		mav.addObject("file", boardFile);
		return mav;
	}
	
	@RequestMapping(value = "/{boardNo}", method = RequestMethod.POST)
	public String updateBoard(BoardEntity board, MultipartHttpServletRequest req) {
		
		boardService.updateBoard(board,req);
		return "redirect:/board/list";
	}


	@RequestMapping(value = "/{boardNo}", method = RequestMethod.DELETE)
	public String deleteBoard(@PathVariable int boardNo) {
		
		ModelAndView result = new ModelAndView("/board");
		
		try {
			boardService.deleteBoard(boardNo);
			result.addObject("resultCode","SUCCEESS");
		} catch (Exception e) {
			result.addObject("resultCode","FAIL");
		}

		return "redirect:/board/list";
	}
	
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFile( @RequestBody HashMap param) {
		
		try {
			boardService.deleteFileByAttachedFileNo((Integer)param.get("attachedFileNo"));
		}catch (Exception e) {
			
			return "FAIL";
		}
		
			return "SUCCEESS";

	}


}
