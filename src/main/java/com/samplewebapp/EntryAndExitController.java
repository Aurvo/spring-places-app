package com.samplewebapp;

import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("username")
public class EntryAndExitController {
	
	@Autowired
	GetUserService getUserService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String defaultURL() {
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/home", method=RequestMethod.GET)
	public String homeURL(ModelMap model) {
		model.addAttribute("activePage", "home");
		model.addAttribute("username", getUserService.getLoggedInUserName());
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet(@RequestParam(required = false) String error,
			ModelMap model) {
		model.addAttribute("activePage", "login");
		model.addAttribute("invalidInput", error != null);
		return "login";		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			(new SecurityContextLogoutHandler()).logout(request, response, auth);
			request.getSession().invalidate();
		}
		model.addAttribute("activePage", "logout");
		return "logout";
	}
}
