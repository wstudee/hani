package com.runHani.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.NoticeEntity;
import com.runHani.entity.NoticeFileEntity;
import com.runHani.entity.SearchEntity;
import com.runHani.service.NoticeService;
import com.runHani.util.PageUtil;

@Controller
@RequestMapping("notice")
public class NoticeController {

	private String task = "notice";

	@Autowired
	private NoticeService noticeService;

	
	@RequestMapping("list")
	public ModelAndView list(SearchEntity searchEntity, @PageableDefault( size = 10, sort = "noticeNo",direction = Sort.Direction.DESC)  Pageable pageable) {
		ModelAndView mav = new ModelAndView(task + "/list");

		Page<NoticeEntity> resultList = noticeService.getNoticeList(searchEntity, pageable);  
		List<NoticeEntity> noticeList = resultList.getContent();
		HashMap<String, Integer> paging = PageUtil.calculatePaging(resultList);
		
		mav.addObject("searchEntity",searchEntity);
		mav.addObject("list", noticeList);
		mav.addObject("page", paging);
		mav.addObject("totalCnt", resultList.getTotalElements());
		
		
		return mav;
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView registerPage(ModelAndView mav) {
		mav.setViewName(task + "/register");

		return mav;
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String register(NoticeEntity notice, MultipartHttpServletRequest req) {

		
		noticeService.postNotice(notice,req);
		

		return "redirect:/notice/list";
	}

	@RequestMapping(value = "/{noticeNo}", method = RequestMethod.GET)
	public ModelAndView getNotice(@PathVariable int noticeNo) {

		ModelAndView mav = new ModelAndView(task + "/detail");
		NoticeEntity notice =  noticeService.selectNotice(noticeNo);
		mav.addObject("notice", notice);
		return mav;
	}
	
	@RequestMapping(value = "/editor/{noticeNo}", method = RequestMethod.POST)
	public ModelAndView postNotice(@PathVariable int noticeNo) {
		
		ModelAndView mav = new ModelAndView(task + "/modify");
		mav.addObject("notice", (NoticeEntity) noticeService.selectNotice(noticeNo));
		return mav;
	}
	
	@RequestMapping(value = "/{noticeNo}", method = RequestMethod.POST)
	public String updateNotice(NoticeEntity notice, MultipartHttpServletRequest req) {
		
		noticeService.updateNotice(notice,req);
		return "redirect:/notice/list";
	}


	@RequestMapping(value = "/{noticeNo}", method = RequestMethod.DELETE)
	public String deleteNotice(@PathVariable int noticeNo) {
		
		ModelAndView result = new ModelAndView("/notice");
		
		try {
			noticeService.deleteNotice(noticeNo);
			result.addObject("resultCode","SUCCEESS");
		} catch (Exception e) {
			result.addObject("resultCode","FAIL");
		}

		return "redirect:/notice/list";
	}
	
	
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	@ResponseBody
	public String deleteFile( @RequestBody HashMap param) {
		
		try {
			noticeService.deleteFileByAttachedFileNo((Integer)param.get("attachedFileNo"));
		}catch (Exception e) {
			
			return "FAIL";
		}
		
			return "SUCCEESS";

	}


}
