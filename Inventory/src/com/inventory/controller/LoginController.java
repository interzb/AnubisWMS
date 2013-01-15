package com.inventory.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.inventory.util.ValidationUtil;

@Controller
public class LoginController {

	@RequestMapping("/login.do")
	public String login(
			HttpServletRequest request,
			@RequestParam(value = "username", required = false) String username,
			@RequestParam(value = "password", required = false) String password,
			ModelMap map) {
		boolean valid = true;
		if (!ValidationUtil.isValidString(username)) {
			map.put("Username_Error", "Username is required");
			valid = false;
		}
		if(!ValidationUtil.isValidString(username)) {
			map.put("Password_Error", "Password is required");
			valid = false;
		}
		if(!valid){
			return "login";
		}
		if ("admin".equals(username) && "admin".equals(password)) {
			request.getSession().setAttribute("USER", "admin");
			return "index";
		} else {
			map.put("Username_Error", "Wrong username or password,try agin");
			
			return "login";
		}

	}

}
