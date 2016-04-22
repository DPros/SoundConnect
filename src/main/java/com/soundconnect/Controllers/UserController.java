package com.soundconnect.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.soundconnect.Beans.User;
import com.soundconnect.Services.UserService;

@Controller
@SessionAttributes("user")
public class UserController {

	@Autowired
	UserService userserv;
	
	@RequestMapping(value="/followings", method=RequestMethod.POST)
	public String listFollowedByMe(Model model, HttpServletRequest request){
		model.addAttribute("followedusers", userserv.getFollowings((Long) request.getSession().getAttribute("userId")));
		return "includes/following";
	}
}
