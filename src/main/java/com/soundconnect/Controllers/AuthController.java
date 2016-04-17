package com.soundconnect.Controllers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;    
  
@Controller  
@SessionAttributes({"userId", "confId"})
public class AuthController {  
   
	@RequestMapping("/auth")  
	public String hello(HttpServletRequest req, Model model) { 
		model.addAttribute("message", "This is data from Controller");
		model.addAttribute("session", "Current session info:<br>userId: "+req.getSession().getAttribute("userId")+"<br>confId: "+req.getSession().getAttribute("confId"));
		return "auth";  
	}  
	
	@RequestMapping(value="/auth", method=RequestMethod.POST)
	public String createSession(HttpServletRequest req, Model model){
		req.getSession().setAttribute("userId", req.getParameter("userId"));
		req.getSession().setAttribute("confId", req.getParameter("confId"));
		model.addAttribute("session", "Current session info:<br>userId: "+req.getSession().getAttribute("userId")+"<br>confId: "+req.getSession().getAttribute("confId"));
		return "auth";
	}
	
	@RequestMapping("/logout")
	public RedirectView logOut(HttpServletRequest req, SessionStatus status) throws ServletException{
		status.setComplete();
		return new RedirectView("redirect:/auth");
	}
} 