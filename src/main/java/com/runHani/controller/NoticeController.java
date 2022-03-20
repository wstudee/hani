package com.runHani.controller;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
		  
		  return "redirect:/notice";
	  }
	  
	  @RequestMapping(value = "/{noticeNo}" , method = RequestMethod.GET)
	  public ModelAndView postNotice(@PathVariable int  noticeNo) {

		  ModelAndView mav = new ModelAndView( baseJSPpath+"/detail");
		  
		  mav.addObject("notice", (NoticeEntity)noticeService.selectNotice(noticeNo));
		  
		  return mav;
	  }

	  @RequestMapping(value = "/{noticeNo}" , method = RequestMethod.DELETE)
	  public ResponseEntity  deleteNotice(@PathVariable int  noticeNo){

		  try {
			noticeService.deleteNotice(noticeNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		  
		  return ResponseEntity
			       .ok().contentType(MediaType.APPLICATION_JSON)
			       .header("Allow", "DELETE")
			       .build();
		  
	  }
	  
	  
	  
}


