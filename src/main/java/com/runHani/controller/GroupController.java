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

//		Page<GroupEntity> resultList = groupService.getList();  
//		List<GroupEntity> boardList = resultList.getContent();
//		HashMap<String, Integer> paging = HaniUtil.calculatePaging(resultList);
//		mav.addObject("page", paging);
//		mav.addObject("totalCnt", resultList.getTotalElements());
		
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list", groupService.getList());
		
		return mav;
	}
	
	
	@RequestMapping("new")
	public ModelAndView newGroupFormat() {
		ModelAndView mav = new ModelAndView(baseJSPpath + "/new");
		
		return mav;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(GroupEntity group, MultipartHttpServletRequest req) {

		
		groupService.registerGroup(group,req);
		

		return "redirect:/board/list";
	}


}
