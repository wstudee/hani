package com.runHani.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.entity.UserGroupEntity;
import com.runHani.repository.UserRepository;
import com.runHani.service.GroupService;
import com.runHani.util.PageUtil;

@Controller
@RequestMapping("group")
public class GroupController {

	private String task = "group";

	@Autowired
	private GroupService groupService;

	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "sn",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/list");

		List<GroupEntity>  groupList = groupService.getList(pageable);
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list",groupList);

		
		HashMap<String,Object> pageInfo =  new HashMap<String, Object>();
		pageInfo.put("pageable",pageable);
		pageInfo.put("totalCnt",groupService.getListCnt());
		mav.addObject("page",PageUtil.calculatePagingByMap(pageInfo));
		
		return mav;
	}
	
	@RequestMapping("allList")
	public ModelAndView allList(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "sn",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/list");
		

		Page<GroupEntity> resultList = groupService.getAllList(pageable);  
		List<GroupEntity> boardList = resultList.getContent();
		HashMap<String, Integer> paging = PageUtil.calculatePaging(resultList);
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list", boardList);
		mav.addObject("page", paging);
		mav.addObject("totalCnt", resultList.getTotalElements());
		
		
		return mav;
	}
	
	


	@RequestMapping("new")
	public ModelAndView newGroupFormat() {
		ModelAndView mav = new ModelAndView(task + "/new");
		
		return mav;
	}

	@RequestMapping(value = "/{groupNo}", method = RequestMethod.GET)
	public ModelAndView detailView(@PathVariable int groupNo) {
		
		ModelAndView mav = new ModelAndView(task + "/detail");
		GroupEntity group = groupService.findById(groupNo);
		
		boolean isMember  = groupService.isMember(group);
		
		mav.addObject("group", group);
		mav.addObject("isMember", isMember);
		
		return mav;
	}

	
	@RequestMapping(value = "/member/{groupNo}", method = RequestMethod.POST)
	public String joinGroup(@PathVariable int groupNo) {
		
		groupService.registerGroup(groupNo);
		return "redirect:/group/list";
	}

	//TODO: 탈퇴기능
	
	
}
