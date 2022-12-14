package com.bn.market.web;

import com.bn.market.service.ProductServiceImpl;
import com.bn.market.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@Autowired
	UserServiceImpl userService;

	@Autowired
	ProductServiceImpl productService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		String userName = auth.getName();
		if (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			return "redirect:/admin/users";
		}
//		model.addAttribute("name", userName + "!");
		return "redirect:/user/products";
	}


}
