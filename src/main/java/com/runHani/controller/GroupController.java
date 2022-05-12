package com.runHani.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.GroupEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.service.GroupService;
import com.runHani.util.PageUtil;

@Controller
@RequestMapping("group")
public class GroupController {

	private String baseJSPpath = "group";

	@Autowired
	private GroupService groupService;

	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "sn",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(baseJSPpath + "/list");

		List<GroupEntity>  groupList = groupService.getList(pageable);
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list",groupList);

		
		HashMap<String,Object> pageInfo =  new HashMap<String, Object>();
		pageInfo.put("pageable",pageable);
		pageInfo.put("totalCnt",groupService.getListCnt());
		mav.addObject("page",PageUtil.calculatePagingByMap(pageInfo));
		
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
