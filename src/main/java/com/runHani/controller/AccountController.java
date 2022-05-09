package com.runHani.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.runHani.entity.UserEntity;
import com.runHani.service.UserService;
import com.runHani.vo.UserSessionVO;

@Controller
@RequestMapping("account")
public class AccountController {

	private String baseJSPpath = "account";
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return baseJSPpath + "/login";
	}

	@GetMapping("/register")
	public String register() {
		return baseJSPpath + "/memberJoin";
	}

	@PostMapping("/register")
	public String register(UserEntity user, MultipartHttpServletRequest req) {
		userService.saveUser(user,req);
		return "redirect:/board/list";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loout(HttpServletRequest request, HttpServletResponseWrapper response) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/account/login";
	}

	@GetMapping("/info")
	public String userInfo() {
		return baseJSPpath + "/info";
	}

	@ResponseBody
	@RequestMapping(value = "/emailDuplicateCheck", method = RequestMethod.POST)
	public HashMap emailDuplicateCheck(@RequestBody String email) {

		HashMap map = new HashMap();
		if (userService.emailDuplicateCheck(email)) {
			map.put("result", "fail");
		} else {
			map.put("result", "success");
		}

		return map;
	}
	
	
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView profile() {
		ModelAndView result = new ModelAndView();
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(o instanceof UserSessionVO) {
			UserSessionVO user = (UserSessionVO)o;
			result.addObject("user", user);
			result.setViewName(baseJSPpath + "/profile");
			
		}else {
			result.setViewName("/account/login");
		}
		return result;
	}
	

	@RequestMapping(value = "/profileInfo", method = RequestMethod.GET)
	public ModelAndView profileInfo() {
		ModelAndView result = new ModelAndView();
		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(o instanceof UserSessionVO) {
			UserSessionVO user = (UserSessionVO)o;
			result.addObject("user", user);
			result.setViewName(baseJSPpath + "/profileModi");
			
		}else {
			result.setViewName("/account/login");
		}
		return result;
	}
	
	@PostMapping("/profileInfo")
	public String updateUser(UserEntity user, MultipartHttpServletRequest req) {
		userService.updateUser(user,req);
		
		return "redirect:/account/profile";
	}

}
