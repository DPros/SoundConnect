package com.soundconnect.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/home")
public class MainController {
	@RequestMapping(method=RequestMethod.POST)
	public String viewMain(Model model, HttpServletRequest request){
		return "main";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public String getViewMain(Model model, HttpServletRequest request){
		return "main";
	}
}
