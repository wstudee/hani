package com.runHani.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.runHani.entity.UserEntity;
import com.runHani.service.BoardService;
import com.runHani.service.UserService;
import com.runHani.util.HaniUtil;

@Controller
@RequestMapping("account")
public class UserController {

	private String baseJSPpath = "account";

	@Autowired
	private UserService userService;


    @GetMapping("/login")
    public String login(@Parameter String error) {
        return "account/login";
    }

    @GetMapping("/register")
    public String register() {
        return "account/memberJoin";
    }

    @PostMapping("/register")
    public String register(UserEntity user) {
        userService.saveUser(user);
        return "redirect:/";
    }
	
	/*
	 * @RequestMapping(value = "login", method = RequestMethod.GET) public
	 * ModelAndView loginView() { ModelAndView mav = new ModelAndView(baseJSPpath +
	 * "/login");
	 * 
	 * return mav; }
	 * 
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "login", method = RequestMethod.POST) public HashMap
	 * login(UserEntity user) {
	 * 
	 * HashMap map = new HashMap(); if(userService.isUser(user)) { map.put("result",
	 * "success");
	 * 
	 * }else { map.put("result", "fail"); }
	 * 
	 * return map; }
	 * 
	 * 
	 * @RequestMapping(value = "register", method = RequestMethod.GET) public
	 * ModelAndView memberJoinView() { ModelAndView mav = new
	 * ModelAndView(baseJSPpath + "/memberJoin");
	 * 
	 * return mav; }
	 * 
	 * @RequestMapping(value = "register", method = RequestMethod.POST) public
	 * ModelAndView memberJoin(UserEntity user) { ModelAndView mav = new
	 * ModelAndView();
	 * 
	 * userService.saveUser(user); mav.setViewName("/account/login");
	 * 
	 * return mav; }
	 * 
	 * 
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "emailDuplicateCheck", method = RequestMethod.POST)
	 * public HashMap emailDuplicateCheck(UserEntity user) {
	 * 
	 * HashMap map = new HashMap(); if(userService.emailDuplicateCheck(user)) {
	 * map.put("result", "success");
	 * 
	 * }else { map.put("result", "fail"); }
	 * 
	 * return map; }
	 */

}
