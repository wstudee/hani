package com.runHani.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runHani.entity.UserEntity;
import com.runHani.service.CmmService;

@Controller
public class CmmController {
	
	@Autowired // 필요한 의존 객체의 “타입"에 해당하는 빈을 찾아 주입
	private CmmService cmmService;
	
	@RequestMapping("/signupview")
	public String signupView(@ModelAttribute("userVO") UserEntity userVO
			, Model model
			, BindingResult result
			, HttpServletRequest request) {
		System.out.println();
		return "/cmm/signup";
	}
	
	@RequestMapping("/signup")
	public String signup(@ModelAttribute("userVO") UserEntity userVO
			, Model model
			, BindingResult result
			, HttpServletRequest request) {
		System.out.println("=========controller==========");
		System.out.println(userVO.toString());
		
		
		cmmService.signup(userVO);
		
		return "redirect:/";
	}

}
