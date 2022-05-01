package com.runHani.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.tiles.autotag.core.runtime.annotation.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
	public String login() {
		return baseJSPpath + "/login";
	}

	@GetMapping("/register")
	public String register() {
		return baseJSPpath + "/memberJoin";
	}

	@PostMapping("/register")
	public String register(UserEntity user) {
		userService.saveUser(user);
		return "redirect:/";
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

}
