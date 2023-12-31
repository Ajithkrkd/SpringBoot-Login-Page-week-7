package com.ajith.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ajith.model.UserDtls;
import com.ajith.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@PostMapping("/createUser")
	public String createuser( @ModelAttribute UserDtls user, HttpSession session) {
		boolean f = userService.checkEmail(user.getEmail());
		if(!user.checkvalid())
		{
			session.setAttribute("fail", "Enter valid details");	
		}

		else {

			if (f) {
				session.setAttribute("fail", "Email Id alreday exists");
			}

			else {
				UserDtls userDtls = userService.createUser(user);
				if (userDtls != null) {
					session.setAttribute("msg", "Register Sucessfully");
				} else {
					session.setAttribute("msg", "Something wrong on server");
				}
			}
		}

		
		return "redirect:/register";
	}

}