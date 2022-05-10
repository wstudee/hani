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
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.runHani.entity.GroupEntity;
import com.runHani.entity.BoardEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.service.BoardService;
import com.runHani.service.GroupService;
import com.runHani.util.HaniUtil;

@Controller
@RequestMapping("group")
public class GroupController {

	private String baseJSPpath = "group";

	@Autowired
	private GroupService groupService;

	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "boardNo",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(baseJSPpath + "/list");

//		Page<GroupEntity> resultList = groupService.getList(searchEntity);  
//		List<GroupEntity> boardList = resultList.getContent();
		List<GroupEntity> boardList = groupService.getList(searchEntity);  

		
		
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list", boardList);
//		mav.addObject("page", paging);
//		mav.addObject("totalCnt", resultList.getTotalElements());
		
		
		return mav;
	}
	
	
	@RequestMapping("new")
	public ModelAndView newGroupFormat() {
		ModelAndView mav = new ModelAndView(baseJSPpath + "/new");
		
		return mav;
	}



}
