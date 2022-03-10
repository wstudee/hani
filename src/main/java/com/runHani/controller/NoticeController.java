package com.runHani.controller;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.NoticeEntity;
import com.runHani.service.NoticeService;

@Controller
public class NoticeController {

	  private String baseJSPpath = "notice";
		
	  @Autowired
	  private NoticeService noticeService;
	  
	  @RequestMapping("/list")
	    public ModelAndView Index(ModelAndView mav) {
	        mav.setViewName(baseJSPpath+"/list");
	        	      
	        ArrayList<NoticeEntity> noticeList =  (ArrayList<NoticeEntity>) noticeService.selectNoticeList();
	         mav.addObject("list", noticeList);
	         
	        return mav;
	    }
	  
	  
	  
	  
}


