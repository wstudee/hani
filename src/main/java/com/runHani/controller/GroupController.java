package com.runHani.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.catalina.User;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.BoardEntity;
import com.runHani.entity.BoardFileEntity;
import com.runHani.entity.GroupEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.entity.UserEntity;
import com.runHani.entity.UserGroupEntity;
import com.runHani.repository.UserRepository;
import com.runHani.service.BoardService;
import com.runHani.service.GroupService;
import com.runHani.util.PageUtil;
import com.runHani.vo.UserWeekVO;

@Controller
@RequestMapping("group")
public class GroupController {

	private String task = "group";

	@Autowired
	private GroupService groupService;
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity,
			@PageableDefault(size = 10, sort = "sn", direction = Sort.Direction.DESC) Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/list");

		List<GroupEntity> groupList = groupService.getList(pageable);
		mav.addObject("searchEntity", searchEntity);
		mav.addObject("list", groupList);

		HashMap<String, Object> pageInfo = new HashMap<String, Object>();
		pageInfo.put("pageable", pageable);
		pageInfo.put("totalCnt", groupService.getListCnt());
		mav.addObject("page", PageUtil.calculatePagingByMap(pageInfo));

		return mav;
	}

	@RequestMapping("allList")
	public ModelAndView allList(SearchEntity searchEntity,
			@PageableDefault(size = 10, sort = "sn", direction = Sort.Direction.DESC) Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/list");

		Page<GroupEntity> resultList = groupService.getAllList(pageable);
		List<GroupEntity> boardList = resultList.getContent();
		HashMap<String, Integer> paging = PageUtil.calculatePaging(resultList);
		mav.addObject("searchEntity", searchEntity);
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
		boolean isMember = groupService.isMember(group);
		List<UserGroupEntity> memebers = group.getMemeberList();

		
		
		mav.addObject("group", group);
		mav.addObject("isMember", isMember);
		mav.addObject("memebers", memebers);

		return mav;
	}

	@RequestMapping(value ="baordList", method = {RequestMethod.GET,RequestMethod.POST} )
	public ModelAndView listBoard(@RequestParam int grouSn, @PageableDefault( size = 10, sort = "boardNo",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/jspf/boardList");
		
		GroupEntity group = groupService.findById(grouSn);
		Page<BoardEntity> resultList = boardService.getBoardListByGroup(group, pageable);  
		List<BoardEntity> boardList = resultList.getContent();
		HashMap<String, Integer> paging = PageUtil.calculatePaging(resultList);
		mav.addObject("list", boardList);
		mav.addObject("page", paging);
		mav.addObject("totalCnt", resultList.getTotalElements());
		
		return mav;
	}
	
	
	@RequestMapping(value = "/member/{groupNo}", method = RequestMethod.POST)
	public String joinGroup(@PathVariable int groupNo) {

		groupService.registerGroup(groupNo);
		return "redirect:/group/list";
	}

	// TODO: 탈퇴기능

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(GroupEntity group, MultipartHttpServletRequest req) {

		groupService.registerGroup(group, req);

		return "redirect:/board/list";
	}

	@RequestMapping(value = "/memeberWeek", method = RequestMethod.POST)
	@ResponseBody
	public List<UserWeekVO>  selectMemeberWeekRecord(@RequestBody HashMap param) throws ParseException {

		GroupEntity group = groupService.findById((int) param.get("groupSn"));
		List<UserGroupEntity> memebers = group.getMemeberList();

		List<UserWeekVO> result = new ArrayList<UserWeekVO>();
		

		for (UserGroupEntity user : memebers) {
			HashMap<String,String> tmpList =  groupService.selectMemeberWeekRecord(user.getUser(), group, param);
			UserWeekVO userW =  new UserWeekVO();
			userW.setEmail(user.getUser().getEmail());
			userW.setInfo(tmpList);
			result.add(userW);
		}
		
		
		return result;

	}
	
	@DeleteMapping(value = "memeber/{groupNo}")
	public String leaveGroup(@PathVariable int groupNo)  {

	//	groupService.leaveGroup(userGroup);

		return "redirect:/group/list";
	}

}
