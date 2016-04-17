package com.soundconnect.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class MainController {
	
	@RequestMapping(path="/home")
	public String viewMain(Model model, HttpServletRequest request){
		return "main";
	}
	
	@RequestMapping(path="/index")
	public String viewIndex(Model model, HttpServletRequest request){
		return "index";
	}
}
