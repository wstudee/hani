package com.runHani.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.runHani.entity.UserEntity;

@Controller
public class CmmController {
	
	@RequestMapping("/signupview.do")
	public String signupView(@ModelAttribute UserEntity userVO
			, Model model) {
		return "/cmm/signup";
	}

}
