package com.ajith.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ajith.config.UserServiceImpl;
import com.ajith.model.UserDtls;

@Controller
@RequestMapping("/admin")
public class adminController {

	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/")
	public String getadminPage(Model model, Authentication auth) {

		List<UserDtls> users = userService.getALLuser();
//		System.out.println(auth.getPrincipal());
		model.addAttribute("authentication", auth);
		model.addAttribute("users", users);
		if (auth != null) {
			return "admin/admin";
		}

		return "signin";

	}
	
	@GetMapping("/delete/{id}")
	public String deleteByUser(@PathVariable("id") Integer userId, RedirectAttributes redirectAttributes) {
		
		String userName = userService.getUserNameById(userId);
		boolean deleted = userService.deleteUserId(userId);
		

		if (deleted) {
			redirectAttributes.addFlashAttribute("successMessage", "User  "+ userName +"  deleted successfully!");
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting user with ID: " + userId);
		}

		return "redirect:/admin/";
	}
	
	@GetMapping("/edit/{id}")
	public String editUser(@PathVariable("id") int userId, Model model) {
		UserDtls userFinded = userService.getUserbyId(userId);

		model.addAttribute("users", userFinded);

		return "edit";
	}

	@PostMapping("/update")
	public String updateUser(@ModelAttribute UserDtls user, HttpSession session) {

		userService.editUserById(user);

		session.setAttribute("msg", "User updated successfully");
		return "redirect:/admin/";

	}

	@GetMapping("/searchfilter")
	public String searchByname(@RequestParam("fullName") String fullName, Model model, Authentication auth) {

		List<UserDtls> list = userService.getUserbyName(fullName);

		model.addAttribute("authentication", auth);
		model.addAttribute("users", list);
		return "admin/admin";
	}

	@GetMapping("/create")
	public String createByAdmin( UserDtls user, Model model) {
		model.addAttribute("users", user);
		return "admin/createAdmin";
	}

}
