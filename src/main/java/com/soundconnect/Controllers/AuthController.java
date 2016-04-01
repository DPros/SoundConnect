package com.soundconnect.Controllers;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;    
  
@Controller  
public class AuthController {  
   
	@RequestMapping("/auth")  
	public String hello(Model model, SecurityContextHolderAwareRequestWrapper request) { 
		model.addAttribute("message", "This is data from Controller");
		return "auth";  
	}  
} 