package com.soundconnect.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({ "user"})
public class MainController {
	
	@RequestMapping(value={"/home", "/"})
	public ModelAndView viewMain(Model model, HttpServletRequest request){
		if(request.getSession().getAttribute("userId")==null)
			return new ModelAndView("redirect:/auth");
		return new ModelAndView("main");
	}
	
	@RequestMapping(path="/index")
	public String viewIndex(Model model, HttpServletRequest request){
		return "index";
	}
}
