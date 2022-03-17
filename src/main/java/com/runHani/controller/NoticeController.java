package com.runHani.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.NoticeEntity;
import com.runHani.service.NoticeService;

@Controller
@RequestMapping("notice")
public class NoticeController {

	  private String baseJSPpath = "notice";
		
	  @Autowired
	  private NoticeService noticeService;
	  
	  @RequestMapping("")
	    public ModelAndView list (ModelAndView mav) {
	        mav.setViewName(baseJSPpath+"/list");
	        	      
	        
	        
	        ArrayList<NoticeEntity> noticeList =  (ArrayList<NoticeEntity>) noticeService.selectNoticeList();
	         mav.addObject("list", noticeList);
	         
	        return mav;
	    }
	  
	  @RequestMapping(value = "/register" , method = RequestMethod.GET)
	  public ModelAndView registerPage(ModelAndView mav) {
		  mav.setViewName(baseJSPpath+"/register");
		  
		  return mav;
	  }
	  
	  @RequestMapping(value = "/register" , method = RequestMethod.POST)
	  public String register(NoticeEntity notice) {

		  System.err.println(notice);
		  
		  noticeService.postNoitce(notice);
		  
		  return "redirect:/list";
	  }
	  
	  @RequestMapping(value = "/{notice_no}" , method = RequestMethod.GET)
	  public ModelAndView postNotice(@PathVariable int  notice_no) {

		  ModelAndView mav = new ModelAndView( baseJSPpath+"/detail");
		  
		  mav.addObject("notice", (NoticeEntity)noticeService.selectNotice(notice_no));
		  
		  return mav;
	  }
	  
	  
	  
}


